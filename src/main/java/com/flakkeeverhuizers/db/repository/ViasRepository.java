package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.Vias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViasRepository extends JpaRepository<Vias, UUID> {
}
