package thread;

class Consumer extends Thread {
	private CubbyHole cubbyhole;
	private int number;

	public Consumer(CubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {
		int value = 0;
		for (int i = 0; i < 1000; i++) {
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			value = cubbyhole.get();
			// GREEN TEXT
			System.out.println("\033[0;32m Consumer #" + this.number + " got: " + value + " :: "
					+ System.currentTimeMillis() + "\033[0m");

		}
	}
}
