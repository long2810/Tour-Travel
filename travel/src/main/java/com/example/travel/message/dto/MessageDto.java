package com.example.travel.message.dto;

import com.example.travel.message.entity.Message;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MessageDto {
    private Long id;
    private String content;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private boolean confirm;
    private boolean remove;
    public static MessageDto dto(Message message){
        MessageDto messageDto = MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSender().getId())
                .senderName(message.getSender().getName())
                .receiverId(message.getReceiver().getId())
                .receiverName(message.getReceiver().getName())
                .confirm(message.isConfirm())
                .remove(message.isRemove())
                .build();
        return messageDto;
    }
}
