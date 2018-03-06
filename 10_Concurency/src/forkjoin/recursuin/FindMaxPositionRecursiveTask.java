package forkjoin.recursuin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindMaxPositionRecursiveTask extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 1000;
	private int[] data;
	private int start, end;

	public FindMaxPositionRecursiveTask(int[] data, int start, int end) {
		super();
		this.data = data;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		int[] data = new int[10_000_000];
		ForkJoinPool fjPool = new ForkJoinPool();
		RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
		fjPool.invoke(action);

		FindMaxPositionRecursiveTask task = new FindMaxPositionRecursiveTask(data, 0, data.length);
		Integer maxPosition = fjPool.invoke(task);
		System.out.println("Position: " + maxPosition + "; value: " + data[maxPosition]);

	}

	@Override
	protected Integer compute() {
		if (end - start <= THRESHOLD) {
			int position = 0;
			for (int i = start; i < end; i++) {
				if (data[i] > data[position]) {
					position = i;
				}
			}
			return position;
		} else {
			int halfWay = ((end - start) / 2) + start;
			FindMaxPositionRecursiveTask left = new FindMaxPositionRecursiveTask(data, start, halfWay);
			left.fork();
			FindMaxPositionRecursiveTask right = new FindMaxPositionRecursiveTask(data, halfWay, end);
			int maxPosRHS = right.compute();
			int maxPosLHS = left.join();
			if (data[maxPosLHS] > data[maxPosRHS]) {
				return maxPosLHS;
			} else if (data[maxPosLHS] < data[maxPosRHS]) {
				return maxPosRHS;
			} else {
				return maxPosLHS < maxPosRHS ? maxPosLHS : maxPosRHS;
			}
		}
	}
}
