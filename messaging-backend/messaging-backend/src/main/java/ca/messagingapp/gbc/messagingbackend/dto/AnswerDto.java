package ca.messagingapp.gbc.messagingbackend.dto;

public record AnswerDto(
    String toUserId,
    String sdp
) {}