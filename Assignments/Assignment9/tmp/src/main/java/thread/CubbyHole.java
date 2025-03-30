package thread;

import java.util.ArrayDeque;
import java.util.Deque;

class CubbyHole {
	private static int MAX = 10;
	private Deque<Integer> buffer = new ArrayDeque<Integer>(MAX);
	// private boolean available = false;

	public synchronized int get() {
		while (buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		notify();
		return buffer.pollFirst();
	}

	public synchronized void put(int value) {
		while (!buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		buffer.addFirst(value);
		notify();

	}
}