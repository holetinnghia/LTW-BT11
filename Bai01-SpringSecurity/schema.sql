USE security6_basic;
-- tạo users/authorities nếu chưa có
IF OBJECT_ID('dbo.users','U') IS NULL
    BEGIN
        CREATE TABLE dbo.users(username NVARCHAR(100) PRIMARY KEY, password NVARCHAR(255) NOT NULL, enabled BIT NOT NULL);
    END
IF OBJECT_ID('dbo.authorities','U') IS NULL
    BEGIN
        CREATE TABLE dbo.authorities(username NVARCHAR(100) NOT NULL, authority NVARCHAR(100) NOT NULL,
                                     CONSTRAINT PK_authorities PRIMARY KEY(username,authority),
                                     CONSTRAINT FK_authorities_users FOREIGN KEY(username) REFERENCES dbo.users(username));
    END

-- reset dữ liệu tối thiểu
DELETE FROM dbo.authorities WHERE username IN (N'admin',N'user');
DELETE FROM dbo.users       WHERE username IN (N'admin',N'user');

INSERT INTO dbo.users(username,password,enabled) VALUES
                                                     (N'admin', N'{noop}admin123', 1),
                                                     (N'user',  N'{noop}user123',  1);

INSERT INTO dbo.authorities(username,authority) VALUES
                                                    (N'admin', N'ROLE_ADMIN'),
                                                    (N'admin', N'ROLE_USER'),
                                                    (N'user',  N'ROLE_USER');