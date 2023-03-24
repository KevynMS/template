package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.HouseTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseTypesRepository extends JpaRepository<HouseTypes, UUID> {
}
