package assignments;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CounterSync (CLI + self-checking harness)
 *
 * Usage examples:
 *   java --module-path bin -m Java_Again/assignments.CounterSync
 *   java --module-path bin -m Java_Again/assignments.CounterSync --threads 4 --inc 50000
 */
public class CounterSync {

	private static int THREADS = 8;
	private static int INCREMENTS_PER_THREAD = 100_000;

	interface Counter {
		void increment();
		int get();
	}

	static class AtomicCounter implements Counter {
		private final AtomicInteger count = new AtomicInteger(0);
		@Override public void increment() { count.incrementAndGet(); }
		@Override public int get() { return count.get(); }
	}

	static class SynchronizedCounter implements Counter {
		private int count = 0;
		@Override public synchronized void increment() { count++; }
		@Override public synchronized int get() { return count; }
	}

	static class LockCounter implements Counter {
		private int count = 0;
		private final ReentrantLock lock = new ReentrantLock();
		@Override
		public void increment() {
			lock.lock();
			try {
				count++;
			} finally {
				lock.unlock();
			}
		}
		@Override
		public int get() {
			lock.lock();
			try {
				return count;
			} finally {
				lock.unlock();
			}
		}
	}

	private static long runTest(Counter counter) throws InterruptedException {
		Thread[] threads = new Thread[THREADS];
		for (int i = 0; i < THREADS; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
					counter.increment();
				}
			});
		}

		long start = System.nanoTime();
		for (Thread t : threads) t.start();
		for (Thread t : threads) t.join();
		return (System.nanoTime() - start) / 1_000_000L;
	}

	private static void printResult(String label, Counter counter, long elapsedMs) {
		int expected = THREADS * INCREMENTS_PER_THREAD;
		System.out.printf("%s -> final=%d expected=%d time=%dms%n", label, counter.get(), expected, elapsedMs);
		if (counter.get() != expected) {
			throw new AssertionError(label + " failed: final value " + counter.get() + " != expected " + expected);
		}
	}

	private static void selfCheck() throws InterruptedException {
		System.out.println("Running self-check with threads=" + THREADS + " increments/thread=" + INCREMENTS_PER_THREAD);

		AtomicCounter atomic = new AtomicCounter();
		long t1 = runTest(atomic);
		printResult("AtomicCounter", atomic, t1);

		SynchronizedCounter sync = new SynchronizedCounter();
		long t2 = runTest(sync);
		printResult("SynchronizedCounter", sync, t2);

		LockCounter lockC = new LockCounter();
		long t3 = runTest(lockC);
		printResult("LockCounter", lockC, t3);

		System.out.println("Self-check passed.");
	}

	private static void printUsage() {
		System.out.println("Usage: java --module-path bin -m Java_Again/assignments.CounterSync [--threads N] [--inc N]");
		System.out.println("  --threads N    Number of concurrent threads (default 8)");
		System.out.println("  --inc N        Increments per thread (default 100000)");
	}

	public static void main(String[] args) throws InterruptedException {
		// Simple CLI parsing
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "--threads":
					if (i + 1 < args.length) THREADS = Integer.parseInt(args[++i]);
					else { printUsage(); return; }
					break;
				case "--inc":
					if (i + 1 < args.length) INCREMENTS_PER_THREAD = Integer.parseInt(args[++i]);
					else { printUsage(); return; }
					break;
				case "-h":
				case "--help":
					printUsage();
					return;
				default:
					System.out.println("Unknown arg: " + args[i]);
					printUsage();
					return;
			}
		}

		selfCheck();
	}
}

