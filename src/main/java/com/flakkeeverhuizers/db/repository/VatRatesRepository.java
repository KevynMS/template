package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.VatRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VatRatesRepository extends JpaRepository<VatRates, UUID> {
}