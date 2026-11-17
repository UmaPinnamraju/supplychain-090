package com.uma.supplychain.controller;
import com.uma.supplychain.dto.ApiDtos.DashboardResponse;
import com.uma.supplychain.service.DashboardService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
 private final DashboardService service; public DashboardController(DashboardService s){service=s;}
 @GetMapping("/summary") public DashboardResponse summary(){return service.summary();}
}
