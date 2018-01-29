package com.stone.ripple.util.thread;

/**
 * @description
 * @author stone
 * @date 2017年7月2日
 */
public class DemoClass {

	public void doSomeThing() throws InterruptedException {
		synchronized (DemoClass.class) {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "--" + i);
			}
		}
	}

}
