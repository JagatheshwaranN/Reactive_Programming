package org.learn_everyday.chapter3;

import org.learn_everyday.chapter3.client.ExternalServiceClient;
import org.learn_everyday.reuse.Util;

public class FluxNoBlockingIOStream {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getNames().subscribe(Util.subscriber("Subscriber1"));
        client.getNames().subscribe(Util.subscriber("Subscriber2"));
        Util.sleep(6);
    }
}
