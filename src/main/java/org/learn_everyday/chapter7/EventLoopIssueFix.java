package org.learn_everyday.chapter7;

import org.learn_everyday.chapter7.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(EventLoopIssueFix.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        log.info("Starting..");
        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }
        Util.sleep(10);
    }

    private static String process(String input) {
        Util.sleep(1);
        return input+" - Processed";
    }

}
