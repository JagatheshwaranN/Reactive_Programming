package org.learn_everyday.chapter9;

import org.learn_everyday.chapter9.user_order_service.OrderService;
import org.learn_everyday.chapter9.user_order_service.User;
import org.learn_everyday.chapter9.user_order_service.UserService;
import org.learn_everyday.reuse.Util;

public class FluxFlatMap {

    public static void main(String[] args) {

        UserService.getAllUsers()
                .map(User::username)
                .flatMap(OrderService::getUserOrders, 1)
                .subscribe(Util.subscriber());
        Util.sleep(5);

    }

}
