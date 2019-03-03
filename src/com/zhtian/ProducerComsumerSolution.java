package com.zhtian;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhtian on 2017/3/8.
 */
public class ProducerComsumerSolution {

    public static void main(String[] args) {
        Queue<Integer> shareQueue = new LinkedList<>();
        Thread producerThread = new Thread(new Producer(shareQueue, 4), "Producer");
        Thread consumerThread = new Thread(new Consumer(shareQueue, 4), "Consumer");
        producerThread.start();
        consumerThread.start();
    }

}
