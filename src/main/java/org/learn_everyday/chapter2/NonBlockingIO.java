package org.learn_everyday.chapter2;

import org.learn_everyday.chapter2.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("Starting..");
        for(int i = 1; i <=5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleep(2);
    }


}
