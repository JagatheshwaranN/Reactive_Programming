package org.learn_everyday.chapter11;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Mono;

public class Repeat {

    public static void main(String[] args) {

        var mono = Mono.fromSupplier(() -> Util.fake().country().name());
        var subscriber = Util.subscriber();
        mono.repeat(5).subscribe(subscriber);
    }

}
