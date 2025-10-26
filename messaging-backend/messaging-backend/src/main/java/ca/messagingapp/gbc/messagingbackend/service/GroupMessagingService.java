package ca.messagingapp.gbc.messagingbackend.service;

import ca.messagingapp.gbc.messagingbackend.repo.GroupMemberRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GroupMessagingService {

    private final AmqpTemplate amqpTemplate;
    private final SimpMessagingTemplate messagingTemplate;
    private final GroupMemberRepo groupMemberRepo;

    public GroupMessagingService(AmqpTemplate amqpTemplate, 
                                SimpMessagingTemplate messagingTemplate,
                                GroupMemberRepo groupMemberRepo) {
        this.amqpTemplate = amqpTemplate;
        this.messagingTemplate = messagingTemplate;
        this.groupMemberRepo = groupMemberRepo;
    }

    public void publishGroup(UUID groupId, String cipherText) {
        // Publish to RabbitMQ fanout exchange
        amqpTemplate.convertAndSend("grp.broadcast", "", 
            Map.of("groupId", groupId.toString(), "cipherText", cipherText));
    }

    @RabbitListener(queues = "#{serverQueue.name}")
    public void handleGroupMessage(Map<String, String> payload) {
        String groupIdStr = payload.get("groupId");
        String cipherText = payload.get("cipherText");
        
        if (groupIdStr != null && cipherText != null) {
            UUID groupId = UUID.fromString(groupIdStr);
            
            // Get all group members
            groupMemberRepo.findByGroupId(groupId).forEach(member -> {
                // Send to each group member via WebSocket
                messagingTemplate.convertAndSendToUser(
                    member.getUserId().toString(),
                    "/queue/messages",
                    Map.of("groupId", groupIdStr, "cipherText", cipherText)
                );
            });
        }
    }
}