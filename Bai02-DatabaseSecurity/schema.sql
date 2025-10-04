IF DB_ID('security6_db') IS NULL CREATE DATABASE security6_db;
GO
USE security6_db;
GO
IF OBJECT_ID('dbo.user_roles','U') IS NOT NULL DROP TABLE dbo.user_roles;
IF OBJECT_ID('dbo.roles','U') IS NOT NULL DROP TABLE dbo.roles;
IF OBJECT_ID('dbo.users','U') IS NOT NULL DROP TABLE dbo.users;
GO
CREATE TABLE dbo.roles(
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,
                          name NVARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE dbo.users(
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,
                          username NVARCHAR(100) NOT NULL UNIQUE,
                          password NVARCHAR(255) NOT NULL,
                          email NVARCHAR(255) NULL UNIQUE,
                          enabled BIT NOT NULL DEFAULT 1
);
CREATE TABLE dbo.user_roles(
                               user_id BIGINT NOT NULL,
                               role_id BIGINT NOT NULL,
                               CONSTRAINT PK_user_roles PRIMARY KEY(user_id, role_id),
                               CONSTRAINT FK_ur_user FOREIGN KEY(user_id) REFERENCES dbo.users(id),
                               CONSTRAINT FK_ur_role FOREIGN KEY(role_id) REFERENCES dbo.roles(id)
);