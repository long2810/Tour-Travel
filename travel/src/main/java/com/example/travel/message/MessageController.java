package com.example.travel.message;

import com.example.travel.message.dto.MessageDto;
import com.example.travel.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("messages")
public class MessageController {
    private final MessageService messageService;

    @DeleteMapping("sender/{id}")
    public void delete(@PathVariable("id") Long messageId){
        messageService.delete(messageId);
    }

    @PutMapping("receiver/{id}")
    public MessageDto confirm(@PathVariable("id") Long messageId){
        return messageService.confirm(messageId);
    }

    @GetMapping("chat/{receiverId}")
    public List<MessageDto> chatbot(
            @PathVariable("receiverId")
            Long receiverId
    ){
        return messageService.chatbot(receiverId);
    }
    @GetMapping("{id}")
    public MessageDto oneMes(@PathVariable("id") Long messageId){
        return messageService.oneMes(messageId);
    }

}
