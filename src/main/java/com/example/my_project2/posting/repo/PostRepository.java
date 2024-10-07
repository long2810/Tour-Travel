package com.example.my_project2.posting.repo;

import com.example.my_project2.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posting,Long> {
    @Query("SELECT p FROM Posting p WHERE p.user.id = :userId")
    List<Posting> findAllByUserId(Long userId);

    List<Posting> findByTitle(String title);

    @Query("SELECT p FROM Posting p WHERE p.title LIKE %:keyword%")
    List<Posting> findAllByTitle(@Param("keyword") String keyword);
}
