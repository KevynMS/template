package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.AddressFloorSpaceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressFloorSpaceItemsRepository extends JpaRepository<AddressFloorSpaceItems, UUID> {
}