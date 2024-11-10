package com.athuull.markdown_paste_service.Repository;

import com.athuull.markdown_paste_service.Entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasteRepository extends JpaRepository<Paste , Long> {

    // Spring Data JPA knows how to find uniqueId automatically!
    Optional<Paste> findByUniqueId(String uniqueId);
}
