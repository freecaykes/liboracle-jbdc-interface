CREATE TABLE Users
(
    userID INTEGER,
    PRIMARY KEY (userID),
    name varchar(70) NOT NULL,
    cardNumber char(16) NOT NULL
);

CREATE TABLE PrivilegedUser
(
    adminLevel INTEGER,
    userID INTEGER,
    FOREIGN KEY (userID) REFERENCES Users
);

CREATE TABLE User_IsIn
(
    address varchar(100) NOT NULL,
    userID INTEGER NOT NULL,
    distance INTEGER,
    PRIMARY KEY (address,userID),
    FOREIGN KEY (userID) REFERENCES Users,
    CONSTRAINT location_id PRIMARY KEY (address,userID),
);

CREATE TABLE PizzaOrder
(
    oid INTEGER,
    PRIMARY KEY (oid),
    deliveryMethod varchar(20) NOT NULL,
    pizzaType varchar(20) NOT NULL,
    userID INTEGER,
    FOREIGN KEY (userID) REFERENCES Users,
    address varchar(100) NOT NULL,
    FOREIGN KEY (address,userID) REFERENCES User_IsIn
);

CREATE TABLE Pizza
(
    pizzaType varchar(50) NOT NULL,
    PRIMARY KEY (pizzaType),
    price DOUBLE PRECISION
);
