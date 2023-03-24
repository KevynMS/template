package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.Relocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelocationsRepository extends JpaRepository<Relocations, UUID> {
}