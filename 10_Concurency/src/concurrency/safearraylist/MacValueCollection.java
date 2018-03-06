package concurrency.safearraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MacValueCollection {
	private List<Integer> integers = new ArrayList<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void add(Integer i) {
		rwl.writeLock().lock();
		try {
			integers.add(i);
		} finally {
			rwl.writeLock().unlock();
		}
	}
	public int findMax() {
		rwl.readLock().lock();
		try {
			return Collections.max(integers);
		} finally {
			rwl.readLock().unlock();
		}
	}
}
