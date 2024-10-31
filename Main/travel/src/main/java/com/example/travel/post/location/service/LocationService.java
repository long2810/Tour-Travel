package com.example.travel.post.location.service;

import com.example.travel.post.location.dto.LocationDto;
import com.example.travel.post.location.entity.Location;
import com.example.travel.post.location.repo.LocationRepo;
import com.example.travel.post.posting.entity.Posting;
import com.example.travel.post.posting.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepo locationRepo;
    private final PostRepo postRepo;
    public LocationDto create(LocationDto dto){
        Posting posting = postRepo.findById(dto.getPostingId())
                .orElseThrow(()-> new IllegalArgumentException("No exist posting!!!"));
        Location location = Location.builder()
                .title(dto.getTitle())
                .posting(posting)
                .address(dto.getAddress())
                .mapx(dto.getMapx())
                .mapy(dto.getMapy())
                .build();
        return LocationDto.dto(locationRepo.save(location));
    }

    public void delete(Long locationId){
        locationRepo.deleteById(locationId);
    }

    public List<LocationDto> locationOfPost(LocationDto dto){
        List<LocationDto> list = new ArrayList<>();
        Posting posting = postRepo.findById(dto.getPostingId())
                .orElseThrow(()-> new IllegalArgumentException("No exist posting!!!"));
        for (Location location: locationRepo.placesOfPosting(posting)){
            list.add(LocationDto.dto(location));
        }
        return list;
    }
}
