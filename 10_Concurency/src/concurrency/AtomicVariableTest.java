package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableTest {
	private static Integer integer = new Integer(0);
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	static class IntegerIncrementer extends Thread {

		@Override
		public void run() {
			System.out.println("Incremented value of integer is:" + ++integer);
		}
	}

	static class AtomicIntegerIncrementer extends Thread {

		@Override
		public void run() {
			System.out.println("Incremented value of AtomicInteger is:" + atomicInteger.incrementAndGet());
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 5; i++) {
			new IntegerIncrementer().start();
			new AtomicIntegerIncrementer().start();
		}

	}

}
