package synchronisers.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

class MyCallable implements Callable<Integer> {
	@Override
	public Integer call() {
		StringBuilder sb = new StringBuilder();
		int count = ThreadLocalRandom.current().nextInt(1, 11);
		for(int i = 1; i <= count; i++) {
			sb.append("Runing...").append(i).append("\n");
		}
		System.out.println(sb.toString());
		return count;
	}
}
public class CallableTest {

	public static void main(String[] args) {
		Callable<Integer> callable = new MyCallable();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Integer> future = executorService.submit(callable); //asynchronuos
		try {
			Integer callableResult = future.get();// blocks if ecessary
			System.out.println("Random: " + callableResult);
			executorService.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
