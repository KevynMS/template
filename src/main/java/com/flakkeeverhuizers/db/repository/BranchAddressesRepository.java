package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.BranchAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchAddressesRepository extends JpaRepository<BranchAddresses, Integer> {
}