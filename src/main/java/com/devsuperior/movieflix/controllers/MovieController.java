package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(
            @PathVariable Long id){
        MovieDTO movie = this.service.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findMovieReviews(
            @PathVariable Long id){
        MovieDTO movie = this.service.findById(id);
        return ResponseEntity.ok().body(new ArrayList<>(movie.getReviews()));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findMovies(
            @RequestParam(name = "genreId", defaultValue = "0", required = false)
            Long genreId,
            Pageable pageable){
        Page<MovieDTO> movies = this.service.findMovies(genreId, pageable);
        return ResponseEntity.ok().body(movies);
    }

}
