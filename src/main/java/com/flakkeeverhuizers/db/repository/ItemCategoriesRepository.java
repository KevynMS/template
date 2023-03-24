package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.ItemCategories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemCategoriesRepository extends CrudRepository<ItemCategories, UUID> {


    Optional<ItemCategories> findByName(String name);
}
