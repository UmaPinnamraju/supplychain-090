package com.uma.supplychain.repository;
import com.uma.supplychain.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepository extends JpaRepository<Supplier,Long> {}
