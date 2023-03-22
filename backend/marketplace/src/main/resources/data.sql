
--creates the userdatabase table, for storing user information
DROP TABLE IF EXISTS USERDATA;
CREATE TABLE USERDATA (
--   id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(20),
  PRIMARY KEY (username)
);

--inserts some dummy data into the userdatabase table for testing purposes
INSERT INTO USERDATA VALUES ('ole1', '123', 'Ole Olsen', 'ole@mail.com', '12345678');
INSERT INTO USERDATA VALUES ('kari1', '123', 'Kari Olsen', 'kari@mail.com', '87654321');