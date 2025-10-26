package ca.messagingapp.gbc.messagingbackend.dto;

public record GroupMessageDto(
    String groupId,
    String cipherText
) {}