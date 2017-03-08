package com.zhtian;

import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class Producer implements Runnable {

    Queue<Integer> queue;
    int size;

    public Producer(Queue<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                produce(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == size) {
                System.out.println("Queue is full and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                queue.wait();
            }
            System.out.println("Producer produce " + i + ".");
            queue.add(i);
            queue.notifyAll();
        }
    }

}
