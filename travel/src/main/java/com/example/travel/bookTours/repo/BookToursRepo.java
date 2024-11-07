package com.example.travel.bookTours.repo;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.bookTours.entity.BookTours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookToursRepo extends JpaRepository<BookTours, Long> {
//    List<BookTours> findByUserEntity(UserEntity customer);
    @Query("SELECT DISTINCT b FROM BookTours b JOIN FETCH b.customer WHERE b.customer= :user")
    List<BookTours> listBooking(@Param("user") UserEntity user);
}
