package com.flakkeeverhuizers.db.repository;

import com.flakkeeverhuizers.db.entity.Spaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpacesRepository extends JpaRepository<Spaces, UUID> {

}
