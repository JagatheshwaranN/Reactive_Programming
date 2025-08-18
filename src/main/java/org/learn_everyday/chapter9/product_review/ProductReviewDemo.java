package org.learn_everyday.chapter9.product_review;

import org.learn_everyday.chapter9.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;

public class ProductReviewDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i <= 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());

        }
        Util.sleep(5);
    }

}