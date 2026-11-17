package com.uma.supplychain.domain;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="shipment", uniqueConstraints=@UniqueConstraint(name="uk_shipment_order",columnNames="order_id"))
public class Shipment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="order_id",nullable=false,unique=true) private CustomerOrder order;
 @Column(nullable=false,length=50) private String trackingNumber;
 @Column(nullable=false,length=80) private String carrier;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private ShipmentStatus status=ShipmentStatus.LABEL_CREATED;
 @Column(nullable=false) private Instant updatedAt=Instant.now();
 protected Shipment(){}
 public Shipment(CustomerOrder order,String trackingNumber,String carrier){this.order=order;this.trackingNumber=trackingNumber;this.carrier=carrier;}
 public Long getId(){return id;} public CustomerOrder getOrder(){return order;} public String getTrackingNumber(){return trackingNumber;}
 public String getCarrier(){return carrier;} public ShipmentStatus getStatus(){return status;} public Instant getUpdatedAt(){return updatedAt;}
 public void setStatus(ShipmentStatus status){this.status=status;this.updatedAt=Instant.now();}
}
