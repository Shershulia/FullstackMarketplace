INSERT INTO USERDATA (username, email, password, name, lastname, age,permission)
VALUES ('ole1', 'john.doe@example.com', 'Asdf1234', 'John', 'Doe', 30,'normal');
INSERT INTO ITEMDATA (name, description, userid, price, location, image, categories, metadata)
VALUES ('Example Item', 'This is an example item', 1, 1000, 'Oslo', 'https://i.ebayimg.com/00/s/MTAyNFg3Njg=/z/QW0AAOSwanRXghxY/$_86.JPG',ARRAY ['Home and Garden','Health and Beauty'], 'Example Metadata 1, Example Metadata 2');
INSERT INTO ITEMDATA (name, description, userid, price, location, image, categories, metadata)
VALUES ('Example Item2', 'This is an example item2', 1, 1000, 'Trondheim', 'https://www.gamersdecide.com/sites/default/files/authors/u18518/DSC0490-Edit_1.jpg', 'Sports and Outdoors', 'Example Metadata 1, Example Metadata 2');

INSERT INTO USERDATA (username, email, password, name, lastname, age,permission)
VALUES ('admin', 'admin@example.com', 'admin', 'John', 'Doe', 30,'admin');
