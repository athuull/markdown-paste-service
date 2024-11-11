package com.athuull.markdown_paste_service.Repository;

import com.athuull.markdown_paste_service.Entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasteRepository extends JpaRepository<Paste , String> {

    // Spring Data JPA knows how to find uniqueID automatically!
    Optional<Paste> findByUniqueID(String uniqueID);
}
