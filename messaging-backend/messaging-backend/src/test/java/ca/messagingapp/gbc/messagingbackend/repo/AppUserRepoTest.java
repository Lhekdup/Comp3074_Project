package ca.messagingapp.gbc.messagingbackend.repo;

import ca.messagingapp.gbc.messagingbackend.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AppUserRepoTest {

    @Autowired
    private AppUserRepo appUserRepo;

    @Test
    void shouldSaveAndFindUserByUsername() {
        // Given
        AppUser user = new AppUser("testuser");
        
        // When
        AppUser savedUser = appUserRepo.save(user);
        Optional<AppUser> foundUser = appUserRepo.findByUsername("testuser");
        
        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound() {
        // When
        Optional<AppUser> foundUser = appUserRepo.findByUsername("nonexistent");
        
        // Then
        assertThat(foundUser).isEmpty();
    }
}



