package synchronisers.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

class SomeTask implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " SomeTask::run()");
	}
}

public class PhaserTest {
	public static void main(String[] args) {
		List<Runnable> tasks = new ArrayList<>();
		tasks.add(new SomeTask());
		tasks.add(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()
						+ "Anonymous Inner Class - PhaserTest.main().new Runnable() {...}.run()");
			}
		});
		new PhaserTest().runTasks(tasks);
	}

	private void runTasks(List<Runnable> tasks) {// method from java api
		// final Phaser phaser = new Phaser(1); // "1" to register main
		final Phaser phaser = new Phaser() {
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("Advancing - registered parties is: " + registeredParties);
				return true;
			}
		};
		phaser.register(); // register main thread (parties == 1)
		// task is used in an inner class = must be declared 'final'
		for (final Runnable task : tasks) {
			phaser.register();
			new Thread() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " waiting...");
					// wait until the arrived parties equals the registtered parties
					phaser.arriveAndAwaitAdvance();
					System.out.println(Thread.currentThread().getName() + " running....");
					task.run();
				}
			}.start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " deregistering...");
		phaser.arriveAndDeregister();
	}
}
