package com.example.travel.post.location.repo;

import com.example.travel.post.location.entity.Location;
import com.example.travel.post.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Long> {
    @Query("SELECT DISTINCT l FROM Location l JOIN FETCH l.posting WHERE l.posting=:posting")
    List<Location> placesOfPosting(@Param("posting")Posting posting);
}
