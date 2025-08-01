package org.learn_everyday.chapter1.execute;

import org.learn_everyday.chapter1.publisher.PublisherImpl;
import org.learn_everyday.chapter1.subscriber.SubscriberImpl;

import java.time.Duration;

public class PublisherSubscriberDemo {

    public static void main(String[] args) throws InterruptedException {
        noRequestDemo();
        System.out.println("====================================");
        requestDemo();
        System.out.println("====================================");
        maxRequestDemo();
        System.out.println("====================================");
        requestAndCancelDemo();
        System.out.println("====================================");
        requestAndErrorDemo();
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

    private static void maxRequestDemo() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    private static void requestAndCancelDemo() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
    }

    private static void requestAndErrorDemo() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(11);
    }


}
