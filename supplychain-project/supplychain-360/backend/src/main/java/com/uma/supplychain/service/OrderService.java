package com.uma.supplychain.service;

import com.uma.supplychain.domain.*;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.events.OrderCreatedEvent;
import com.uma.supplychain.repository.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
 private final OrderRepository orders; private final InventoryRepository inventory; private final ApplicationEventPublisher events;
 public OrderService(OrderRepository orders,InventoryRepository inventory,ApplicationEventPublisher events){this.orders=orders;this.inventory=inventory;this.events=events;}
 @Transactional
 public OrderResponse create(CreateOrderRequest request){
   CustomerOrder order=new CustomerOrder(request.customerName(),request.customerEmail(),request.shippingAddress());
   for(OrderLineRequest line:request.lines()){
     InventoryItem item=inventory.findBySkuForUpdate(line.sku()).orElseThrow(()->new IllegalArgumentException("Unknown SKU: "+line.sku()));
     item.reserve(line.quantity());
     order.addLine(new OrderLine(line.sku(),line.quantity()));
   }
   CustomerOrder saved=orders.save(order);
   events.publishEvent(new OrderCreatedEvent(saved.getId(),saved.getCustomerName(),Instant.now()));
   return toResponse(saved);
 }
 @Transactional(readOnly=true) public List<OrderResponse> findAll(){return orders.findAll().stream().map(this::toResponse).toList();}
 @Transactional(readOnly=true) public OrderResponse find(Long id){return toResponse(orders.findById(id).orElseThrow(()->new IllegalArgumentException("Order not found: "+id)));}
 @Transactional public void cancel(Long id){
   CustomerOrder order=orders.findById(id).orElseThrow(()->new IllegalArgumentException("Order not found: "+id));
   if(order.getStatus()==OrderStatus.SHIPPED || order.getStatus()==OrderStatus.DELIVERED) throw new IllegalStateException("Shipped orders cannot be cancelled");
   if(order.getStatus()==OrderStatus.CANCELLED) return;
   for(OrderLine line:order.getLines()) inventory.findBySkuForUpdate(line.getSku()).ifPresent(i->i.release(line.getQuantity()));
   order.setStatus(OrderStatus.CANCELLED);
 }
 private OrderResponse toResponse(CustomerOrder o){return new OrderResponse(o.getId(),o.getCustomerName(),o.getCustomerEmail(),o.getShippingAddress(),o.getStatus().name(),o.getCreatedAt(),o.getLines().stream().map(l->new OrderLineResponse(l.getSku(),l.getQuantity())).toList());}
}
