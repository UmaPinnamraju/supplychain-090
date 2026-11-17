package com.uma.supplychain.controller;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/inventory")
public class InventoryController {
 private final InventoryService service; public InventoryController(InventoryService s){service=s;}
 @GetMapping public List<InventoryResponse> all(){return service.all();}
 @PatchMapping("/{id}/adjust") @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')") public InventoryResponse adjust(@PathVariable Long id,@Valid @RequestBody AdjustInventoryRequest r){return service.adjust(id,r.delta());}
}
