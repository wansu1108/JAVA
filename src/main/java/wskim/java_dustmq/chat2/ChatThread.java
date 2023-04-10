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
		this.name = br.readLine(); // ������ �г����� �ϳ� �����ٿ���
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
					return ( includeMe || o != this ); // ���� ������ ��� �����
				})
				.forEach((o)-> o.sendMessage(msg));
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void run() {
		// broadcast
		// ChatThread�� ����ڰ� ���� �޼����� �о�鿩��
		// ���ӵ� ��� Ŭ���̾�Ʈ���� �޽����� ������.
		
		// ���� ������ ��� ����ڿ��� "00���� ����Ǿ����ϴ�."
		// ���� ChatThread�� �����ϰ� ������.
		try {
			broadCast(name + "���� ����Ǿ����ϴ�.", false);
			
			// Ŭ���̾�Ʈ�� ������ �����ϸ� null�� ������.
			String line = null;
			while((line = br.readLine()) != null) {
				if("/quit".equals(line)) {
					break;
				}
				broadCast(name + " : " + line, true);
			}
		} catch (Exception e) {
		} finally {
			// Ŭ���̾�Ʈ���� ���� ����
			// 1. Ŭ���̾�Ʈ /quit �Է�
			// 2. Ŭ���̾�Ʈ�� ���α׷� ��������
			broadCast(name + "���� ������ ����Ǿ����ϴ�.", false);
			this.list.remove(this);
			
			// ���� ����
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
