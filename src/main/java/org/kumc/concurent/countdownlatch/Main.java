package org.kumc.concurent.countdownlatch;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CountDownLatch;


public class Main {
	private static final int TASKS = 10;

	/**
	 * 10개의 작업이 끝나면 service를 종료시킨다. 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("BEGIN");
		
		// Pool를 사용해서 메모리사용량을 조절하고 있음. 
		ExecutorService service = Executors.newFixedThreadPool(5);
		CountDownLatch doneLatch = new CountDownLatch(TASKS);

		try {
			for (int i = 0; i < TASKS; i++) {
				service.execute(new MyTask(doneLatch, i));
			}
			System.out.println("AWAIT");

			//진행이 멈추었다가 doneLatch.getCount() 값이 0이 될때 다시 시작 
			doneLatch.await();
			
		} catch (InterruptedException e) {

		} finally {
			service.shutdown();
			System.out.println("END");
		}
	}
}
