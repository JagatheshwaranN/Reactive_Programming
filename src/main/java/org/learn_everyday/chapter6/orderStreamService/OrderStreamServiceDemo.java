package org.learn_everyday.chapter6.orderStreamService;

import org.learn_everyday.reuse.Util;

public class OrderStreamServiceDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream().subscribe(Util.subscriber("Inventory"));
        revenueService.stream().subscribe(Util.subscriber("Revenue"));

        Util.sleep(30);
    }

}
