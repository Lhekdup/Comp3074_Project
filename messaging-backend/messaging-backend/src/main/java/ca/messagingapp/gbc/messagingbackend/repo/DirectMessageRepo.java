package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DirectMessageRepo extends JpaRepository<DirectMessage, UUID> {
    List<DirectMessage> findTop50ByReceiverIdOrderByCreatedAtDesc(UUID receiverId);
}