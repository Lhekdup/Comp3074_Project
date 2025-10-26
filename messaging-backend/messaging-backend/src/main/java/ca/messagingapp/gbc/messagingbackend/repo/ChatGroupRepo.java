package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatGroupRepo extends JpaRepository<ChatGroup, UUID> {
}