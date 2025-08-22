package org.learn_everyday.chapter10.purchase_order_service;

import org.learn_everyday.reuse.Util;

public record PurchaseOrder(String item, String category, Integer price) {

    public static PurchaseOrder create() {
        var commerce = Util.fake().commerce();
        return new PurchaseOrder(
                commerce.productName(),
                commerce.department(),
                Util.fake().random().nextInt(10, 100));
    }

}


