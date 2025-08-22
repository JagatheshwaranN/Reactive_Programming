package org.learn_everyday.chapter10.purchase_order_service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class OrderProcessingService {

    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP =
            Map.of("Kids", kidsProcessing(), "Automotive", automativeProcessing());

    private static UnaryOperator<Flux<PurchaseOrder>> automativeProcessing() {
        return purchaseOrderFlux ->
                purchaseOrderFlux.map(purchaseOrder ->
                        new PurchaseOrder(purchaseOrder.item(), purchaseOrder.category(), purchaseOrder.price() + 100));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {
        return purchaseOrderFlux ->
                purchaseOrderFlux.flatMap(purchaseOrder ->
                        getFreeKidsOrder(purchaseOrder).flux().startWith(purchaseOrder));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder purchaseOrder) {
        return Mono.fromSupplier(() ->
                new PurchaseOrder(purchaseOrder.item() + "-FREE", purchaseOrder.category(), 0)
        );
    }

    public static Predicate<PurchaseOrder> canProcess() {
        return purchaseOrder -> PROCESSOR_MAP.containsKey(purchaseOrder.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return PROCESSOR_MAP.get(category);
    }
}
