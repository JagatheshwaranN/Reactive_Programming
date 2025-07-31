package org.learn_everyday.chapter1.execute;

import org.learn_everyday.chapter1.publisher.PublisherImpl;
import org.learn_everyday.chapter1.subscriber.SubscriberImpl;

public class PublisherSubscriberDemo {

    public static void main(String[] args) {
        noRequestDemo();
        requestDemo();
    }

    private static void noRequestDemo() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void requestDemo() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
    }


}
