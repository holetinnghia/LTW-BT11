IF DB_ID('security6_jwt') IS NULL CREATE DATABASE security6_jwt; GO
USE security6_jwt; GO
IF OBJECT_ID('user_roles','U') IS NOT NULL DROP TABLE user_roles;
IF OBJECT_ID('roles','U') IS NOT NULL DROP TABLE roles;
IF OBJECT_ID('users','U') IS NOT NULL DROP TABLE users; GO

CREATE TABLE roles(id BIGINT IDENTITY PRIMARY KEY, name NVARCHAR(50) UNIQUE NOT NULL);
CREATE TABLE users(
                      id BIGINT IDENTITY PRIMARY KEY,
                      username NVARCHAR(100) UNIQUE NOT NULL,
                      password NVARCHAR(255) NOT NULL,
                      email NVARCHAR(255) NULL UNIQUE,
                      enabled BIT NOT NULL DEFAULT 1
);
CREATE TABLE user_roles(
                           user_id BIGINT NOT NULL, role_id BIGINT NOT NULL,
                           CONSTRAINT PK_user_roles PRIMARY KEY(user_id,role_id),
                           CONSTRAINT FK_ur_u FOREIGN KEY(user_id) REFERENCES users(id),
                           CONSTRAINT FK_ur_r FOREIGN KEY(role_id) REFERENCES roles(id)
);
INSERT INTO roles(name) VALUES (N'ROLE_ADMIN'),(N'ROLE_USER');
-- user thử nhanh (noop)
INSERT INTO users(username,password,enabled,email) VALUES
                                                       (N'admin', N'{noop}admin123',1,N'admin@example.com'),
                                                       (N'user',  N'{noop}user123', 1,N'user@example.com');
INSERT INTO user_roles SELECT u.id,r.id FROM users u JOIN roles r ON r.name IN('ROLE_ADMIN','ROLE_USER') WHERE u.username='admin';
INSERT INTO user_roles SELECT u.id,r.id FROM users u JOIN roles r ON r.name='ROLE_USER' WHERE u.username='user';