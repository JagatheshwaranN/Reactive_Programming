package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Transform {

    private static final Logger log = LoggerFactory.getLogger(Transform.class);

    record Customer(int id, String name) {
    };

    record PurchaseOrder(String productName, int quantity, int price) {
    };

    public static void main(String[] args) {

        var isHandlerEnabled = true;
        getCustomer()
                .transform(isHandlerEnabled ? handler() : Function.identity())
                .subscribe();

        getPurchaseOrder()
                .transform(handler())
                .subscribe();
    }

    private static Flux<Customer> getCustomer() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.fake().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrder() {
        return Flux.range(1, 3)
                .map(i -> new PurchaseOrder(Util.fake().commerce().productName(), i, i * 10));
    }

    private static <T> UnaryOperator<Flux<T>> handler() {
        return tFlux -> tFlux
                .doOnNext(i -> log.info("Received: {}", i))
                .doOnComplete(() -> log.info("Completed!"))
                .doOnError(err -> log.error("Error: ", err));
    }

}
