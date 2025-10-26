package ca.messagingapp.gbc.messagingbackend.dto;

public record DirectMessageDto(
    String toUserId,
    String cipherText
) {}