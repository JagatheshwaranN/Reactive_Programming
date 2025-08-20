package org.learn_everyday.chapter9;

import org.learn_everyday.chapter9.user_order_service.OrderService;
import org.learn_everyday.chapter9.user_order_service.UserService;
import org.learn_everyday.reuse.Util;

public class MonoFlatMapMany {

    public static void main(String[] args) {

        UserService.getUserName(1)
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());
        Util.sleep(3);
    }

}
