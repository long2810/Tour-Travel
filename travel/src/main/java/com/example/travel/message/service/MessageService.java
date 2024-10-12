package com.example.travel.message.service;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.message.dto.MessageDto;
import com.example.travel.message.entity.Message;
import com.example.travel.message.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepo messageRepo;
    private final UserComponent userComponent;

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

//    public MessageDto edit(MessageDto dto){
//        UserEntity sender = userComponent.userById(dto.getSenderId());
//        Message message = messageRepo.findById(dto.getId())
//                .orElseThrow(()-> new IllegalArgumentException("No exist message!!"));
//        if (!message.getSender().getId().equals(sender.getId()))
//            throw new IllegalArgumentException("No exist message");
//        else if (message.isConfirm()) throw new IllegalArgumentException("Cannot edit this message");
//        message.setContent(dto.getContent());
//        return MessageDto.dto(messageRepo.save(message));
//    }

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

    public void delete(Long messageId){
        UserEntity sender = userComponent.userLogin();
        Message message = messageRepo.findById(messageId)
                .orElseThrow(()-> new IllegalArgumentException("No exist message!!"));
        if (!message.getSender().getId().equals(sender.getId()))
            throw new IllegalArgumentException("No exist message");
        else if (message.isConfirm()) throw new IllegalArgumentException("Cannot delete this message");
        messageRepo.delete(message);
    }

    // Receiver
    public MessageDto confirm(Long messageId){
        UserEntity receiver= userComponent.userLogin();
        Message message = messageRepo.findById(messageId)
                .orElseThrow(()-> new IllegalArgumentException("No exist message!!"));
        if (!message.getReceiver().getId().equals(receiver.getId()))
            throw new IllegalArgumentException("No exist message");
        message.setConfirm(true);
        return MessageDto.dto(messageRepo.save(message));
    }

    public List<MessageDto> chatbot(Long receiverId){
        List<MessageDto> list = new ArrayList<>();
        UserEntity sender = userComponent.userLogin();
        UserEntity receiver = userComponent.userById(receiverId);

        List<Message> messages = messageRepo.chatMessages(sender, receiver);
        for (Message message: messages){
            list.add(MessageDto.dto(message));
        }
        return list;
    }

    public MessageDto oneMes(Long id){
        Message message = messageRepo.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("No exist message")
        );
        return MessageDto.dto(message);
    }

}
