package com.uma.supplychain.service;
import com.uma.supplychain.domain.*;
import com.uma.supplychain.dto.ApiDtos.DashboardResponse;
import com.uma.supplychain.repository.*;
import org.springframework.stereotype.Service;
@Service
public class DashboardService {
 private final OrderRepository orders; private final InventoryRepository inventory; private final ShipmentRepository shipments;
 public DashboardService(OrderRepository o,InventoryRepository i,ShipmentRepository s){orders=o;inventory=i;shipments=s;}
 public DashboardResponse summary(){
  var allOrders=orders.findAll(); var allItems=inventory.findAll(); var allShipments=shipments.findAll();
  long open=allOrders.stream().filter(o->o.getStatus()!=OrderStatus.DELIVERED&&o.getStatus()!=OrderStatus.CANCELLED).count();
  long low=allItems.stream().filter(i->i.getAvailable()<=i.getReorderPoint()).count();
  long delivered=allShipments.stream().filter(s->s.getStatus()==ShipmentStatus.DELIVERED).count();
  return new DashboardResponse(open,low,allShipments.size()-delivered,delivered);
 }
}
