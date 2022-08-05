CREATE USER 'graphql'@'%'  IDENTIFIED WITH mysql_native_password;
SET PASSWORD FOR 'graphql'@'%'  = 'dev';

DROP  DATABASE IF EXISTS graphql;
CREATE DATABASE `graphql`
	DEFAULT CHARACTER SET utf8mb4
	COLLATE utf8mb4_unicode_ci;
USE `graphql`;

GRANT ALL PRIVILEGES ON `graphql`.* TO 'graphql'@'%';