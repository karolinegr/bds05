package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasAnyRole('MEMBER')")
    @Transactional
    public ReviewDTO insertReview(ReviewDTO reviewBody) {
        User loggedUser = this.authService.authenticated();

        Review newReviewEntity = new Review();
        newReviewEntity.setText(reviewBody.getText());
        newReviewEntity.setMovie(this.movieRepository.getOne(reviewBody.getMovieId()));
        newReviewEntity.setUser(loggedUser);
        newReviewEntity = this.reviewRepository.save(newReviewEntity);
        return new ReviewDTO(newReviewEntity, newReviewEntity.getMovie().getId());
    }
}
