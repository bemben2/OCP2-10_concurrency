package concurrency.collections;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

	public static void main(String[] args) {
		final PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
		
		new Thread() {
			public void run() {
				try {
					System.out.println("The removed element is: " + priorityBlockingQueue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			public void run() {
				priorityBlockingQueue.add(10);
				System.out.println("Successfully added element to the queue: ");
			}
		}.start();
	}
}
