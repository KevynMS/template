package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.LiftOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LiftOptionsRepository extends JpaRepository<LiftOptions, UUID> {
}
