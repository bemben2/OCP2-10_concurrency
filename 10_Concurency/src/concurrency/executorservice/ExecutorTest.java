package concurrency.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	public static void main(String[] args) {
		//cahedThreadPool
		ExecutorService es = Executors.newCachedThreadPool();
		
		// FixedThreadPool
		int cpuCount = Runtime.getRuntime().availableProcessors();
		ExecutorService es2 = Executors.newFixedThreadPool(cpuCount);
		
		//SingleThreadExecutor
		ExecutorService es3 = Executors.newSingleThreadExecutor();
		
	}

}
