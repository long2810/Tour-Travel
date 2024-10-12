package com.example.travel.message.service;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.message.dto.MessageDto;
import com.example.travel.message.entity.Message;
import com.example.travel.message.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MesSocketService {
    private final UserComponent userComponent;
    private final MessageRepo messageRepo;

    public MessageDto create(MessageDto dto){
        UserEntity sender = userComponent.userById(dto.getSenderId());
        UserEntity receiver = userComponent.userById(dto.getReceiverId());

        Message newMessage = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .receiver(receiver)
                .confirm(false)
                .remove(false)
                .build();

        return MessageDto.dto(messageRepo.save(newMessage));
    }

    public MessageDto edit(MessageDto dto){
        UserEntity sender = userComponent.userById(dto.getSenderId());
        Message message = messageRepo.findById(dto.getId())
                .orElseThrow(()-> new IllegalArgumentException("No exist message!!"));
        if (!message.getSender().getId().equals(sender.getId()))
            throw new IllegalArgumentException("No exist message");
        else if (message.isConfirm()) throw new IllegalArgumentException("Cannot edit this message");
        message.setContent(dto.getContent());
        return MessageDto.dto(messageRepo.save(message));
    }

    public MessageDto remove(MessageDto dto){
        UserEntity sender = userComponent.userById(dto.getSenderId());
        Message message = messageRepo.findById(dto.getId())
                .orElseThrow(()-> new IllegalArgumentException("No exist message!!"));
        if (!message.getSender().getId().equals(sender.getId())
                && !message.getReceiver().getId().equals(sender.getId()))
            throw new IllegalArgumentException("No exist message");
        message.setContent("This message has been removed!!!");
        message.setRemove(true);
        return MessageDto.dto(messageRepo.save(message));
    }
}
