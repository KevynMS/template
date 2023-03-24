package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.AddressFloorSpaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressFloorSpacesRepository extends JpaRepository<AddressFloorSpaces, UUID> {
}