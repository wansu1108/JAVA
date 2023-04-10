package wskim.java_dustmq.chat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
	public static void main(String[] args) throws Exception{
		String name = "kim";
		
		Socket socket = new Socket("127.0.0.1", 8888);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
		
		// �г��� ����
		pw.println(name);
		pw.flush();
		
		// ��׶��� �۾� , �������� ������ �޽����� ȭ�鿡 ���
		InputStream inputStream = new InputStream(br);
		inputStream.start();
		
		// Ű���带 ���� �Էµ� ������ ������ ����
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
			System.out.println("...");
		} finally {
			try {
				System.out.println("����");
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
