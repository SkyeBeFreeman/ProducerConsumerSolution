package com.zhtian;

import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class Consumer implements Runnable {

    private Queue<Integer> queue;
    private int size;
    private ProducerComsumerMonitor monitor;

    public Consumer(Queue<Integer> queue, int size, ProducerComsumerMonitor monitor) {
        this.queue = queue;
        this.size = size;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
//                consume();
                monitor.cosume();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException{
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                queue.wait();
            }
            System.out.println("Consumer consume " + queue.poll());
            queue.notifyAll();
        }
    }
}
