package com.example.travel.message.service;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.authentication.user.repo.UserRepo;
import com.example.travel.message.dto.MessageDto;
import com.example.travel.message.entity.Message;
import com.example.travel.message.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MesNonUserService {
    private final UserRepo userRepo;

    public MessageDto create(MessageDto dto){
        Optional<UserEntity> sender = userRepo.findById(dto.getSenderId());
        Optional<UserEntity> receiver = userRepo.findById(dto.getReceiverId());

        Message newMessage = Message.builder()
                .content(dto.getContent())
                .sender(sender.get())
                .receiver(receiver.get())
                .confirm(false)
                .remove(false)
                .build();

        return MessageDto.dto(newMessage);
    }
}
