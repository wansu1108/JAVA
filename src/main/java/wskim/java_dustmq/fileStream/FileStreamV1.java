package wskim.java_dustmq.fileStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileStreamV1 {
	
	public static void main(String[] args) {
		try {
			RandomAccessFile file = new RandomAccessFile("data.txt","rw");
			System.out.println(file.getFilePointer()); // 0 : 파일 포인터의 현재 위치를 반환함
			file.writeInt(10);						   // 정수 10을 저장
			System.out.println(file.getFilePointer()); // 4
			file.seek(20); 							   // 파일 포인터의 위치를 20으로 이동시킴
			System.out.println(file.getFilePointer()); // 20
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File dir = new File("D:\\1Mydata");
		File file = new File(dir, "data.txt");
		
		if(!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		
		System.out.println(file.getPath());    // D:\data\data.txt
	    System.out.println(file.length());     // 파일의 크기를 반환함.
	}
}
