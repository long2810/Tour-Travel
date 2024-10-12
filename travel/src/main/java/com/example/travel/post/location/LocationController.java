package com.example.travel.post.location;

import com.example.travel.post.location.dto.LocationDto;
import com.example.travel.post.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("location")
public class LocationController {
    private final LocationService locationService;
    @PostMapping
    public LocationDto create(@RequestBody LocationDto dto){
        return locationService.create(dto);
    }

    @DeleteMapping("{locationId}")
    public void delete(@PathVariable("locationId") Long locationId){
        locationService.delete(locationId);
    }
}
