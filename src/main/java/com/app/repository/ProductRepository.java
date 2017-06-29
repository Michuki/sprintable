package com.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Item;

@Repository
public interface ProductRepository extends JpaRepository<Item, Long>{
}
