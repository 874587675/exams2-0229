-- 检查是否存在 pub 数据库并创建它
CREATE DATABASE IF NOT EXISTS pub;

-- 切换到 pub 数据库
USE pub;

-- 创建 users 表
CREATE TABLE IF NOT EXISTS users
(
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    user_name     VARCHAR(20) NOT NULL UNIQUE,
    user_password VARCHAR(20) NOT NULL,
    email         VARCHAR(30) NOT NULL UNIQUE,
    created_time  DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 创建 friends 表
CREATE TABLE IF NOT EXISTS friends
(
    user_id      INT,
    friend_id    INT,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (friend_id) REFERENCES users (user_id)
);

-- 创建 posts 表
CREATE TABLE IF NOT EXISTS posts
(
    post_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT,
    content      TEXT,
    location     VARCHAR(30),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);-- DDL sql 记录到这里
