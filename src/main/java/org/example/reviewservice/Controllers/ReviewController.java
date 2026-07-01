package org.example.reviewservice.Controllers;

import jakarta.websocket.server.PathParam;
import org.example.reviewservice.RequestDTO.ReviewDTO;
import org.example.reviewservice.RequestDTO.ReviewEditDTO;
import org.example.reviewservice.models.Booking;
import org.example.reviewservice.models.Review;
import org.example.reviewservice.repositories.BookingRespository;
import org.example.reviewservice.repositories.ReviewRepository;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final BookingRespository bookingRespository;

    ReviewController(ReviewRepository reviewRepository,
                     BookingRespository bookingRespository) {
        this.reviewRepository = reviewRepository;
        this.bookingRespository = bookingRespository;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.ok(this.reviewRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Review review = this.reviewRepository.findById(id).orElse(null);
        return ResponseEntity.ok(review);
    }
    @PostMapping("/{booking_id}/create")
    public ResponseEntity<Review> createReview(
            @RequestBody ReviewDTO reviewDTO,
            @PathVariable Long booking_id) {

        Optional<Booking> bookingOptional = bookingRespository.findById(booking_id);

        if (bookingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingOptional.get();
        if(booking.getReview()!=null){
            return ResponseEntity.badRequest().build();
        }

        Review review = Review.builder()
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .build();

        booking.setReview(review); // optional but keeps both objects in sync

        Review savedReview = reviewRepository.save(review);
        bookingRespository.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
    @DeleteMapping("/{review_id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long review_id) {
        Optional<Review> review= reviewRepository.findById(review_id);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Booking booking = bookingRespository.findByReview_id(review_id);
        booking.setReview(null);
        bookingRespository.save(booking);
        reviewRepository.deleteById(review_id);
        return ResponseEntity.ok(review.get());
    }

    @PatchMapping("/edit/{review_id}")
    public ResponseEntity<Review> editReview(@RequestBody ReviewEditDTO reviewDTO,
                                             @PathVariable Long review_id) {
        Optional<Review> review= reviewRepository.findById(review_id);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Booking booking = bookingRespository.findByReview_id(review_id);
        Review review1 = review.get();
        review1.setContent(reviewDTO.getComment());
        booking.setReview(review1);
        Review savedReview = reviewRepository.save(review1);
        bookingRespository.save(booking);
        return ResponseEntity.ok(savedReview);

    }

}
