-- init.sql: create database and user for local development
CREATE DATABASE IF NOT EXISTS `microservicios_db`;
CREATE USER IF NOT EXISTS 'ms_user'@'%' IDENTIFIED BY 'ms_pass';
GRANT ALL PRIVILEGES ON `microservicios_db`.* TO 'ms_user'@'%';
FLUSH PRIVILEGES;
