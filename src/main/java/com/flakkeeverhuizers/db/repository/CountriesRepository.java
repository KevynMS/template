package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountriesRepository extends JpaRepository<Countries, UUID> {
}
