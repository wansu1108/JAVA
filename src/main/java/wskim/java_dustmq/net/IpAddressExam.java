package wskim.java_dustmq.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressExam {

	public static void main(String[] args) {
		try {
			// InetAddress를 통해 주소 검색
			InetAddress ia = InetAddress.getLocalHost();
			System.out.println(ia.getHostAddress());
		} catch (UnknownHostException e) {
			e.getStackTrace();
		}

		try {
			// 여러개의 IP주소를 가지는 경우 getAllByName()을 통해 배열로 주소를 받는다.
			InetAddress[] iaArray = InetAddress.getAllByName("www.google.com");
			for (InetAddress ia : iaArray) {
				System.out.println(ia.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.getStackTrace();
		}
	}
}
