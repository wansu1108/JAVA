package wskim.java_dustmq.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressExam {

	public static void main(String[] args) {
		try {
			// InetAddress�� ���� �ּ� �˻�
			InetAddress ia = InetAddress.getLocalHost();
			System.out.println(ia.getHostAddress());
		} catch (UnknownHostException e) {
			e.getStackTrace();
		}

		try {
			// �������� IP�ּҸ� ������ ��� getAllByName()�� ���� �迭�� �ּҸ� �޴´�.
			InetAddress[] iaArray = InetAddress.getAllByName("www.google.com");
			for (InetAddress ia : iaArray) {
				System.out.println(ia.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.getStackTrace();
		}
	}
}
