package com.example.my_project2.posting.dto;

import com.example.my_project2.posting.entity.Image_Posting;
import com.example.my_project2.posting.entity.Posting;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ImageDto {
    private Long id;
    private String link;
    private Long posting_id;

    public static ImageDto fromEntity(Image_Posting entity) {
        ImageDto dto = ImageDto.builder()
                .id(entity.getId())
                .link(entity.getLink())
                .posting_id(entity.getPosting().getId())
                .build();
        return dto;
    }
}
