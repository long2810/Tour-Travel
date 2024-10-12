package com.example.travel.tourPackage.createPackage;

import com.example.travel.tourPackage.repo.PackageRepo;
import com.example.travel.tourPackage.repo.ScheduleRepo;
import org.springframework.stereotype.Component;

@Component
public class PackageInfo {
    private final ScheduleRepo scheduleRepo;
    private final PackageRepo packageRepo;

    public PackageInfo(ScheduleRepo scheduleRepo, PackageRepo packageRepo) {
        this.scheduleRepo = scheduleRepo;
        this.packageRepo = packageRepo;
    }

    
}
