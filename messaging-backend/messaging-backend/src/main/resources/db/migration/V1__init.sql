-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- App users table
CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username TEXT UNIQUE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Device keys for Signal Protocol
CREATE TABLE device_key (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    device_id TEXT NOT NULL,
    identity_key_public TEXT NOT NULL,
    signed_prekey_public TEXT NOT NULL,
    one_time_prekey_public TEXT,
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    UNIQUE(user_id, device_id)
);

-- Direct messages (encrypted)
CREATE TABLE direct_message (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sender_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    receiver_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    ciphertext_base64 TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Chat groups
CREATE TABLE chat_group (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Group members
CREATE TABLE group_member (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    group_id UUID NOT NULL REFERENCES chat_group(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    UNIQUE(group_id, user_id)
);

-- Group messages (encrypted)
CREATE TABLE group_message (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    group_id UUID NOT NULL REFERENCES chat_group(id) ON DELETE CASCADE,
    sender_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    ciphertext_base64 TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Create indexes for better performance
CREATE INDEX idx_direct_message_receiver_id ON direct_message(receiver_id);
CREATE INDEX idx_direct_message_created_at ON direct_message(created_at);
CREATE INDEX idx_group_message_group_id ON group_message(group_id);
CREATE INDEX idx_group_message_created_at ON group_message(created_at);
CREATE INDEX idx_device_key_user_id ON device_key(user_id);
CREATE INDEX idx_group_member_group_id ON group_member(group_id);
CREATE INDEX idx_group_member_user_id ON group_member(user_id);