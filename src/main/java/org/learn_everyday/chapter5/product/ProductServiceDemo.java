package org.learn_everyday.chapter5.product;

import org.learn_everyday.reuse.Util;

public class ProductServiceDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for(int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleep(3);
    }
}
