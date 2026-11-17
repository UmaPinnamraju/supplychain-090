package com.uma.supplychain.controller;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/suppliers")
public class SupplierController {
 private final SupplierService service; public SupplierController(SupplierService s){service=s;}
 @GetMapping public List<SupplierResponse> all(){return service.all();}
 @PostMapping @PreAuthorize("hasRole('ADMIN')") public SupplierResponse create(@Valid @RequestBody SupplierRequest r){return service.create(r);}
}
