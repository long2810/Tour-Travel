package com.example.travel.bookTours.serviceController.user;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.bookTours.dto.BookToursDto;
import com.example.travel.bookTours.entity.BookTours;
import com.example.travel.bookTours.repo.BookToursRepo;
import com.example.travel.tourPackage.entity.Package;
import com.example.travel.tourPackage.repo.PackageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookToursService {
    private final BookToursRepo bookToursRepo;
    private final PackageRepo packageRepo;
    private final UserComponent userComponent;
    //Create
    public BookToursDto booking(BookToursDto dto){
        Package pack = packageRepo.findById(dto.getPackageId())
                .orElseThrow(()-> new IllegalArgumentException("No exist package!!!"));
        BookTours bookTours = BookTours.builder()
                .tourPackage(pack)
                .numOfPeople(dto.getNumOfPeople())
                .customer(userComponent.userLogin())
                .departureDay(dto.getDepartureDay())
                .status(BookTours.Status.Booking_successful)
                .payment(dto.getPayment())
                .build();
        return BookToursDto.dto(bookToursRepo.save(bookTours));
    }

    //Update payment
    public BookToursDto paying(BookToursDto dto, Long bookId){
        BookTours tour = bookToursRepo.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("No exist booking"));
        if (!tour.getCustomer().getId().equals(userComponent.userLogin().getId()))
            throw new IllegalArgumentException("This booking is not available!!!");
        tour.setStatus(BookTours.Status.Payment_successful);
        return BookToursDto.dto(bookToursRepo.save(tour));
    }

    //Read one
    public BookToursDto checkBooking(Long bookId){
        BookTours tour = bookToursRepo.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("No exist booking"));
        if (!tour.getCustomer().getId().equals(userComponent.userLogin().getId()))
            throw new IllegalArgumentException("This booking is not available!!!");
        return BookToursDto.dto(tour);
    }

    //Read all
    public List<BookToursDto> listBooking(){
        List<BookToursDto> list = new ArrayList<>();
        List<BookTours> toursList = bookToursRepo.listBooking(userComponent.userLogin());
        for (BookTours booking: toursList){
            list.add(BookToursDto.dto(booking));
        }
        return list;
    }

    public BookToursDto cancel(Long bookId){
        BookTours tour = bookToursRepo.findById(bookId)
                .orElseThrow(()-> new IllegalArgumentException("No exist booking"));
        if (!tour.getCustomer().getId().equals(userComponent.userLogin().getId()))
            throw new IllegalArgumentException("This booking is not available!!!");
        tour.setStatus(BookTours.Status.Cancel_booking);
        return BookToursDto.dto(bookToursRepo.save(tour));
    }
}
