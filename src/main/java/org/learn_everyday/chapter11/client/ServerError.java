package org.learn_everyday.chapter11.client;

public class ServerError extends RuntimeException {

    public ServerError() {
        super("Server Error");
    }
}
