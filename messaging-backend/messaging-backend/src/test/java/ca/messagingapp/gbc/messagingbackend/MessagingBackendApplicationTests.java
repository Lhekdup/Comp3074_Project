package ca.messagingapp.gbc.messagingbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class MessagingBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
