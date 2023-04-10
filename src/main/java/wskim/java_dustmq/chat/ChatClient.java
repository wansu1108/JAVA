package wskim.java_dustmq.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String name = "kim";
		
		Socket socket = new Socket("127.0.0.1", 9999);
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		String line = null;
		InputThread inputThread = new InputThread(in);
		inputThread.start();
		
		while((line = keyboard.readLine()) != null) {
			// 서버에 정보를 전송 한다.
			out.println(name +" : "+ line);
			out.flush();
		}
		
	}
}

class InputThread extends Thread {
	
	private BufferedReader in;
	
	public InputThread(BufferedReader in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		String line = null;
		try {
			while((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
