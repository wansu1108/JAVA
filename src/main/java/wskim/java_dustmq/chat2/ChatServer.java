package wskim.java_dustmq.chat2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		List<ChatThread> list = Collections.synchronizedList(new ArrayList<>()); // ���ü����� �ذ��� ���� ����Ʈ
		
		while(true) {
			Socket socket = serverSocket.accept(); // Ŭ���̾�Ʈ ���� , ������ ����� Ŭ���̾�Ʈ�� null�� �����Ѵ�.
			ChatThread client = new ChatThread(socket, list);
			client.start();
		}
	}
}
