package ca.messagingapp.gbc.messagingbackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "device_key")
public class DeviceKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    
    @Column(name = "device_id", nullable = false)
    private String deviceId;
    
    @Column(name = "identity_key_public", nullable = false)
    private String identityKeyPublic;
    
    @Column(name = "signed_prekey_public", nullable = false)
    private String signedPreKeyPublic;
    
    @Column(name = "one_time_prekey_public")
    private String oneTimePreKeyPublic;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    
    // Constructors
    public DeviceKey() {}
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public String getIdentityKeyPublic() {
        return identityKeyPublic;
    }
    
    public void setIdentityKeyPublic(String identityKeyPublic) {
        this.identityKeyPublic = identityKeyPublic;
    }
    
    public String getSignedPreKeyPublic() {
        return signedPreKeyPublic;
    }
    
    public void setSignedPreKeyPublic(String signedPreKeyPublic) {
        this.signedPreKeyPublic = signedPreKeyPublic;
    }
    
    public String getOneTimePreKeyPublic() {
        return oneTimePreKeyPublic;
    }
    
    public void setOneTimePreKeyPublic(String oneTimePreKeyPublic) {
        this.oneTimePreKeyPublic = oneTimePreKeyPublic;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}