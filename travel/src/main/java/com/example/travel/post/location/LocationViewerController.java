package com.example.travel.post.location;

import com.example.travel.post.location.dto.LocationDto;
import com.example.travel.post.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("location-view")
public class LocationViewerController {
    private final LocationService locationService;
    @GetMapping
    public List<LocationDto> placesOfPost(@RequestBody LocationDto dto){
        return locationService.locationOfPost(dto);
    }
}
