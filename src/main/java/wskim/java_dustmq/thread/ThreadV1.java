package wskim.java_dustmq.thread;

public class ThreadV1 {
	
	// Thread 클래스를 상속받아 멀티 쓰레드 생성
	public static void main(String[] args) {
		System.out.println("[MainThread] - start");
		String name = Thread.currentThread().getName();
		System.out.println("[Thread] : " + name);
		
		MyThread thread1 = new MyThread("*");
		MyThread thread2 = new MyThread("+");
		
		thread1.start();
		thread2.start();
		
		System.out.println("[MainThread] - end");
	}
	
	static class MyThread extends Thread{
		
		private String str;
		
		public MyThread(String str) {
			this.str = str;
		}
		
		@Override
		public void run() {
			
			String name = Thread.currentThread().getName();
			System.out.println("--------------------" + name + "--------------------");
			
			for (int i = 0; i < 10; i++) {
				System.out.print(str + " ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			}
		}
	}
}
