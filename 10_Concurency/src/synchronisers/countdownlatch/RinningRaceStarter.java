package synchronisers.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class RinningRaceStarter {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(5);

		Thread minnieThread = new Thread(new Runner(counter));
		minnieThread.setName("Minnie");
		minnieThread.start();

		Thread donaldThread = new Thread(new Runner(counter));
		donaldThread.setName("Donald");
		donaldThread.start();

		Thread tomThread = new Thread(new Runner(counter));
		tomThread.setName("Tom");
		tomThread.start();
		
		System.out.println("Sterting the countdown ");
		long countVal = counter.getCount();
		while(countVal > 0) {
			Thread.sleep(1000);
			System.out.println(countVal);
			if(countVal == 1 ) {
				// once counter.countDown() in the next statement is called
				// count down will reach zero; so shout "Start"
				System.out.println("GO!!!");
			}
			counter.countDown();// down by 1
			countVal = counter.getCount(); 
		}
	}

}
class Runner implements Runnable{
	private CountDownLatch counter;
	public Runner (CountDownLatch counter) {
		this.counter = counter;// all threads share the same CountDownLatch
	}
	@Override
	public void run() {
		try {
		System.out.println(Thread.currentThread().getName() + " is ready");
		counter.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " started running...");
	}
}
