package com.uma.supplychain.controller;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/shipments")
public class ShipmentController {
 private final ShipmentService service; public ShipmentController(ShipmentService s){service=s;}
 @GetMapping public List<ShipmentResponse> all(){return service.all();}
 @PatchMapping("/{id}/status") @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')") public ShipmentResponse update(@PathVariable Long id,@Valid @RequestBody ShipmentStatusRequest r){return service.update(id,r);}
 @GetMapping("/{id}/audit") public List<AuditResponse> audit(@PathVariable Long id){return service.audit(id);}
}
