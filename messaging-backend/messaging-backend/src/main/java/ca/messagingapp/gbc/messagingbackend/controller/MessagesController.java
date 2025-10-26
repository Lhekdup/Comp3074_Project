package ca.messagingapp.gbc.messagingbackend.controller;

import ca.messagingapp.gbc.messagingbackend.dto.DirectMessageDto;
import ca.messagingapp.gbc.messagingbackend.dto.GroupMessageDto;
import ca.messagingapp.gbc.messagingbackend.entity.DirectMessage;
import ca.messagingapp.gbc.messagingbackend.entity.GroupMessage;
import ca.messagingapp.gbc.messagingbackend.repo.DirectMessageRepo;
import ca.messagingapp.gbc.messagingbackend.repo.GroupMessageRepo;
import ca.messagingapp.gbc.messagingbackend.service.GroupMessagingService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/messages")
public class MessagesController {

    private final DirectMessageRepo dmRepo;
    private final GroupMessageRepo gmRepo;
    private final SimpMessagingTemplate ws;
    private final GroupMessagingService groupMessagingService;

    public MessagesController(DirectMessageRepo dmRepo, GroupMessageRepo gmRepo,
                              SimpMessagingTemplate ws, GroupMessagingService groupMessagingService) {
        this.dmRepo = dmRepo;
        this.gmRepo = gmRepo;
        this.ws = ws;
        this.groupMessagingService = groupMessagingService;
    }

    @PostMapping("/direct")
    public void direct(@RequestBody DirectMessageDto dto) {
        DirectMessage m = new DirectMessage();
        m.setSenderId(UUID.randomUUID()); // TODO replace with authenticated user
        m.setReceiverId(UUID.fromString(dto.toUserId()));
        m.setCiphertextBase64(dto.cipherText());
        dmRepo.save(m);
        ws.convertAndSendToUser(dto.toUserId(), "/queue/messages", dto);
    }

    @PostMapping("/group")
    public void group(@RequestBody GroupMessageDto dto) {
        UUID gid = UUID.fromString(dto.groupId());
        GroupMessage gm = new GroupMessage();
        gm.setGroupId(gid);
        gm.setSenderId(UUID.randomUUID()); // TODO replace with authenticated user
        gm.setCiphertextBase64(dto.cipherText());
        gmRepo.save(gm);
        groupMessagingService.publishGroup(gid, dto.cipherText());
    }
}
