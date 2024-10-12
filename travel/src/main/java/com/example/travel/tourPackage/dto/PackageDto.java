package com.example.travel.tourPackage.dto;

import com.example.travel.tourPackage.entity.Package;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PackageDto {
    private Long id;
    private String listTourists; //form "contentId,contentId,..."
    private Integer price;
    private Double avgRating;
    private String title;
    public static PackageDto dto(Package entity){
        PackageDto packageDto = PackageDto.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .avgRating(entity.getAvgRating())
                .title(entity.getTitle()).build();
        return packageDto;
    }
}
