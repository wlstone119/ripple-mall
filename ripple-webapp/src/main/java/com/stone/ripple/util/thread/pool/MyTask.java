package com.stone.ripple.util.thread.pool;

public class MyTask implements Runnable {

	public int count = 0;

	public void run() {
		System.out.println("正在执行任务:" + Thread.currentThread().getName());
		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("执行中：" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		count++;
		System.out.println("执行完毕:" + Thread.currentThread().getName());
	}

}
