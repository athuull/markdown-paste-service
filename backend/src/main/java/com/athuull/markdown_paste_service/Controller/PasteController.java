package com.athuull.markdown_paste_service.Controller;

import com.athuull.markdown_paste_service.DTO.PasteRequest;
import com.athuull.markdown_paste_service.Entity.Paste;
import com.athuull.markdown_paste_service.Service.PasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
public class PasteController {

    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @GetMapping
    public Optional<Paste> getPaste(String uniqueID) {
        return pasteService.getPaste(uniqueID);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results are ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Paste.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found", content = @Content)})
    @Operation(summary = "Create a new Paste in DB")
    public Paste createPaste(@RequestBody PasteRequest request) {
        return pasteService.savePaste(request.getContent(), request.getTitle());

    }
}