package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findMovies(
            Long genreId,
            Pageable pageable){
        List<Genre> genre = genreId == 0 ? null : List.of(this.genreRepository.getOne(genreId));
        pageable = pageable.getSort().isUnsorted() ? PageRequest.of(0, 20, Sort.by("title").ascending()) : pageable;
        Page<Movie> pagedList = this.movieRepository.find(genre, pageable);
        this.movieRepository.findMoviesWithReviews(pagedList.getContent());
        return pagedList.map(item -> new MovieDTO(item, item.getReviews()));
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Optional<Movie> obj = this.movieRepository.findById(id);
        Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
        this.movieRepository.findMoviesWithReviews(List.of(movie));
        return new MovieDTO(movie, movie.getReviews());
    }
}
