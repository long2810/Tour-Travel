package com.example.travel.message;

import com.example.travel.message.dto.MessageDto;
import com.example.travel.message.service.MesNonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MesNonUserController {
    private final MesNonUserService nonUserService;

    @MessageMapping("mes-non-defined")
    @SendTo("/topic/mes-non-defined")
    public MessageDto create(MessageDto dto){
        return nonUserService.create(dto);
    }
}
