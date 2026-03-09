package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        OrderService fastPayService = new OrderService(gateways.get("fastpay"));
        String id1 = fastPayService.charge("cust-1", 1299);

        OrderService safeCashService = new OrderService(gateways.get("safecash"));
        String id2 = safeCashService.charge("cust-2", 1299);

        System.out.println(id1);
        System.out.println(id2);
    }
}
