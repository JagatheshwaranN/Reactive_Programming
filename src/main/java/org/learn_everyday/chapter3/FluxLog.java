package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxLog {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .log()
                .subscribe(Util.subscriber());
    }

}
