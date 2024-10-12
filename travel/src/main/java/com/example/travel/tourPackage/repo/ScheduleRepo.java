package com.example.travel.tourPackage.repo;

import com.example.travel.tourPackage.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
