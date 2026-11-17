package com.uma.supplychain.service;
import com.uma.supplychain.domain.Supplier;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SupplierService {
 private final SupplierRepository repo;
 public SupplierService(SupplierRepository repo){this.repo=repo;}
 public List<SupplierResponse> all(){return repo.findAll().stream().map(this::map).toList();}
 public SupplierResponse create(SupplierRequest r){return map(repo.save(new Supplier(r.code(),r.name(),r.contactEmail(),r.leadTime())));}
 private SupplierResponse map(Supplier s){return new SupplierResponse(s.getId(),s.getCode(),s.getName(),s.getContactEmail(),s.getLeadTime(),s.isActive());}
}
