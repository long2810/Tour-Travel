package com.example.travel.tourist.template.kor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tours/kor")
public class KorController {
    private final KorTemplate korTemplate;

    @GetMapping("landmark")
    public List<Map<String, Object>> allLandmarkPerPage(
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("sigunguCode") String sigunguCode,
            @RequestParam("keyword") String keyword
    ){
        if (keyword!=null && sigunguCode!=null)
            return korTemplate.search(pageNo, 12, sigunguCode, keyword);
        else if (keyword!=null)
            return korTemplate.search(pageNo, 12, null, keyword);
        else if (sigunguCode!=null)
            return korTemplate.allInfoPerPage(pageNo, 12, sigunguCode);
        return korTemplate.allInfoPerPage(pageNo, 12, null);
    }
    @GetMapping("restaurant")
    public List<Map<String, Object>> allRestaurantPerPage(@RequestParam("pageNo") Integer pageNo){
        return korTemplate.allInfoPerPage(pageNo, 39, null);
    }
    @GetMapping("accommodation")
    public List<Map<String, Object>> allAccommodationPerPage(@RequestParam("pageNo") Integer pageNo){
        return korTemplate.allInfoPerPage(pageNo, 32, null);
    }

    @GetMapping("detail/{contentId}")
    public Map<String, Object> oneTour(@PathVariable("contentId") String contentId){
        return korTemplate.detailInfo(contentId);
    }
}
