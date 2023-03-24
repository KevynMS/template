package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.AddressTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressTypesRepository extends JpaRepository<AddressTypes, UUID> {
}
