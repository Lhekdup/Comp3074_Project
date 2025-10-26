package ca.messagingapp.gbc.messagingbackend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "group_member")
public class GroupMember {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "group_id", nullable = false)
    private UUID groupId;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    
    // Constructors
    public GroupMember() {}
    
    public GroupMember(UUID groupId, UUID userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
    
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
    
    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}