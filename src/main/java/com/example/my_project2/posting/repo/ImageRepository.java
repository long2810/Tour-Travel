package com.example.my_project2.posting.repo;

import com.example.my_project2.posting.entity.Image_Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image_Posting, Long> {
}
