package com.example.travel.bookTours.serviceController.admin;

import com.example.travel.bookTours.dto.BookToursDto;
import com.example.travel.bookTours.entity.BookTours;
import com.example.travel.bookTours.repo.BookToursRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageBookingService {
    private final BookToursRepo bookToursRepo;
    public BookToursDto confirm(Long bookId){
        BookTours booking = bookToursRepo.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("No exist booking"));
        booking.setStatus(BookTours.Status.Confirmed);
        return BookToursDto.dto(bookToursRepo.save(booking));
    }

    public List<BookToursDto> allBooking(){
        List<BookToursDto> all = new ArrayList<>();
        for (BookTours booking: bookToursRepo.findAll()){
            all.add(BookToursDto.dto(booking));
        }
        return all;
    }

    public void delete(Long bookId){
        BookTours booking = bookToursRepo.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("No exist booking"));
        if (!booking.getStatus().equals(BookTours.Status.Cancel_booking))
            throw new IllegalArgumentException("Cannot delete this booking!!!");
        bookToursRepo.delete(booking);
    }
}
