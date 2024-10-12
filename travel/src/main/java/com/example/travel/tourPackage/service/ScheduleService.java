package com.example.travel.tourPackage.service;

import com.example.travel.tourPackage.repo.PackageRepo;
import com.example.travel.tourPackage.repo.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final PackageRepo packageRepo;

}
