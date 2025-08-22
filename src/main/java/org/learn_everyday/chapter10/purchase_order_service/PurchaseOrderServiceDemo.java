package org.learn_everyday.chapter10.purchase_order_service;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class PurchaseOrderServiceDemo {

    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(poGroupedFlux ->
                        poGroupedFlux.transform(OrderProcessingService.getProcessor(poGroupedFlux.key())))
                .subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }

}

