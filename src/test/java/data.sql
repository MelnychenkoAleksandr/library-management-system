DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname varchar(250) NOT NULL,
    lastname varchar(250) NOT NULL,
    address varchar(250) NOT NULL,
    contacts varchar(250) not null,
    userrole varchar(250) not null,
    takenbooks varchar(250) not null
);

INSERT INTO USER (firstname, lastname, address, contacts, userrole, takenbooks) VALUES
('Ivan', 'Petrov', 'Moscow, Rublevka st, 15-28', '12676789', 'librarian', ''),
('John', 'Johnson', 'NY, Merril st.1', '23456789', 'reader', '');

DROP TABLE IF EXISTS BOOKS;

CREATE TABLE BOOKS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(250) not null,
    author varchar(250) not null,
    isbn varchar(250) not null,
    description varchar(250) not null,
    available boolean not null
);

INSERT INTO BOOKS (name, author, isbn, description, available) VALUES
('Pinocchio', 'Carlo Collodi', '1234H', 'Story about the wooden boy and his father.', true),
('The Lord of the Rings', 'J. R. R. Tolkien', '12e4H', 'The greatest fantasy book.', true),
('The Hobbit, or There and Back Again', 'J. R. R. Tolkien', '12e4H', 'The greatest fantasy book.', true);
