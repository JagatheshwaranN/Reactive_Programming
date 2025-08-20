package org.learn_everyday.chapter9;

import org.learn_everyday.chapter9.user_order_service.PaymentService;
import org.learn_everyday.chapter9.user_order_service.UserService;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Mono;

public class MonoFlatMap {

    public static void main(String[] args) {

        UserService.getUserName(1)
                .flatMap(userName -> Mono.fromSupplier(() -> "Hello "+userName))
                .subscribe(Util.subscriber());

        UserService.getUserName(1)
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());

    }

}
