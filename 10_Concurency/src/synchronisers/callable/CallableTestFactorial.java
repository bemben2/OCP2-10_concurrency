package synchronisers.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Factorial implements Callable<Long> {
	long n;

	public Factorial(long n) {
		this.n = n;
	}

	@Override
	public Long call() throws Exception {
		if (n <= 0) {
			throw new Exception("for finding factorial, N should be >0");
		}
		long fact = 1;
		for (long i = 1; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}
}

public class CallableTestFactorial {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Long> task = new Factorial(20);

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<Long> future = executorService.submit(task);

		System.out.printf("factorial of %d is %d", 20, future.get());
		executorService.shutdownNow();

	}

}
