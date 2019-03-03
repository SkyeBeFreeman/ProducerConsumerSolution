package com.zhtian;

import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class Producer implements Runnable {

    private Queue<Integer> queue;
    private int size;
    private ProducerComsumerMonitor monitor;

    public Producer(Queue<Integer> queue, int size, ProducerComsumerMonitor monitor) {
        this.queue = queue;
        this.size = size;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
//                produce(i);
                monitor.produce(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == this.size) {
                System.out.println("Queue is full and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                queue.wait();
            }
            queue.add(i);
            System.out.println("Producer produce " + i);
            queue.notifyAll();
        }
    }

}
