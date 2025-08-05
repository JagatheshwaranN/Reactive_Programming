package org.learn_everyday.chapter3.stock;

import org.learn_everyday.chapter3.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;

public class StockPriceObserverDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();
        client.getPriceChange().subscribe(subscriber);
        Util.sleep(20);
    }
}
