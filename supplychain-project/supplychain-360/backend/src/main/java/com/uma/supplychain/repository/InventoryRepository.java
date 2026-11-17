package com.uma.supplychain.repository;
import com.uma.supplychain.domain.InventoryItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface InventoryRepository extends JpaRepository<InventoryItem,Long> {
 Optional<InventoryItem> findBySku(String sku);
 @Lock(LockModeType.PESSIMISTIC_WRITE)
 @Query("select i from InventoryItem i where i.sku=:sku")
 Optional<InventoryItem> findBySkuForUpdate(@Param("sku") String sku);
}
