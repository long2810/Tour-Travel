package com.example.travel.authentication.user;

import com.example.travel.authentication.user.dto.UserDto;
import com.example.travel.authentication.user.service.AfterLoginService;
import com.example.travel.authentication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final AfterLoginService afterLoginService;
    @PostMapping
    public UserDto create(@RequestBody UserDto dto){
        return userService.create(dto);
    }

    @GetMapping
    public UserDto user(){
        return userService.userLogin();
    }

    @PutMapping
    public UserDto updateInfo(@RequestBody UserDto dto){
        return userService.changeInfo(dto);
    }

    @PutMapping("password")
    public UserDto updateOtherInfo(
            @RequestParam String oldPw,
            @RequestParam String newPw
    ){
        return userService.changePassword(oldPw, newPw);
    }

    @PutMapping("avatar")
    public UserDto updateImg(
            @RequestParam("avatar")
            MultipartFile avatar
    ){
        return userService.changeAvatar(avatar);
    }
//    @GetMapping("mes/{id}")
//    public Integer mesCount(@PathVariable("id") Long friendId){
//        return afterLoginService.mesCount(friendId);
//    }



//    @GetMapping("{id}")
//    public UserDto oneUser(@PathVariable("id") Long id){
//        return userService.oneUser(id);
//    }
}
