package com.inf.pdp.esbrito.pdp.consumer;

import com.google.gson.Gson;
import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
import com.inf.pdp.esbrito.pdp.domain.BenchmarkMessage;

import java.util.concurrent.atomic.AtomicLong;

public abstract class BasicConsumer implements Consumer {

    private final AllConsumedLatch latch;
    private final Gson parser;
    private final String broker;
    private AtomicLong totalDelay = new AtomicLong(0);
    private AtomicLong totalMessages = new AtomicLong(0);

    protected BasicConsumer(Gson parser, AllConsumedLatch latch, String broker) {
        this.parser = parser;
        this.latch = latch;
        this.broker = broker;
    }

    public abstract void consume(String msg);

    @Override
    public void process(String msg) {
        BenchmarkMessage message = parser.fromJson(msg, BenchmarkMessage.class);
        long delay = System.nanoTime() - message.timestamp();
        System.out.println("Delay da mensagem eh de " + delay/1000000.0 + " ms - " + broker);
        totalDelay.addAndGet(delay);
        totalMessages.incrementAndGet();
        System.out.println("Delay medio eh de " + (totalDelay.get()/totalMessages.get())/1000000.0 + " ms - " + broker);
        // Used to count down each message consumed
        latch.countDown();
    }
}
