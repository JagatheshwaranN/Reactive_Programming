package org.learn_everyday.chapter9.user_order_service;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserInformation {

    record UserInformationDetail(Integer userId, String username, Integer balance, List<Order> orders) {

    }

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(UserInformation::getUserInformation)
                .subscribe(Util.subscriber());
        Util.sleep(5);
    }

    private static Mono<UserInformationDetail> getUserInformation(User user) {
        return Mono.zip(PaymentService.getUserBalance(user.username()),
                        OrderService.getUserOrders(user.username()).collectList())
                .map(tuple -> new UserInformationDetail(user.id(), user.username(), tuple.getT1(), tuple.getT2()));
    }

}
