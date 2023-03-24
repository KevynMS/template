package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.RelocationLabels;
import com.flakkeeverhuizers.db.entity.RelocationLabelsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelocationLabelsRepository extends JpaRepository<RelocationLabels, RelocationLabelsKey> {
}