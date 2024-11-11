package com.athuull.markdown_paste_service.Service;

import com.athuull.markdown_paste_service.Entity.Paste;
import com.athuull.markdown_paste_service.Repository.PasteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    // Creates a paste object using the title and content and saves it to db. Other stuff is automatically generated
    public Paste savePaste(String content, String title) {
        Paste paste = new Paste();
        paste.setContent(content);
        paste.setTitle(title);
        paste.setCreatedAt(LocalDateTime.now());


        return pasteRepository.save(paste);
    }

    public Optional<Paste> getPaste(String uniqueID) {

        return pasteRepository.findByUniqueID(uniqueID);

    }



}
