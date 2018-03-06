package forkjoin.recursuin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInitRecursiveAction extends RecursiveAction{

	private static final int THRESHOLD=10000;
	private int[] data;
	private int start, end;
	
	public RandomInitRecursiveAction(int[] data, int start, int end) {
		super();
		this.data = data;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		int [] data = new int[10_000_000];
		ForkJoinPool fjPool = new ForkJoinPool();
		RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
		fjPool.invoke(action);

	}

	@Override
	protected void compute() {
		if(end - start <= THRESHOLD) {
			for (int i = start; i < end; i++) {
				data[i]= ThreadLocalRandom.current().nextInt();
			}
		} else {
			int halfWay = ((end-start) / 2) + start;
			RandomInitRecursiveAction left = new RandomInitRecursiveAction(data, start, halfWay);
			left.fork();
			
			RandomInitRecursiveAction right = new RandomInitRecursiveAction(data, halfWay, end);
			right.compute();
			left.join();
			
			//invokeAll(right, left);
		}
	}
}
