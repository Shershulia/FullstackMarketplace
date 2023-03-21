
--creates the userdatabase table, for storing user information
CREATE TABLE IF NOT EXISTS userdata (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

--inserts some dummy data into the userdatabase table for testing purposes
INSERT INTO userdata (username, name, phone, email, password)
VALUES
  ('johndoe', 'John Doe', '123-456-7890', 'johndoe@example.com', 'password123'),
  ('janedoe', 'Jane Doe', '555-555-5555', 'janedoe@example.com', '123456789'),
  ('bobsmith', 'Bob Smith', '555-123-4567', 'bobsmith@example.com', 'abc123'),
  ('ole1', 'Ole Nordmann', '123456789', 'ole@example.com', 'password1'),
  ('sarahjones', 'Sarah Jones', NULL, 'sarahjones@example.com', 'passw0rd');


--create more tables here if needed