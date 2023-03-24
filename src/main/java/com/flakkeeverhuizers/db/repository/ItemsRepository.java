package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.ItemCategories;
import com.flakkeeverhuizers.db.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Items, UUID> {

    Optional<Items> findByNameAndItemCategories(String name, ItemCategories itemCategories);
}
