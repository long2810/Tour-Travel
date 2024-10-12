package com.example.travel.tourPackage.repo;

import com.example.travel.tourPackage.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepo extends JpaRepository<Package, Long> {
}
