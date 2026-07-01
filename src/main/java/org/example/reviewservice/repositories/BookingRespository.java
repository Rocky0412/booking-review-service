package org.example.reviewservice.repositories;

import org.example.reviewservice.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRespository extends JpaRepository<Booking, Long> {
    Booking findByReview_id(Long reviewId);
}
