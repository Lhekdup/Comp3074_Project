package ca.messagingapp.gbc.messagingbackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "direct_message")
public class DirectMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "sender_id", nullable = false)
    private UUID senderId;
    
    @Column(name = "receiver_id", nullable = false)
    private UUID receiverId;
    
    @Column(name = "ciphertext_base64", nullable = false)
    private String ciphertextBase64;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    
    // Constructors
    public DirectMessage() {}
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getSenderId() {
        return senderId;
    }
    
    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }
    
    public UUID getReceiverId() {
        return receiverId;
    }
    
    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
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