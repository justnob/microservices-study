package com.amarnath.inventoryservice.repository;

import com.amarnath.inventoryservice.module.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
    Optional<List<Inventory>> findBySkuCodeIn(List<String> skuCode);
}
