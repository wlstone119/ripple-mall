package basics.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.fastjson.JSON;

public class WaitNotify {

    public static final int max_size = 100;

    Queue<Integer>          queue    = new ConcurrentLinkedQueue<Integer>();

    class MyProducer extends Thread {

        public void run() {
            // 生产线程
            synchronized (queue) {
                // 空闲则生成元素 ， 并通知消费者线程
                do {
                    if (queue.size() == max_size) {
                        try {
                            // 挂起当前线程， 并释放通过同步块获取的 queue上的锁，，上消费者线程可以获取该锁，然后 获取队列里面的元素
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        queue.add(1);
                        queue.notifyAll();
                        System.out.println("Producer==>" + JSON.toJSONString(queue));
                    }
                } while (queue.size() <= max_size);
            }
        }
    }

    class MyConsumer extends Thread {

        public void run() {

            // 消费者线程
            synchronized (queue) {

                // 消费元素 ，并通知唤醒生产者线程
                do {
                    if (queue.size() == 0) {
                        try {
                            // 挂起当前线程，并释放通过同步块获取的 queue上的锁， 上生产者线程可以获取该锁，将生产元素放入队列
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        queue.poll();
                        queue.notifyAll();
                        System.out.println("Consumer==>" + JSON.toJSONString(queue) + "===>" + queue.size());
                    }
                } while (queue.size() >= 0);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        WaitNotify notify = new WaitNotify();
        
        Thread t1 = notify.new MyProducer();
        t1.start();
        
        Thread t2 = notify.new MyConsumer();
        t2.start();
        
    }

}
