package com.example.my_project2.naver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaverRepository extends JpaRepository<Naver, Long> {
}
