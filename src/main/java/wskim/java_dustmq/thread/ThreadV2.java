package wskim.java_dustmq.thread;

// Runnable �������̽��� �����Ͽ� ��Ƽ ������ ����
public class ThreadV2 {
	public static void main(String[] args) {
		System.out.println("[MainThread] - start");
		String name = Thread.currentThread().getName();
		System.out.println("[Thread] : " + name);
		
		MyThreadV2 myThread1 = new MyThreadV2("*");
		MyThreadV2 myThread2 = new MyThreadV2("+");
		
		// Thread �ν��Ͻ��� ������ �� �����ڿ� Runnable �ν��Ͻ��� �־��ش�.
		Thread thread1 = new Thread(myThread1);
		Thread thread2 = new Thread(myThread2);
		
		thread1.start();
		thread2.start();
		
		System.out.println("[MainThread] - end");
	}

	static class MyThreadV2 implements Runnable {

		private String str;

		public MyThreadV2(String str) {
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
