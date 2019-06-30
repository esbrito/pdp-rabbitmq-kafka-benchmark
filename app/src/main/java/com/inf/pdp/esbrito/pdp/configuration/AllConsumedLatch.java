package com.inf.pdp.esbrito.pdp.configuration;

import java.util.concurrent.CountDownLatch;

public class AllConsumedLatch {

    private CountDownLatch latch;

    public void init(int size) {
        this.latch = new CountDownLatch(size);
    }

    public void countDown() {
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }

}
