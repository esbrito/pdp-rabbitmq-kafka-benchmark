package com.inf.pdp.esbrito.pdp.configuration;

import java.util.concurrent.CountDownLatch;

public class AllConsumedLatch {

    private CountDownLatch latch;
    private Long firstMessageTime;

    public void init(int size) {
        this.latch = new CountDownLatch(size);
    }

    public void countDown() {
        if (firstMessageTime == null){
            firstMessageTime = System.nanoTime();
        }
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public Long firstConsumedMessageTime() {
        return firstMessageTime;
    }
}
