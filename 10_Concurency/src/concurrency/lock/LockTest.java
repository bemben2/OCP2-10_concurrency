package concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	public static void main(String[] args) {
		Lock machineLock = new ReentrantLock();
		Thread minnieThread = new Thread(new Somebody(machineLock));
		minnieThread.setName("Minnie");
		minnieThread.start();
		
		Thread donaldThread = new Thread(new Somebody(machineLock));
		donaldThread.setName("donald");
		donaldThread.start();
		
		Thread tomThread = new Thread(new Somebody(machineLock));
		tomThread.setName("tom");
		tomThread.start();
		
		Thread cacperThread = new Thread(new Somebody(machineLock));
		cacperThread.setName("cacper");
		cacperThread.start();
	}

}
class Somebody implements Runnable {
	private Lock machineLock;
	public Somebody (Lock machineLock) {
		this.machineLock = machineLock;
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " waiting to access an ATM machine");
			machineLock.lock();
			System.out.println(Thread.currentThread().getName() + " is accessing an ATM machine");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " is done using the ATM machinedfg");
			System.out.println();
			machineLock.unlock();
		}
		
	}
	
}
