package com.uma.supplychain.controller;
import com.uma.supplychain.dto.ApiDtos.*;
import com.uma.supplychain.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final OrderService service; public OrderController(OrderService s){service=s;}
 @GetMapping public List<OrderResponse> all(){return service.findAll();}
 @GetMapping("/{id}") public OrderResponse one(@PathVariable Long id){return service.find(id);}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')") public OrderResponse create(@Valid @RequestBody CreateOrderRequest r){return service.create(r);}
 @PostMapping("/{id}/cancel") @PreAuthorize("hasAnyRole('ADMIN','OPERATOR')") public void cancel(@PathVariable Long id){service.cancel(id);}
}
