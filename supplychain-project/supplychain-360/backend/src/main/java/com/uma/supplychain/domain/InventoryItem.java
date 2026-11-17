package com.uma.supplychain.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="inventory_item", indexes=@Index(name="ix_inventory_sku", columnList="sku", unique=true))
public class InventoryItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=40) private String sku;
    @Column(nullable=false, length=120) private String name;
    @Column(nullable=false) private int onHand;
    @Column(nullable=false) private int reserved;
    @Column(nullable=false) private int reorderPoint;
    @Column(nullable=false, length=80) private String warehouse;
    private Instant updatedAt = Instant.now();
    protected InventoryItem() {}
    public InventoryItem(String sku,String name,int onHand,int reserved,int reorderPoint,String warehouse) {
        this.sku=sku; this.name=name; this.onHand=onHand; this.reserved=reserved; this.reorderPoint=reorderPoint; this.warehouse=warehouse;
    }
    public Long getId(){return id;} public String getSku(){return sku;} public String getName(){return name;}
    public int getOnHand(){return onHand;} public int getReserved(){return reserved;} public int getAvailable(){return onHand-reserved;}
    public int getReorderPoint(){return reorderPoint;} public String getWarehouse(){return warehouse;} public Instant getUpdatedAt(){return updatedAt;}
    public void reserve(int qty){ if(qty<=0 || getAvailable()<qty) throw new IllegalArgumentException("Insufficient available inventory for "+sku); reserved+=qty; updatedAt=Instant.now(); }
    public void release(int qty){ reserved=Math.max(0,reserved-qty); updatedAt=Instant.now(); }
    public void adjustOnHand(int delta){ if(onHand+delta<reserved) throw new IllegalArgumentException("Adjustment would put on-hand below reserved quantity"); onHand+=delta; updatedAt=Instant.now(); }
}
