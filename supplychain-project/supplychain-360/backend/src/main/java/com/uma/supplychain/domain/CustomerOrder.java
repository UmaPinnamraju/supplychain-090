package com.uma.supplychain.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="customer_order", indexes=@Index(name="ix_order_created",columnList="createdAt"))
public class CustomerOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=140) private String customerName;
 @Column(nullable=false,length=180) private String customerEmail;
 @Column(nullable=false,length=300) private String shippingAddress;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private OrderStatus status=OrderStatus.CREATED;
 @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
 private List<OrderLine> lines=new ArrayList<>();
 protected CustomerOrder(){}
 public CustomerOrder(String customerName,String customerEmail,String shippingAddress){this.customerName=customerName;this.customerEmail=customerEmail;this.shippingAddress=shippingAddress;}
 public void addLine(OrderLine line){lines.add(line);line.setOrder(this);}
 public Long getId(){return id;} public String getCustomerName(){return customerName;} public String getCustomerEmail(){return customerEmail;}
 public String getShippingAddress(){return shippingAddress;} public OrderStatus getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
 public List<OrderLine> getLines(){return lines;} public void setStatus(OrderStatus status){this.status=status;}
}
