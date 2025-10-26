package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupMessageRepo extends JpaRepository<GroupMessage, UUID> {
    List<GroupMessage> findTop50ByGroupIdOrderByCreatedAtDesc(UUID groupId);
}