package wskim.java_dustmq.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(9999)) {
			List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
			while(true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("접속 : " + clientSocket);
				ChatThread chatThread = new ChatThread(clientSocket, outList);
				chatThread.start();
			}
		}
	}
}

class ChatThread extends Thread {
	private Socket socket; // 현재 연결된 클라이언트(단일)
	private List<PrintWriter> outList; // 쓰레드 공유 객체
	private BufferedReader in;
	private PrintWriter out;
	
	public ChatThread(Socket socket, List<PrintWriter> outList) {
		this.socket = socket;
		this.outList = outList;
		
		// 1. socket으로 부터 읽어들일 수 있는 객체를 얻는다.
		// 2. socket으로 부터 쓰기 위한 객체를 얻는다.
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			outList.add(out);
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public void run() {
		//3. 클라이언트가 보낸 메세지를 읽는다.
		//4. 접속한 모든 클라이언트에게 메시지를 보낸다.(현재 접속된 모든 클라이언트에게 쓸수 있는 객체가 필요하다.)
		String line = null;
		try {
			while((line = in.readLine()) != null) {
				for (int i = 0; i < outList.size(); i++) {
					PrintWriter o = outList.get(i);
					o.println(line);
					o.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 접속자의 연결이 종료되었을 때
			outList.remove(out);
			for (int i = 0; i < outList.size(); i++) {
				PrintWriter o = outList.get(i);
				o.println("어떤 접속자의 연결이 끊어졌습니다.");
				o.flush();
			}
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
