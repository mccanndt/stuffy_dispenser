-- create and select the database
DROP DATABASE IF EXISTS stuffyDB;
CREATE DATABASE stuffyDB;
USE stuffyDB;

-- create the Stuffy table
CREATE TABLE Stuffy (
  ID		INT				PRIMARY KEY	AUTO_INCREMENT,
  Type		VARCHAR(20)		NOT NULL	UNIQUE,
  Color		VARCHAR(10)		NOT NULL,
  Size		VARCHAR(10)		NOT NULL,
  Limbs		INT
);

-- insert some rows into the Product table
INSERT INTO Stuffy VALUES
(1, "Dog", "Blue", "Large", 4),
(2, "Cat", "Green", "Small", 4),
(3, "Dragon", "Purple", "Medium", 6),
(4, "Snail", "Yellow", "X-Large", 1),
(5, "Platypus", "Blue", "Medium", 4),
(6, "Octopus", "Purple", "Large", 8),
(7, "Squirrel", "Brown", "Small", 4),
(8, "Starfish", "Pink", "X-Large", 5),
(9, "Lobster", "Red", "Large", 12),
(10, "Spider", "Clear", "Small", 8);

-- create a user and grant privileges to that user
CREATE USER IF NOT EXISTS stuffyDB_user@localhost IDENTIFIED BY 'sesame';
GRANT SELECT, INSERT, DELETE, UPDATE ON stuffyDB.* TO stuffyDB_user@localhost;
