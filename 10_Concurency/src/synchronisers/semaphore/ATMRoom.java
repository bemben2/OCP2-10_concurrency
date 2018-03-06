package synchronisers.semaphore;

import java.util.concurrent.Semaphore;

public class ATMRoom {

	public static void main(String[] args) {
		Semaphore machines = new Semaphore(2);

		Thread minnieThread = new Thread(new Person(machines));
		minnieThread.setName("Minnie");
		minnieThread.start();

		Thread donaldThread = new Thread(new Person(machines));
		donaldThread.setName("donald");
		donaldThread.start();

		Thread tomThread = new Thread(new Person(machines));
		tomThread.setName("tom");
		tomThread.start();

		Thread cacperThread = new Thread(new Person(machines));
		cacperThread.setName("cacper");
		cacperThread.start();
	}

}

class Person implements Runnable {

	private Semaphore machines;

	public Person(Semaphore machines) {
		this.machines = machines;
	}

	@Override
	public void run() {
		try {
			System.out.println("machines.availablePermits()" + machines.availablePermits() + ", "
					+ Thread.currentThread().getName() + " trying to get in...");
			machines.acquire();
			System.out.println("machines.availablePermits()" + machines.availablePermits() + ", "
					+ Thread.currentThread().getName() + " taking out money...");
			Thread.sleep(1000);
			machines.release();
			System.out.println("machines.availablePermits()" + machines.availablePermits() + ", "
					+ Thread.currentThread().getName() + " finished...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
