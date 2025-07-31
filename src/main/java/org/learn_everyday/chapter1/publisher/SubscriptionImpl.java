package org.learn_everyday.chapter1.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Faker faker;
    private Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private static final int MAX_ITEM = 10;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if(isCancelled) {
            return;
        }
        log.info("Subscriber has requested {} items", requested);
        for(int i = 0; i < requested && count < MAX_ITEM; i++) {
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if(count == MAX_ITEM) {
            log.info("No more items to produce.");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled");
        this.isCancelled = true;
    }

}
