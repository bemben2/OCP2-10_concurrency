package concurrency.collections;

import java.util.PriorityQueue;

public class PriorityQueueExample {

	public static void main(String[] args) {
		final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		// spawn a thread that removes an element from the priority queue
		new Thread () {
			public void run() {
				System.out.println("The remove element is: " + priorityQueue.remove());
			}
		}.start();
		
		new Thread () {
			public void run() {
				priorityQueue.add(10);
				System.out.println("Successfully added to the queue");
			}
		}.start();
	}
}
