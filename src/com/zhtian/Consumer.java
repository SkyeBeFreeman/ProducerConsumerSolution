package com.zhtian;

import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class Consumer implements Runnable {

    Queue<Integer> queue;
    int size;

    public Consumer(Queue<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumer consume " + consume());
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int consume() throws InterruptedException{
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                queue.wait();
            }
            queue.notifyAll();
            return queue.poll();
        }
    }
}
