#--- !Ups
CREATE TABLE user (
	firstname varchar(50),
	lastname varchar(50),
	username varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(50) NOT NULL
);

INSERT INTO user (firstname, lastname, username, email, password) values ('javier', 'piris', 'jvrpiris', 'jvrpiris@gmail.com', 'tfg');

#--- !Downs
DROP TABLE IF EXISTS user;

DELETE user WHERE username = 'jvrpiris';