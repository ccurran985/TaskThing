DROP DATABASE IF EXISTS wednesdayTaskThing;

use wednesdayTaskThing;

CREATE TABLE users (username varchar(20) PRIMARY KEY,
	fName varchar(20), lName varchar(20), password varchar(20),
	isAdmin tinyint, isBlocked tinyint);

INSERT VALUES("Admin", "Admin", "", "password", 1, 0);

