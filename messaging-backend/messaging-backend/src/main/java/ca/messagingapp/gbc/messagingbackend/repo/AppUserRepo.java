package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByUsername(String username);
}