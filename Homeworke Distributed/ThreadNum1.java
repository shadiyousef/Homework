
package blockingqueue;


public class ThreadNum1 extends Thread {
    BlockingQueue q1;

	public ThreadNum1(BlockingQueue q) {
		this.q1 = q;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			try {
				q1.add("f" + i + ".txt");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
    
}
