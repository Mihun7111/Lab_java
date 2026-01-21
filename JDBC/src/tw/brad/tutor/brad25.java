package tw.brad.tutor;

import java.util.concurrent.atomic.AtomicBoolean;

public class brad25 {
	public static void main(String[] args) {
		AtomicBoolean running = new AtomicBoolean(true);
		new Thread(() -> {
			for (int i=0; running.get(); i++) {
				System.out.println("A:" + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}).start();
		new Thread(() -> {
			for (int i=0; i<10; i++) {
				System.out.println("B:" + i);
				if (i == 7) {
					running.set(false);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}
}
