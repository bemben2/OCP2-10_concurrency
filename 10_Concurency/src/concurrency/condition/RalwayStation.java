package concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RalwayStation {

	private static Lock station = new ReentrantLock();
	private static Condition joeArrival = station.newCondition();
	
	class Train implements Runnable{

		@Override
		public void run() {
			station.lock();
			try {
				System.out.println(Thread.currentThread().getName() + ": I've arrived in station");
				if (Thread.currentThread().getName().startsWith("IC1122")) {
					joeArrival.signalAll();
				}
			} finally {
				station.unlock();
			}
		}
	}
	
	class WaitForJoe implements Runnable {

		@Override
		public void run() {
			System.out.println("Waiting in the station for IC1122 in which Joe is comming");
			station.lock();
			try {
				//awit foe's train arrival and relase the 'station' lock
				joeArrival.await();
				// if this statement execiutes, it means we got a train arrival signal
				System.out.println("Pick-up Joe anf go home");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				station.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread waitJoe = new Thread(new RalwayStation().new WaitForJoe());
		waitJoe.start();
		
		Thread parisToMunich = new Thread( new RalwayStation().new Train());
		parisToMunich.setName("IC1234 - Paris to Munich");
		parisToMunich.start();
				
		Thread parisToMadrid = new Thread( new RalwayStation().new Train());
		parisToMadrid.setName("IC2211 - Paris Madrid");
		parisToMadrid.start();
		
		Thread munichToParis = new Thread( new RalwayStation().new Train());
		munichToParis.setName("IC1122 - munich To Paris");
		munichToParis.start();
		
		Thread madridToParis = new Thread( new RalwayStation().new Train());
		madridToParis.setName("IC4321 - madrid To Paris");
		madridToParis.start();
		
				
	}

}
