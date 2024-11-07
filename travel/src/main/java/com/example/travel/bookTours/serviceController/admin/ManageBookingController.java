package com.example.travel.bookTours.serviceController.admin;

import com.example.travel.bookTours.dto.BookToursDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("manage-booking")
public class ManageBookingController {
    private final ManageBookingService manageBookingService;
    @PutMapping("{bookId}")
    public BookToursDto confirm(@PathVariable("bookId") Long bookId){
        return manageBookingService.confirm(bookId);
    }

    @GetMapping
    public List<BookToursDto> allBooking(){
        return manageBookingService.allBooking();
    }

    @DeleteMapping("{bookId}")
    public void delete(@PathVariable("bookId") Long bookId ){
        manageBookingService.delete(bookId);
    }
}
