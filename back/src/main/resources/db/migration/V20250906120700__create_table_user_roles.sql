CREATE TABLE tab_user_roles (
    user_id BIGINT NOT NULL,
    role_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);