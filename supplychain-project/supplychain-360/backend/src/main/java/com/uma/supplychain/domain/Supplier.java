package com.uma.supplychain.domain;
import jakarta.persistence.*;
@Entity @Table(name="supplier")
public class Supplier {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=30) private String code;
 @Column(nullable=false,length=140) private String name;
 @Column(length=160) private String contactEmail;
 @Column(length=80) private String leadTime;
 @Column(nullable=false) private boolean active=true;
 protected Supplier(){}
 public Supplier(String code,String name,String contactEmail,String leadTime){this.code=code;this.name=name;this.contactEmail=contactEmail;this.leadTime=leadTime;}
 public Long getId(){return id;} public String getCode(){return code;} public String getName(){return name;}
 public String getContactEmail(){return contactEmail;} public String getLeadTime(){return leadTime;} public boolean isActive(){return active;}
}
