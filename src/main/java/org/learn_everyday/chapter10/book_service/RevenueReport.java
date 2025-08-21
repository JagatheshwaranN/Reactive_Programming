package org.learn_everyday.chapter10.book_service;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime time, Map<String, Integer> revenue) {

}
