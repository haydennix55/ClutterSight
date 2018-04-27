# Create Testuser
ALTER USER 'root'@'localhost' IDENTIFIED BY 'CUBigD@t@18';

# Create db
CREATE DATABASE IF NOT EXISTS ClutterSight;
USE ClutterSight;

# Create table
CREATE TABLE IF NOT EXISTS tweets( id int NOT NULL AUTO_INCREMENT, tweet text, sentiment int, sentiment_text varchar(255), location varchar(255), PRIMARY KEY (id) );
