package wskim.java_dustmq.fileStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileStreamV1 {
	
	public static void main(String[] args) {
		try {
			RandomAccessFile file = new RandomAccessFile("data.txt","rw");
			System.out.println(file.getFilePointer()); // 0 : ���� �������� ���� ��ġ�� ��ȯ��
			file.writeInt(10);						   // ���� 10�� ����
			System.out.println(file.getFilePointer()); // 4
			file.seek(20); 							   // ���� �������� ��ġ�� 20���� �̵���Ŵ
			System.out.println(file.getFilePointer()); // 20
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File dir = new File("D:\\1Mydata");
		File file = new File(dir, "data.txt");
		
		if(!file.exists()) {
			System.out.println("������ �������� �ʽ��ϴ�.");
			System.exit(0);
		}
		
		System.out.println(file.getPath());    // D:\data\data.txt
	    System.out.println(file.length());     // ������ ũ�⸦ ��ȯ��.
	}
}
