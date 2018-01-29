package basics.thread;

import java.util.HashMap;

import com.stone.ripple.util.tool.StringUtil;

/**
 * @description
 * @author stone
 * @date 2017年4月23日
 */
public class ThreadLocalTest {
	// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
	private static ThreadLocal<HashMap<String, Integer>> seqNum = new ThreadLocal<HashMap<String, Integer>>() {
		public HashMap<String, Integer> initialValue() {
			return new HashMap<String, Integer>();
		}
	};

	// ②获取下一个序列值
	public void getNextNum(Integer var) {
		seqNum.get().put(var + "", var);
		System.out.println(
				"thread[" + Thread.currentThread().getName() + "] --> sn[" + StringUtil.stringify(seqNum.get()) + "]");
	}

	public static void main(String[] args) {
		ThreadLocalTest sn = new ThreadLocalTest();
		// ③ 3个线程共享sn，各自产生序列号
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		t1.start();
		t2.start();
		t3.start();
	}

	private static class TestClient extends Thread {
		private ThreadLocalTest sn;

		public TestClient(ThreadLocalTest sn) {
			this.sn = sn;
		}

		public void run() {
			for (int i = 0; i < 3; i++) {
				// ④每个线程打出3个序列值
				sn.getNextNum(i);
			}
		}
	}
}
