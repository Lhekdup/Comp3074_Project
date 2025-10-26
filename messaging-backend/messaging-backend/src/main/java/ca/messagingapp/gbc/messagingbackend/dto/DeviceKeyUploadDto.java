package ca.messagingapp.gbc.messagingbackend.dto;

public record DeviceKeyUploadDto(
    String deviceId,
    String identityKeyPublic,
    String signedPreKeyPublic,
    String oneTimePreKeyPublic
) {}