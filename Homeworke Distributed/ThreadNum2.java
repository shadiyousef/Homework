
package blockingqueue;
import java.io.IOException;


public class ThreadNum2 extends Thread {
    BlockingQueue q1;
	BlockingQueue q2;
	private String rawDataFromFile;
	private String processedData;
	private String filename;

	public ThreadNum2(BlockingQueue q1, BlockingQueue q2) {
		this.q1 = q1;
		this.q2 = q2;
	}

	@Override
	public void run() {
	
		while (q2.count() <= q2.limit) {
			try {
				filename = q1.pop().toString();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				rawDataFromFile = FileUtils.readFileAsString("data\\" + filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			processedData = filename + SomeMethod.count(rawDataFromFile);
			try {
				q2.add(processedData);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}

class ThirdThread extends Thread {
	BlockingQueue q2;
	private String processedData;

	public ThirdThread(BlockingQueue q2) {
		this.q2 = q2;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				processedData = (String) q2.pop();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				FileUtils.appendStringToFile("processedData.txt", processedData);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
    
}
