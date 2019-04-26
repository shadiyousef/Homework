package blockingqueue;


public class Main {
    public static void main(String[] args) throws InterruptedException {
		BlockingQueue q1 = new BlockingQueue(100);
		BlockingQueue q2 = new BlockingQueue(100);
		ThreadNum1 t1 = new ThreadNum1(q1);
		Thread th1 = new Thread(t1);
		ThreadNum2 th2[] = new ThreadNum2[8];

		ThreadNum3 t3 = new ThreadNum3(q2);
		Thread th3 = new Thread(t3);
		th1.start();
		for (int i = 0; i < 8; i++) {
			th2[i] = new ThreadNum2(q1, q2);
			th2[i].start();

		}

		th3.start();

		th1.join();

		for (int i = 0; i < 8; i++) {

			th2[i].join();

		}

		th3.join();

	}
    
}
