package com.uma.supplychain.service;
import com.uma.supplychain.domain.*;
import com.uma.supplychain.events.OrderCreatedEvent;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.repository.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {
 private final ShipmentRepository shipments; private final OrderRepository orders; private final ShipmentAuditRepository audits;
 public ShipmentService(ShipmentRepository shipments,OrderRepository orders,ShipmentAuditRepository audits){this.shipments=shipments;this.orders=orders;this.audits=audits;}
 @KafkaListener(topics="${app.kafka.order-created-topic}",groupId="supplychain-shipment-service")
 @Transactional
 public void onOrderCreated(OrderCreatedEvent event){
   if(shipments.findByOrder_Id(event.orderId()).isPresent()) return;
   CustomerOrder order=orders.findById(event.orderId()).orElse(null); if(order==null || order.getStatus()==OrderStatus.CANCELLED)return;
   Shipment shipment=shipments.save(new Shipment(order,"SC-"+UUID.randomUUID().toString().substring(0,8).toUpperCase(),"Parcel Partner"));
   audits.save(new ShipmentAudit(shipment.getId(),shipment.getStatus().name(),"Shipment automatically created from order event"));
 }
 @Transactional(readOnly=true) public List<ShipmentResponse> all(){return shipments.findAll().stream().map(this::map).toList();}
 @Transactional public ShipmentResponse update(Long id,ShipmentStatusRequest request){
   Shipment s=shipments.findById(id).orElseThrow(()->new IllegalArgumentException("Shipment not found: "+id));
   s.setStatus(request.status()); audits.save(new ShipmentAudit(s.getId(),request.status().name(),request.notes()));
    // Reserved for future carrier adapter integration.
   if(request.status()==ShipmentStatus.DELIVERED) s.getOrder().setStatus(OrderStatus.DELIVERED);
   else if(request.status()==ShipmentStatus.IN_TRANSIT || request.status()==ShipmentStatus.OUT_FOR_DELIVERY) s.getOrder().setStatus(OrderStatus.SHIPPED);
   return map(s);
 }
 @Transactional(readOnly=true) public List<AuditResponse> audit(Long id){return audits.findByShipmentIdOrderByCreatedAtDesc(id).stream().map(a->new AuditResponse(a.getId(),a.getShipmentId(),a.getStatus(),a.getNotes(),a.getCreatedAt())).toList();}
 private ShipmentResponse map(Shipment s){return new ShipmentResponse(s.getId(),s.getOrder().getId(),s.getOrder().getCustomerName(),s.getTrackingNumber(),s.getCarrier(),s.getStatus().name(),s.getUpdatedAt());}
}
