package com.example.jpa.q3.repo;

import com.example.jpa.q3.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
