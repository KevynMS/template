package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.AddressFloorSpaceBoxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressFloorSpaceBoxesRepository extends JpaRepository<AddressFloorSpaceBoxes, UUID> {
}