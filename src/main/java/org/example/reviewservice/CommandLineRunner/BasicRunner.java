package org.example.reviewservice.CommandLineRunner;

import org.example.reviewservice.models.Review;
import org.example.reviewservice.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicRunner implements CommandLineRunner {

    ReviewRepository repository;
    @Autowired
    public void setRepository(ReviewRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {

        Review review = Review.builder()
                .content("This is a test review")
                .rating(4.5)
                .build();
        repository.save(review);
        List<Review> reviews = repository.findAll();
        System.out.println("Hello World!");
        for (Review review1 : reviews) {
            System.out.println(review1);
        }

    }
}
