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
			System.out.println("...");
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
