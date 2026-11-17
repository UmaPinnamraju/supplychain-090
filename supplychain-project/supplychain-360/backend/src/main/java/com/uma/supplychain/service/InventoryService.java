package com.uma.supplychain.service;
import com.uma.supplychain.domain.InventoryItem;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class InventoryService {
 private final InventoryRepository repo;
 public InventoryService(InventoryRepository repo){this.repo=repo;}
 @Transactional(readOnly=true) public List<InventoryResponse> all(){return repo.findAll().stream().map(this::map).toList();}
 @Transactional public InventoryResponse adjust(Long id,int delta){
   InventoryItem item=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Inventory item not found: "+id));
   item.adjustOnHand(delta); return map(item);
 }
 private InventoryResponse map(InventoryItem i){return new InventoryResponse(i.getId(),i.getSku(),i.getName(),i.getOnHand(),i.getReserved(),i.getAvailable(),i.getReorderPoint(),i.getWarehouse(),i.getAvailable()<=i.getReorderPoint());}
}
