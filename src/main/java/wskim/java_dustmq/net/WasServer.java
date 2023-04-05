package wskim.java_dustmq.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WasServer {
	public static void main(String[] args) throws IOException {
		ServerSocket severSocket = new ServerSocket(10000);

		try {
			while (true) {
				Socket clientSocket = severSocket.accept();
				ClientThread ct = new ClientThread(clientSocket);
				ct.start();
			}
		} finally {
			severSocket.close();
		}
	}

	static class ClientThread extends Thread {

		private Socket clientSocket;

		public ClientThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				InputStream in = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				OutputStream out = clientSocket.getOutputStream();
				PrintWriter pr = new PrintWriter(new OutputStreamWriter(out));

				// http://localhost:10000/hello
				// GET /hello HTTP/1.1
				String firstLine = br.readLine();
				System.out.println(firstLine);
				String line = null;
				List<String> headers = new ArrayList<String>();
				while (!(line = br.readLine()).equals("")) {
					headers.add(line);
					System.out.println(line);
				}

				pr.println("HTTP/1.1 200 OK");
				pr.println("name: kim");
				pr.println("email: hello@naver.com");
				pr.println();
				pr.println("<html>");
				pr.println(firstLine);
				pr.println("</html>");
				pr.flush();

				br.close();
				pr.close();
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
