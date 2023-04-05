package wskim.java_dustmq.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VerySimpleWebServer {

	public static void main(String args[]) throws Exception {
		// 클라이언트와 통신을 위한 ServerSocket 생성
		ServerSocket ss = new ServerSocket(9090);
		System.out.println("클라이언트를 기다리는 중 입니다.");
		Socket soket = ss.accept();
		
		// Socket을 통해 생성한 IO
		InputStream in = soket.getInputStream();
		OutputStream out = soket.getOutputStream();

		// 클라이언트 요청 읽기
		BufferedReader readLine = new BufferedReader(new InputStreamReader(in));
		String firstLine = readLine.readLine();
		List<String> headers = new ArrayList<String>();
		String line = null;
		
		System.out.println(firstLine);
		while(!(line = readLine.readLine()).equals("")) {
			headers.add(line);
			System.out.println(line);
		}
		
		// 클라이언트에 응답
		System.out.println("클라이언트에게 응답");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
		writer.println("HTTP/1.1 200 OK"); // 상태 메시지
		writer.println("name: kim");	   // 응답 헤더
		writer.println("email: kim@blahblah.com");
		writer.println();	//빈줄
		writer.println("<html>");
		writer.println("<h1>Hello World</h1>");
		writer.println("</html>");
		
		writer.flush();
		readLine.close();
		writer.close();
		
		// 소켓서버 종료
		ss.close();
		System.out.println("서버를 종료합니다.");
	}
}
