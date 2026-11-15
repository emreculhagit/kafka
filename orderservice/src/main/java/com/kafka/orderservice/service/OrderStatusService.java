package com.kafka.orderservice.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class OrderStatusService {

    private final Map<Long, String> orderStatuses = new ConcurrentHashMap<>();

    public void addOrder(Long orderId) {
        orderStatuses.put(orderId, "PENDING");
    }

    public void updateStatus(Long orderId, String status) {
        orderStatuses.put(orderId, status);
    }

    public String getStatus(Long orderId) {
        return orderStatuses.get(orderId);
    }
}