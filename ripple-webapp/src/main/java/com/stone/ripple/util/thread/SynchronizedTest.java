package com.stone.ripple.util.thread;

/** 
  * @description
  * @author   stone
  * @date     2017年7月2日
  */
public class SynchronizedTest implements Runnable{

	@Override
	public void run() {
		try {
			new DemoClass().doSomeThing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		for(int i=0;i<5;i++){
			Thread t = new Thread(new SynchronizedTest());
			t.start();
		}
	}

}
