package org.example.reviewservice.Services;

import org.example.entityservices.models.Booking;
import org.example.reviewservice.RequestDTO.BookingRequestDTO;

import java.util.List;

public interface IBookingService {


    public BookingRequestDTO addBooking(BookingRequestDTO bookingRequestDTO);

}
