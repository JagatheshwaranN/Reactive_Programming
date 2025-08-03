package org.learn_everyday.chapter2;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(MonoFromRunnable.class);

    public static void main(String[] args) {
        for(int i = 1; i < 3; i++) {
            getProductName(i)
                    .subscribe(Util.subscriber());
        }
    }


   private static Mono<String> getProductName(int productId) {
        if(productId == 1) {
            return Mono.fromSupplier(() -> Util.fake().commerce().productName());
        }
        return Mono.fromRunnable(() -> notify(productId));
   }

   private static void notify(int productId) {
        log.info("Notifying business on unavailable product {}", productId);
   }
}
