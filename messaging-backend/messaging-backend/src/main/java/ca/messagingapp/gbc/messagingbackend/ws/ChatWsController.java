package ca.messagingapp.gbc.messagingbackend.ws;

import ca.messagingapp.gbc.messagingbackend.dto.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWsController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatWsController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.direct")
    public void handleDirectMessage(DirectMessageDto dto) {
        // Send direct message to specific user
        messagingTemplate.convertAndSendToUser(
            dto.toUserId(), 
            "/queue/messages", 
            dto
        );
    }

    @MessageMapping("/webrtc.offer")
    public void handleWebRtcOffer(OfferDto dto) {
        // Send WebRTC offer to specific user
        messagingTemplate.convertAndSendToUser(
            dto.toUserId(), 
            "/queue/webrtc", 
            dto
        );
    }

    @MessageMapping("/webrtc.answer")
    public void handleWebRtcAnswer(AnswerDto dto) {
        // Send WebRTC answer to specific user
        messagingTemplate.convertAndSendToUser(
            dto.toUserId(), 
            "/queue/webrtc", 
            dto
        );
    }

    @MessageMapping("/webrtc.ice")
    public void handleWebRtcIce(IceDto dto) {
        // Send WebRTC ICE candidate to specific user
        messagingTemplate.convertAndSendToUser(
            dto.toUserId(), 
            "/queue/webrtc", 
            dto
        );
    }
}