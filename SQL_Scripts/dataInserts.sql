INSERT INTO Users VALUES
(
    0001,
    'Leonardo',
    'TMNT001'
);

INSERT INTO Users VALUES
(
    0002,
    'Raphael',
    'TMNT002'
);

INSERT INTO Users VALUES
(
    0003,
    'Michaelangelo',
    'TMNT003'
);

INSERT INTO Users VALUES
(
    0004,
    'Donatello',
    'TMNT005'
);

INSERT INTO Users VALUES
(
    0010,
    'Master Splinter',
    'MASTERCARD0010'
);

INSERT INTO PrivilegedUser VALUES
(
    1,
    0010
);

INSERT INTO User_IsIn VALUES
(
    'Dojo, New York',
    0010,
    2
);

INSERT INTO User_IsIn VALUES
(
    'The Sewer Lair, New York',
    0001,
    1	
);

INSERT INTO User_IsIn VALUES
(
    'Shredder Hideout, New York',
    0002,
    3
);

INSERT INTO User_IsIn VALUES
(
    'Area 51, Nevada',
    0004,
    5
);

INSERT INTO User_IsIn VALUES
(
   'The Sewer Lair, New York',
    0003,
    1	 
);

INSERT INTO Pizza VALUES
(
    'Pepperoni',
    19.99
);

INSERT INTO Pizza VALUES
(
    'Hawaiian',
    20.99
);

INSERT INTO Pizza VALUES
(
    'Vegitarian',
    17.99
);

INSERT INTO Pizza VALUES
(
    'Meat Lover',
    21.99
);

INSERT INTO Pizza VALUES
(
    'Cheese',
    15.99
);
