package com.flakkeeverhuizers.db.repository;


import com.flakkeeverhuizers.db.entity.Addresses;
import com.flakkeeverhuizers.db.entity.BranchAddresses;
import com.flakkeeverhuizers.db.entity.MovingActivities;
import com.flakkeeverhuizers.db.entity.MovingDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovingActivitiesRepository extends JpaRepository<MovingActivities, UUID> {

}