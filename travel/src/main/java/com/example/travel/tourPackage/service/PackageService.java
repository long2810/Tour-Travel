package com.example.travel.tourPackage.service;

import com.example.travel.tourPackage.dto.PackageDto;
import com.example.travel.tourPackage.entity.Package;
import com.example.travel.tourPackage.repo.PackageRepo;
import com.example.travel.tourPackage.repo.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepo packageRepo;
    private final ScheduleRepo scheduleRepo;

    public PackageDto readOne(Long packageId){
        Package tourPackage = packageRepo.findById(packageId)
                .orElseThrow(()-> new IllegalArgumentException("No exist tour package"));
        return PackageDto.dto(tourPackage);
    }

    public List<PackageDto> readAll(){
        List<PackageDto> allPackages = new ArrayList<>();
        for(Package tour: packageRepo.findAll()){
            allPackages.add(PackageDto.dto(tour));
        }
        return allPackages;
    }
}
