package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {
    @Autowired
    private GenreService service;

    @GetMapping
    public ResponseEntity<Page<GenreDTO>> findAll(Pageable pageable){
        Page<GenreDTO> genres = this.service.findAllPaged(pageable);
        return ResponseEntity.ok().body(genres);
    }
}
