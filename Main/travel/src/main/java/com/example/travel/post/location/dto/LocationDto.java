package com.example.travel.post.location.dto;

import com.example.travel.post.location.entity.Location;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class LocationDto {
    private Long id;
    private String title;
    private String address;
    private String mapx;
    private String mapy;
    private Long postingId;
    public static LocationDto dto (Location location){
        LocationDto locationDto = LocationDto.builder()
                .id(location.getId())
                .address(location.getAddress())
                .title(location.getTitle())
                .mapx(location.getMapx())
                .mapy(location.getMapy())
                .postingId(location.getPosting().getId())
                .build();
        return locationDto;
    }
}
