package com.example.travel.bookTours.entity;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.baseEntity.BaseEntity;
import com.example.travel.tourPackage.entity.Package;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookTours extends BaseEntity {
    private LocalDate departureDay;
    private Integer numOfPeople;
    private Integer payment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Coupon coupon;
    @ManyToOne
    private Package tourPackage;
    @ManyToOne
    private UserEntity customer;
    public enum Status {
        Booking_successful, Cancel_booking, Payment_successful, Confirmed
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum Coupon {
        TWENTY(20),
        THIRTY(30),
        FORTY(40),
        FIFTY(50),
        SIXTY(60),
        SEVENTY(70),
        EIGHTY(80);
        private Integer value;
    }
}
