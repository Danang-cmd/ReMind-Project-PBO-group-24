CREATE DATABASE concentration;
USE concentration;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    score INT,
    play_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
