package org.learn_everyday.chapter11.client;

public class ClientError extends RuntimeException {

    public ClientError() {
        super("Bad Request");
    }
}
