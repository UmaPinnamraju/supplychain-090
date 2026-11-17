package com.uma.supplychain.domain;
import jakarta.persistence.*;
@Entity @Table(name="order_line")
public class OrderLine {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private CustomerOrder order;
 @Column(nullable=false,length=40) private String sku;
 @Column(nullable=false) private int quantity;
 protected OrderLine(){}
 public OrderLine(String sku,int quantity){this.sku=sku;this.quantity=quantity;}
 void setOrder(CustomerOrder order){this.order=order;}
 public Long getId(){return id;} public String getSku(){return sku;} public int getQuantity(){return quantity;}
}
