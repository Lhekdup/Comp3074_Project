# Messaging Backend

A Spring Boot messaging application with WebSocket support, PostgreSQL database, and RabbitMQ for group messaging.

## Features

- **End-to-End Encryption**: Server stores/relays ciphertext only (Signal Protocol)
- **Real-time Messaging**: WebSocket with STOMP protocol
- **WebRTC Signaling**: Call signaling over WebSocket
- **Group Messaging**: RabbitMQ fanout for group broadcasts
- **Device Key Management**: Signal Protocol key storage
- **Database Migrations**: Flyway for schema management

## Tech Stack

- **Java 17**
- **Spring Boot 3.5**
- **PostgreSQL** (dev via Docker)
- **RabbitMQ** (dev via Docker)
- **Flyway** (database migrations)
- **WebSocket/STOMP** (real-time messaging)
- **Gradle** (build tool)

## Quick Start

### 1. Start Services

```bash
# Start PostgreSQL and RabbitMQ
docker compose up -d
```

### 2. Run Application

```bash
# Run the Spring Boot application
./gradlew bootRun
```

The application will be available at:
- **HTTP**: http://localhost:8080
- **WebSocket**: ws://localhost:8080/ws
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)

## API Endpoints

### REST API

#### Device Keys
- `POST /v1/keys/{userId}` - Upload Signal public keys for a device
- `GET /v1/keys/{userId}` - List device key records for a user

#### Messages
- `POST /v1/messages/direct` - Send encrypted direct message
- `POST /v1/messages/group` - Send encrypted group message

### WebSocket/STOMP

#### Connection
- **Endpoint**: `/ws`
- **Protocol**: STOMP over WebSocket

#### Client Subscriptions
- `/user/queue/messages` - Receive direct messages
- `/user/queue/webrtc` - Receive WebRTC signaling

#### Client Sends
- `/app/chat.direct` - Send direct message
- `/app/webrtc.offer` - Send WebRTC offer
- `/app/webrtc.answer` - Send WebRTC answer
- `/app/webrtc.ice` - Send WebRTC ICE candidate

## Data Model

### Entities
- **AppUser**: User accounts
- **DeviceKey**: Signal Protocol keys per device
- **DirectMessage**: Encrypted direct messages
- **ChatGroup**: Group chat rooms
- **GroupMember**: Group membership
- **GroupMessage**: Encrypted group messages

### Database Schema
All tables use UUID primary keys and UTC timestamps. See `src/main/resources/db/migration/V1__init.sql` for the complete schema.

## Android Integration

### Connection Details
- **REST Base**: `http://10.0.2.2:8080/`
- **WebSocket**: `ws://10.0.2.2:8080/ws`

### STOMP Usage
```javascript
// Subscribe to messages
stompClient.subscribe('/user/queue/messages', onMessage);
stompClient.subscribe('/user/queue/webrtc', onWebRTC);

// Send direct message
stompClient.send('/app/chat.direct', {}, {
    toUserId: 'user-uuid',
    cipherText: 'encrypted-message'
});

// Send WebRTC offer
stompClient.send('/app/webrtc.offer', {}, {
    toUserId: 'user-uuid',
    sdp: 'offer-sdp'
});
```

## Testing

### Run Tests
```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests AppUserRepoTest
```

### Test Configuration
Tests use H2 in-memory database and disabled external services for fast execution.

## Development

### Database Migrations
- Migrations are in `src/main/resources/db/migration/`
- Flyway automatically runs migrations on startup
- Use descriptive names: `V2__add_user_preferences.sql`

### Adding New Features
1. Create entity in `entity/` package
2. Create repository in `repo/` package
3. Add REST controller in `controller/` package
4. Create Flyway migration if schema changes needed
5. Add tests in `test/` package

## Production Deployment

### Supabase Configuration
Create `application-prod.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://<supabase-host>:5432/postgres?sslmode=require
    username: <supabase_user>
    password: <supabase_pass>
```

### Environment Variables
- `SPRING_PROFILES_ACTIVE=prod`
- `DATABASE_URL` (Supabase connection string)
- `RABBITMQ_URL` (CloudAMQP or similar)

## Example Usage

### Upload Device Keys
```bash
curl -X POST http://localhost:8080/v1/keys/11111111-1111-1111-1111-111111111111 \
  -H "Content-Type: application/json" \
  -d '{
    "deviceId": "dev1",
    "identityKeyPublic": "base64-identity-key",
    "signedPreKeyPublic": "base64-signed-prekey",
    "oneTimePreKeyPublic": "base64-otpk"
  }'
```

### Send Direct Message
```bash
curl -X POST http://localhost:8080/v1/messages/direct \
  -H "Content-Type: application/json" \
  -d '{
    "toUserId": "22222222-2222-2222-2222-222222222222",
    "cipherText": "BASE64_ENCRYPTED_MESSAGE"
  }'
```

### Send Group Message
```bash
curl -X POST http://localhost:8080/v1/messages/group \
  -H "Content-Type: application/json" \
  -d '{
    "groupId": "33333333-3333-3333-3333-333333333333",
    "cipherText": "BASE64_ENCRYPTED_MESSAGE"
  }'
```

## Architecture

### Security Model (MVP)
- No user authentication (sender is UUID.randomUUID() placeholder)
- Server never decrypts; only stores/relays ciphertext
- Client-side Signal Protocol encryption
- Future: JWT authentication with Spring Security

### Message Flow
1. **Direct Messages**: REST/WebSocket → Database → WebSocket to recipient
2. **Group Messages**: REST/WebSocket → Database → RabbitMQ → WebSocket to all group members

### WebRTC Signaling
- Offer/Answer/ICE candidates sent via WebSocket
- No server-side media processing
- Client-to-client direct connection

## Troubleshooting

### Common Issues
1. **Database Connection**: Ensure PostgreSQL is running (`docker compose up -d`)
2. **RabbitMQ Connection**: Check RabbitMQ is accessible on port 5672
3. **WebSocket Connection**: Verify CORS settings and WebSocket endpoint
4. **Port Conflicts**: Ensure ports 8080, 5432, 5672, 15672 are available

### Logs
```bash
# View application logs
./gradlew bootRun

# View Docker logs
docker compose logs postgres
docker compose logs rabbitmq
```

## License

This project is part of a messaging application development course.



