package ca.messagingapp.gbc.messagingbackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "group_message")
public class GroupMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "group_id", nullable = false)
    private UUID groupId;
    
    @Column(name = "sender_id", nullable = false)
    private UUID senderId;
    
    @Column(name = "ciphertext_base64", nullable = false)
    private String ciphertextBase64;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    
    // Constructors
    public GroupMessage() {}
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getGroupId() {
        return groupId;
    }
    
    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }
    
    public UUID getSenderId() {
        return senderId;
    }
    
    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }
    
    public String getCiphertextBase64() {
        return ciphertextBase64;
    }
    
    public void setCiphertextBase64(String ciphertextBase64) {
        this.ciphertextBase64 = ciphertextBase64;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}