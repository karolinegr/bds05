package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select distinct obj from Movie obj " +
            "inner join obj.genre as gens where " +
            "(:genre IS NULL OR gens IN (:genre))")
    Page<Movie> find(
            List<Genre> genre,
            Pageable pageable);

    @Query("select obj from Movie obj join fetch obj.reviews where " +
            "obj in :movies")
    List<Movie> findMoviesWithReviews(List<Movie> movies);
}
