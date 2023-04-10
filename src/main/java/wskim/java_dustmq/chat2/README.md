# 채팅프로그램

## 1. 채팅프로그램 설명

1. 순서
    1. java 패키지명 ChatClient 닉네임 [enter] - 서버에 접속
    2. 접속한 모든 사용자에게 "OOO님이 접속하였습니다"
    3. 채팅을 입력한 후 [enter]를 입력하면 모든 사용자(나 포함)에게 메시지가 전달된다.
    4. /quit를 입력하면 연결이 끊어진다. 연결이 끊어질 때 모든 사용자에게 "OOO님이 연결을 끊었습니다"
    5. 강제로 연결을 끊었을때 (ex 프로그램 강제종료) 사용자에게 "OOO님이 연결을 끊었습니다."
2. 서버
    1. 사용자가 접속할 때 마다 Thread를 만든다. ( `ChatThread` )
        - ChatThread는 클라이언트와 통신을 하는 용도
        - 클라이언트가 10개면 ChatThread도 10개 생성
        - 클라이언트 접속이 끊어지면 하나의 ChatThread도 종료된다.
        - ChatThread는 사용자가 보내준 chat메시지를 현재 접속한 모든 사용자에게 보낸다.
        - ChatThtread는 현재 접속한 모든 사용자 연결정보 or 출력객체를 알아야 한다.

## 2. 클래스의 역할

1. ChatServer : 클라이언트 접속을 기다린다. 클라이언트와 연결되면 쓰레드( `ChatThread` )를 생성 한다.
2. ChatThread :  ChatServer로 부터 클라이언트와의 소켓을 전달 받아, 클라이언트와 통신 한다.
3. ChatClient :  서버와 소켓을 연결한다. 키보드로 메시지를 입력받아 서버에 전송한다

## 3. 객체

### 3.1 Socket

- Server와 Client가 계속 ㄷ연결을 유지하는 양방향 통신이다.
- Server와 Client가 실시간으로 데이터를 주고받는 상황이 필요한 경우 사용된다.
- **연결이 종료되면 null을 전송한다.**

### 3.2 BufferedReader

- 소켓의 정보를 읽어들이기 위한 객체이다.

### 3.3 PrintWriter

- 정보를 소켓에 담아서 보내기 위한 객체이다.

## 4. 코드

### 4.1 ChatServer

```java
public class ChatServer {
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		List<ChatThread> list = Collections.synchronizedList(new ArrayList<>()); // 동시성문제 해결을 위한 리스트
		
		while(true) {
			Socket socket = serverSocket.accept();
			ChatThread client = new ChatThread(socket, list);
			client.start();
		}
	}
}
```

- ServerSocket : 소켓 서버를 생성한다.
    - accept() : 소켓 서버는 클라이언트의 요청을 기다리고, 클라이언트와 연결이 되면 Socket을 반환한다.
- ChatThread : 서버는 클라이언트와의 통신을 위해 Socket을 전달하고, Thread를 생성한다.
- List<ChatThread> : 모든 Thread가 공유하는 공유 객체이다.

### 4.2 ChatThread

```java
public class ChatThread extends Thread{
	
	private String name;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	List<ChatThread> list;
	
	public ChatThread(Socket socket, List<ChatThread> list) throws Exception {
		this.socket = socket;
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.br = br;
		this.pw = pw;
		this.name = br.readLine();
		this.list = list;
		this.list.add(this);
	}
	
	public void sendMessage(String msg) {
		pw.println(msg);
		pw.flush();
	}
	
	private void broadCast(String msg, boolean includeMe) {
		List<ChatThread> chatThreads = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			chatThreads.add(list.get(i));
		}
		
		try {
			chatThreads.stream()
				.filter((o)-> {
					return ( includeMe || o != this ); // 나를 제외한 모든 사용자
				})
				.forEach((o)-> o.sendMessage(msg));
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void run() {
		try {
			broadCast(name + "님이 연결되었습니다.", false);
			
			// 클라이언트가 연결을 종료하면 null을 전송함.
			String line = null;
			while((line = br.readLine()) != null) {
				if("/quit".equals(line)) {
					break;
				}
				broadCast(name + " : " + line, true);
			}

		} catch (Exception e) {
		} finally {
			// 클라이언트와의 연결 종료
			broadCast(name + "님의 연결이 종료되었습니다.", false);
			this.list.remove(this);
			
			// 접속 종료
			try {
				br.close();
				pw.close();
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
```

- name : 클라이언트가 서버와 연결되면 , 가장먼저 닉네임을 전송 한다.
- List<ChatThread> : 모든 쓰레드가 공유하는 공유 객체이다.
    - 공유객체는 현재 접속하여 생성된 모든 Thread를 담는 공간이다.
    - Thread가 생성될 때, 공유객체에 Thraed를 담아준다. `list.add(this)`
- sendMessage : 전달받은 문자를 출력하기 위한 함수
- broadCast : 공유객체를 통해 접속한 모든 클라이언트에게 메세지를 전달한다.
    - boolean includeMe : 메세지를 보낼때 자신을 포함할지 여부
- 접속종료
    1. /quit 전송
        1. 클라이언트가 위의 문장을 입력하여 전송하면, Socket을 종료한다.
    2. 프로그램 종료
        1. 클라이언트가 프로그램을 강제 종료하여 Socket이 종료되면, 서버에 null이 전송되어 while문이 종료된다
    3. 접속 종료
        1. 접속이 종료되면 공유객체에서 해당 쓰레드를 제거한다.
        2. 접속 종료 메세지를 다른 클라이언트에게 전송 한다.

### 4.3 ChatClient

```java
public class ChatClient {
	public static void main(String[] args) throws Exception{
		String name = "kim";
		
		Socket socket = new Socket("127.0.0.1", 8888);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
		
		// 닉네임 전송
		pw.println(name);
		pw.flush();
		
		// 백그라운드 작업 , 서버에서 보내준 메시지를 화면에 출력
		InputStream inputStream = new InputStream(br);
		inputStream.start();
		
		// 키보드를 통해 입력된 구문을 서버로 전송
		try {
			String line = null;
			while((line = keybord.readLine())!= null) {
				if("/quit".equals(line)) {
					pw.println(line);
					pw.flush();
					break;
				}
				pw.println(line);
				pw.flush();
			}
		} catch (Exception e) {
		} finally {
			try {
				System.out.println("종료");
				br.close();
				pw.close();
				socket.close();
			}catch (Exception e) {
			}
		}
	}
}

class InputStream extends Thread {
	private BufferedReader in;
	
	public InputStream(BufferedReader in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			String line = null;
			while((line = in.readLine())!= null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("...");
			e.printStackTrace();
		} 
	}
}
```

- Socket : IP, PORT로 서버에 접근 하고, 읽고 쓰기 위한 객체를 생성한다
    - BufferedReader
    - PrintWriter
- 키보드를 통해 메시지를 입력받는다.
- 입력 받은 메세지를 서버에 전송한다.
- 메세지를 입력받음과 동시에, 서버에서 전송한 메시지를 전달받기 위해 `InputThread`를 사용한다.