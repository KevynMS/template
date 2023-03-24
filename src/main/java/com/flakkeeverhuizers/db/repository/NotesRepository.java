package com.flakkeeverhuizers.db.repository; 


import com.flakkeeverhuizers.db.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotesRepository extends JpaRepository<Notes, UUID> {
}