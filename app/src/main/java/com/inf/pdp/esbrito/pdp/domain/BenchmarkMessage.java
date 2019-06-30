package com.inf.pdp.esbrito.pdp.domain;

public class BenchmarkMessage {

    private final Long timestamp;
    private final String message;

    public BenchmarkMessage(int messageByteSize) {
        this.message = createMessage(messageByteSize);
        // Timestamp is set after message is built so it does not count as part of the delay for sending and consuming message
        this.timestamp = System.nanoTime();
    }

    public Long timestamp() {
        return timestamp;
    }

    private String createMessage(int messageByteSize) {
        // Creates message with exact byte size
        byte[] array = new byte[messageByteSize];
        return new String(array);
    }
}
