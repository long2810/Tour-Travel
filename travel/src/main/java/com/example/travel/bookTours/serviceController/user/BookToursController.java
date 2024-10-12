package com.example.travel.bookTours.serviceController.user;

import com.example.travel.bookTours.dto.BookToursDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookToursController {
    private final BookToursService bookToursService;
    @PostMapping
    public BookToursDto booking(
            @RequestBody
            BookToursDto dto
    ){
        return bookToursService.booking(dto);
    }

    @PutMapping("{bookId}")
    public BookToursDto payment(
            @PathVariable("bookId")
            Long bookId,
            @RequestBody
            BookToursDto dto
    ){
        return bookToursService.paying(dto, bookId);
    }

    @GetMapping("{bookId}")
    public BookToursDto checkBooking(@PathVariable("bookId") Long bookId){
        return bookToursService.checkBooking(bookId);
    }

    @GetMapping
    public List<BookToursDto> listBooking(){
        return bookToursService.listBooking();
    }

    @PutMapping("{bookId}/cancel")
    public BookToursDto cancel(@PathVariable("bookId")
                                   Long bookId){
        return bookToursService.cancel(bookId);
    }
}
