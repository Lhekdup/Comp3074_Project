package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.DeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceKeyRepo extends JpaRepository<DeviceKey, UUID> {
    List<DeviceKey> findByUserId(UUID userId);
}