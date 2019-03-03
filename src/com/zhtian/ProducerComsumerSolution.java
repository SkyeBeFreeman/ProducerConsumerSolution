package com.zhtian;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class ProducerComsumerSolution {

    public static void main(String[] args) {
        Queue<Integer> shareQueue = new LinkedList<>();
        ProducerComsumerMonitor monitor = new ProducerComsumerMonitor(4);
        Thread producerThread = new Thread(new Producer(shareQueue, 4, monitor), "Producer");
        Thread consumerThread = new Thread(new Consumer(shareQueue, 4, monitor), "Consumer");
        producerThread.start();
        consumerThread.start();
    }

}
