package com.uma.supplychain.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.List;

public class ApiDtos {
 public record LoginRequest(@NotBlank String username,@NotBlank String password) {}
 public record LoginResponse(String token,String username,String role) {}
 public record OrderLineRequest(@NotBlank String sku,@Min(1) int quantity) {}
 public record CreateOrderRequest(@NotBlank String customerName,@Email @NotBlank String customerEmail,
                                  @NotBlank String shippingAddress,@NotEmpty List<@NotNull OrderLineRequest> lines) {}
 public record OrderLineResponse(String sku,int quantity) {}
 public record OrderResponse(Long id,String customerName,String customerEmail,String shippingAddress,
                             String status,Instant createdAt,List<OrderLineResponse> lines) {}
 public record InventoryResponse(Long id,String sku,String name,int onHand,int reserved,int available,
                                 int reorderPoint,String warehouse,boolean lowStock) {}
 public record AdjustInventoryRequest(@NotNull Integer delta) {}
 public record SupplierRequest(@NotBlank String code,@NotBlank String name,String contactEmail,String leadTime) {}
 public record SupplierResponse(Long id,String code,String name,String contactEmail,String leadTime,boolean active) {}
 public record ShipmentStatusRequest(@NotNull com.uma.supplychain.domain.ShipmentStatus status,String notes) {}
 public record ShipmentResponse(Long id,Long orderId,String customerName,String trackingNumber,String carrier,
                                String status,Instant updatedAt) {}
 public record AuditResponse(Long id,Long shipmentId,String status,String notes,Instant createdAt) {}
 public record DashboardResponse(long openOrders,long lowStockSkus,long activeShipments,long deliveredShipments) {}
}
