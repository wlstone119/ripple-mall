package com.stone.ripple.util.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description
 * @author stone
 * @date 2017年5月14日
 */
class Runner implements Runnable {
	// 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
	private CyclicBarrier barrier;

	private String name;

	public Runner(CyclicBarrier barrier, String name) {
		super();
		this.barrier = barrier;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * (new Random()).nextInt(8));
			System.out.println(name + " 准备好了...");

			// barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
			if (barrier != null) {
				barrier.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(name + " 起跑！");
	}
}
