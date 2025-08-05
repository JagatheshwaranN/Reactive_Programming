package org.learn_everyday.chapter3.stock;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceObserver implements Subscriber<Integer> {

    private static  final Logger log = LoggerFactory.getLogger(StockPriceObserver.class);
    private int quantity = 0;
    private int balance = 1000;
    private Subscription subscription;


    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if(price < 90 && balance >= price) {
            quantity++;
            balance = balance - price;
            log.info("Bought a Stock at {}, Total quantity {}, and Remaining balance {}", price, quantity, balance);
        } else if(price > 110 && quantity > 0) {
            log.info("Selling Stock with {} quantity at price {}", quantity, price);
            balance = balance + quantity * price;
            quantity = 0;
            subscription.cancel();
            log.info("Profit is {}", (balance - 1000));
        }
    }

    @Override
    public void onError(Throwable t) {
        log.error("error ", t);
    }

    @Override
    public void onComplete() {
        log.info("Completed!");
    }

}
