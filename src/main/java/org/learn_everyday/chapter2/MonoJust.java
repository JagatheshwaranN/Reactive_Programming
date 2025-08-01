package org.learn_everyday.chapter2;

import org.learn_everyday.chapter1.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class MonoJust {

    public static void main(String[] args) {

        Publisher<String> mono = Mono.just("Hello");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(2);
    }
}
