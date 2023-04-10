package wskim.java_dustmq.chat2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		List<ChatThread> list = Collections.synchronizedList(new ArrayList<>()); // 동시성문제 해결을 위한 리스트
		
		while(true) {
			Socket socket = serverSocket.accept(); // 클라이언트 접속 , 접속이 끊기면 클라이언트는 null을 전송한다.
			ChatThread client = new ChatThread(socket, list);
			client.start();
		}
	}
}
