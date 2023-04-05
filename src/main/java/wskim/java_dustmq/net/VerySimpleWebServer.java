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
		// Ŭ���̾�Ʈ�� ����� ���� ServerSocket ����
		ServerSocket ss = new ServerSocket(9090);
		System.out.println("Ŭ���̾�Ʈ�� ��ٸ��� �� �Դϴ�.");
		Socket soket = ss.accept();
		
		// Socket�� ���� ������ IO
		InputStream in = soket.getInputStream();
		OutputStream out = soket.getOutputStream();

		// Ŭ���̾�Ʈ ��û �б�
		BufferedReader readLine = new BufferedReader(new InputStreamReader(in));
		String firstLine = readLine.readLine();
		List<String> headers = new ArrayList<String>();
		String line = null;
		
		System.out.println(firstLine);
		while(!(line = readLine.readLine()).equals("")) {
			headers.add(line);
			System.out.println(line);
		}
		
		// Ŭ���̾�Ʈ�� ����
		System.out.println("Ŭ���̾�Ʈ���� ����");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
		writer.println("HTTP/1.1 200 OK"); // ���� �޽���
		writer.println("name: kim");	   // ���� ���
		writer.println("email: kim@blahblah.com");
		writer.println();	//����
		writer.println("<html>");
		writer.println("<h1>Hello World</h1>");
		writer.println("</html>");
		
		writer.flush();
		readLine.close();
		writer.close();
		
		// ���ϼ��� ����
		ss.close();
		System.out.println("������ �����մϴ�.");
	}
}
