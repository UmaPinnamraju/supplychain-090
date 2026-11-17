package com.uma.supplychain.repository;
import com.uma.supplychain.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {}
