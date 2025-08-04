package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxJust {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, "Test")
                .subscribe(Util.subscriber());
    }

}
