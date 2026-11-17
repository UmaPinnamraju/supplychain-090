package com.uma.supplychain.domain;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="shipment_audit")
public class ShipmentAudit {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long shipmentId;
 @Column(nullable=false,length=30) private String status;
 @Column(length=500) private String notes;
 @Column(nullable=false) private Instant createdAt=Instant.now();
 protected ShipmentAudit(){}
 public ShipmentAudit(Long shipmentId,String status,String notes){this.shipmentId=shipmentId;this.status=status;this.notes=notes;}
 public Long getId(){return id;} public Long getShipmentId(){return shipmentId;} public String getStatus(){return status;}
 public String getNotes(){return notes;} public Instant getCreatedAt(){return createdAt;}
}
