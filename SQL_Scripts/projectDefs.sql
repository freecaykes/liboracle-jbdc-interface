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

CREATE TABLE PizzaPlace
(
    branchID varchar(50),
    PRIMARY KEY (branchID)
);

CREATE TABLE Branch_IsIn
(
    address varchar(100) NOT NULL,
    PRIMARY KEY (address),
    userID INTEGER,
    FOREIGN KEY (userID) REFERENCES Users
);

ALTER TABLE Branch_IsIn
(
    ADD branchID INTEGER,
    FOREIGN KEY (branchID) REFERENCES PizzaPlace
);

CREATE TABLE PizzaOrder
(
    oid INTEGER,
    PRIMARY KEY (oid),
    deliveryMethod varchar(20) NOT NULL,
    branchID INTEGER,
    FOREIGN KEY (branchID) REFERENCES PizzaPlace,
    userID INTEGER,
    FOREIGN KEY (userID) REFERENCES Users,
    address varchar(100) NOT NULL,
    FOREIGN KEY (address) REFERENCES Branch_IsIn
);

CREATE TABLE Pizza
(
    pizzaType varchar(50) NOT NULL,
    PRIMARY KEY (pizzaType),
    price DOUBLE PRECISION,
    oid INTEGER,
    FOREIGN KEY (oid) REFERENCES PizzaOrder
);

CREATE TABLE PizzaPlaceBranchIsIn
(
    branchID INTEGER,
    FOREIGN KEY (branchID) REFERENCES PizzaPlace,
    address varchar(100) NOT NULL,
    FOREIGN KEY (address) REFERENCES Branch_IsIn
);
