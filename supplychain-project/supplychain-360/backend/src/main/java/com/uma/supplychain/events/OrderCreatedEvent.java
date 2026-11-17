package com.uma.supplychain.events;
import java.time.Instant;
public record OrderCreatedEvent(Long orderId,String customerName,Instant occurredAt) {}
