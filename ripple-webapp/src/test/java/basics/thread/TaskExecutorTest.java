/**
 *
 * Copyright (C), 2011-2016, 微贷网.
 */
package basics.thread;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author stone 2017年12月4日.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "spring-thread-pool.xml" })
public class TaskExecutorTest {

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	@Test
	public void testWorkQueue() {
		for (int i = 0; i < 2000; i++) {
			System.out.println(taskExecutor.getThreadPoolExecutor().getQueue().toString());
			taskExecutor.execute(new Thread() {
				public void run() {
					System.out.println("=======" + taskExecutor.getActiveCount() + "======="
							+ taskExecutor.getThreadPoolExecutor().getQueue().size());
				}
				
				public String toString(){
					return getClass().getName() + "@" + Integer.toHexString(hashCode());
				}
			});
		}
	}

}
