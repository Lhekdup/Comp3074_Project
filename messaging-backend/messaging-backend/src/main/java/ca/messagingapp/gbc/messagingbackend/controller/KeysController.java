package ca.messagingapp.gbc.messagingbackend.controller;

import ca.messagingapp.gbc.messagingbackend.dto.DeviceKeyUploadDto;
import ca.messagingapp.gbc.messagingbackend.entity.DeviceKey;
import ca.messagingapp.gbc.messagingbackend.repo.DeviceKeyRepo;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/keys")
public class KeysController {

    private final DeviceKeyRepo repo;

    public KeysController(DeviceKeyRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/{userId}")
    public void upload(@PathVariable UUID userId, @RequestBody DeviceKeyUploadDto dto) {
        DeviceKey dk = new DeviceKey();
        dk.setUserId(userId);
        dk.setDeviceId(dto.deviceId());
        dk.setIdentityKeyPublic(dto.identityKeyPublic());
        dk.setSignedPreKeyPublic(dto.signedPreKeyPublic());
        dk.setOneTimePreKeyPublic(dto.oneTimePreKeyPublic());
        dk.setUpdatedAt(Instant.now());
        repo.save(dk);
    }

    @GetMapping("/{userId}")
    public List<DeviceKey> fetch(@PathVariable UUID userId) {
        return repo.findByUserId(userId);
    }
}
