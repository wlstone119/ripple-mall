package com.stone.ripple.util.thread.cyclicbarrier;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @description
 * @author stone
 * @date 2017年5月14日
 */
public class CommonThread {
	public static void main(String[] args) throws IOException, InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Thread(new Runner(null, "1号选手")));
		executor.submit(new Thread(new Runner(null, "2号选手")));
		executor.submit(new Thread(new Runner(null, "3号选手")));

		executor.shutdown();
	}
}
