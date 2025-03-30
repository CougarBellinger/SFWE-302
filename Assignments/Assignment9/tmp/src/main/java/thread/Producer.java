package thread;

class Producer extends Thread {
	private CubbyHole cubbyhole;
	private int number;

	public Producer(CubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			cubbyhole.put(i);

			// RED TEXT
			System.out.println("\033[0;31m Producer #" + this.number + " put: " + i + " :: "
					+ System.currentTimeMillis() + "\033[0m");

			// try {
			// sleep((int) (Math.random() * 100));
			// } catch (InterruptedException e) {
			// }
		}
	}
}