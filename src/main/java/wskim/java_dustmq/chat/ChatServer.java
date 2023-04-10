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
				System.out.println("���� : " + clientSocket);
				ChatThread chatThread = new ChatThread(clientSocket, outList);
				chatThread.start();
			}
		}
	}
}

class ChatThread extends Thread {
	private Socket socket; // ���� ����� Ŭ���̾�Ʈ(����)
	private List<PrintWriter> outList; // ������ ���� ��ü
	private BufferedReader in;
	private PrintWriter out;
	
	public ChatThread(Socket socket, List<PrintWriter> outList) {
		this.socket = socket;
		this.outList = outList;
		
		// 1. socket���� ���� �о���� �� �ִ� ��ü�� ��´�.
		// 2. socket���� ���� ���� ���� ��ü�� ��´�.
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
		//3. Ŭ���̾�Ʈ�� ���� �޼����� �д´�.
		//4. ������ ��� Ŭ���̾�Ʈ���� �޽����� ������.(���� ���ӵ� ��� Ŭ���̾�Ʈ���� ���� �ִ� ��ü�� �ʿ��ϴ�.)
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
			// �������� ������ ����Ǿ��� ��
			outList.remove(out);
			for (int i = 0; i < outList.size(); i++) {
				PrintWriter o = outList.get(i);
				o.println("� �������� ������ ���������ϴ�.");
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
