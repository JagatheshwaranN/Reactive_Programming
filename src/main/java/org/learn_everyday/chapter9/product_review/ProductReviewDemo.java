package org.learn_everyday.chapter9.product_review;

import org.learn_everyday.chapter9.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class ProductReviewDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

//        usingNormalApproach(client);
        usingConcatMap(client);
//        usingFlatMap(client);
    }

    private static void usingConcatMap(ExternalServiceClient client) {
        Flux.range(1, 10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());
        Util.sleep(20);
    }

    private static void usingFlatMap(ExternalServiceClient client) {
        Flux.range(1, 10)
                .flatMap(client::getProduct, 3)
                .subscribe(Util.subscriber());
        Util.sleep(5);
    }

    private static void usingNormalApproach(ExternalServiceClient client) {
        for (int i = 1; i <= 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());

        }
        Util.sleep(5);
    }

}