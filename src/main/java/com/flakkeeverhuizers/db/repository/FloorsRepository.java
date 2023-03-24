package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.Floors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FloorsRepository extends JpaRepository<Floors, UUID> {
}
