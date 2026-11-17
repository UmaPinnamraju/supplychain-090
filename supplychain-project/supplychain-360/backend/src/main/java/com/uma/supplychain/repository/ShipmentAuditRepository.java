package com.uma.supplychain.repository;
import com.uma.supplychain.domain.ShipmentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ShipmentAuditRepository extends JpaRepository<ShipmentAudit,Long> {
 List<ShipmentAudit> findByShipmentIdOrderByCreatedAtDesc(Long shipmentId);
}
