package wskim.java_dustmq.chat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
		this.name = br.readLine(); // 무조건 닉네임을 하나 보내줄예정
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
		// broadcast
		// ChatThread는 사용자가 보낸 메세지를 읽어들여서
		// 접속된 모든 클라이언트에게 메시지를 보낸다.
		
		// 나를 제외한 모든 사용자에게 "00님이 연결되었습니다."
		// 현재 ChatThread를 제외하고 보낸다.
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
			// 1. 클라이언트 /quit 입력
			// 2. 클라이언트가 프로그램 강제종료
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
