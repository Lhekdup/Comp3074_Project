package ca.messagingapp.gbc.messagingbackend.dto;

public record IceDto(
    String toUserId,
    String candidate
) {}