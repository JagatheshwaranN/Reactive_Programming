package org.learn_everyday.reuse;

import org.reactivestreams.Subscriber;

public class Util {

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

}
