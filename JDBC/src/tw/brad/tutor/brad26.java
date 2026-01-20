package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class brad26 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final BlockingQueue<brad26Task> QUEUE = new ArrayBlockingQueue<>(200);
	private static final AtomicBoolean RUNNING = new AtomicBoolean(true);
	
	public static void main(String[] args) throws Exception {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD)){
			System.out.println("Connection OK");
		}
		/*
		 *	RUNNING == true
		 *	Producer 一直進貨/買貨 -> QUEUE
		 *	logger 一直 -> QUEUE
		 *	Main Thread -> 喊停 / 跑回圈
		 */
		
		ExecutorService producer = Executors.newFixedThreadPool(2);
		ExecutorService writer = Executors.newFixedThreadPool(4);
		
		producer.submit(new Producer());
		producer.submit(new Producer());
		
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		
		Thread.sleep(2*1000);
		RUNNING.set(false);
		producer.shutdown();
		System.out.println("Game Over");
		
	}
	
	static class Producer implements Runnable {
		private final Random r = new Random();
		@Override
		public void run() {
			try {
				while (RUNNING.get()) {
					brad26Task t;
					if (r.nextInt(100) < 70) {
						t = new brad26PurchaseTask(1 + r.nextInt(4));
					}else {
						t = new brad26RestockTask(1 + r.nextInt(6));
					}
					QUEUE.put(t);
					Thread.sleep(30);
				}
			}catch(Exception e) {
				
			}
		}
	}
	static class Writer implements Runnable {
		@Override
		public void run() {
			brad26StoreService service = new brad26StoreService(URL, USER, PASSWD);
			try {
				while (RUNNING.get() || !QUEUE.isEmpty()) {
					brad26Task t = QUEUE.poll(200, TimeUnit.MILLISECONDS);
					if ( t == null) continue;
					
					try {
						t.execute(service);
						System.out.println(t.label());
					}catch(Exception e) {
						System.out.println(e);
					}
				}
			}catch(Exception e) {
				
			}
			
			
		}
	}
	

}