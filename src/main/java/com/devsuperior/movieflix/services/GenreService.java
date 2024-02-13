package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<GenreDTO> findAllPaged(Pageable pageRequest) {
        Page<Genre> pagedList = this.genreRepository.findAll(pageRequest);
        return pagedList.map(GenreDTO::new);
    }

}
