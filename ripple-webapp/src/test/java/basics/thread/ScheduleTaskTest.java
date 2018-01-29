package basics.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author stone
 * @date 2017年12月1日
 */
public class ScheduleTaskTest {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("middleware-statistics-reporter");
				thread.setDaemon(true);
				return thread;
			}
		});
		scheduler.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("====1");
			}
		}, 3, 1, TimeUnit.SECONDS);
		Thread.sleep(100000);
	}

}
