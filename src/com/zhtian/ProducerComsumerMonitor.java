package com.zhtian;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Skye on 2019/3/3.
 * 管程实现
 */
public class ProducerComsumerMonitor {

    private Queue<Integer> queue;

    private int size;

    public ProducerComsumerMonitor(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public void produce(int i) throws InterruptedException {
        synchronized (ProducerComsumerMonitor.class) {
            while (queue.size() == this.size) {
                System.out.println("Queue is full and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                ProducerComsumerMonitor.class.wait();
            }
            queue.add(i);
            System.out.println("Producer produce " + i);
            ProducerComsumerMonitor.class.notifyAll();
        }
    }

    public void cosume() throws InterruptedException {
        synchronized (ProducerComsumerMonitor.class) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty and its size is " + queue.size() + ". " + Thread.currentThread().getName() + " is waiting.");
                ProducerComsumerMonitor.class.wait();
            }
            System.out.println("Consumer consume " + queue.poll());
            ProducerComsumerMonitor.class.notifyAll();
        }
    }

}
