package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public ReviewDTO insertReview(ReviewDTO reviewBody) {
        User loggedUser = new User();
        try {
            loggedUser = this.authService.authenticated();
        } catch (Exception exception) {
            throw new UnauthorizedException("Usuário não autenticado");
        }

        Review newReviewEntity = new Review();
        newReviewEntity.setText(reviewBody.getText());
        newReviewEntity.setMovie(this.movieRepository.getOne(reviewBody.getMovieId()));
        newReviewEntity.setUser(loggedUser);
        newReviewEntity = this.reviewRepository.save(newReviewEntity);
        return new ReviewDTO(newReviewEntity, newReviewEntity.getMovie().getId());
    }
}
