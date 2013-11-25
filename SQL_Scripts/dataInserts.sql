INSERT INTO Users VALUES
(
    'leonardo1',
    'Leonardo',
    'Turtle',
    '1234123412341234',
    '5CA888F39D61ADFE533B0C08BD9F884EE6FF83D69C1221491ECAD366DC56B646'
);

INSERT INTO Users VALUES
(
    'raphael2',
    'Raphael',
    'Turtle',
    '2345234523452345',
    'B1F51A511F1DA0CD348B8F8598DB32E61CB963E5FC69E2B41485BF99590ED75A'
);

INSERT INTO Users VALUES
(
    'michaelangelo3',
    'Michaelangelo',
    'Turtle',
    '3456345634563456',
    'C207B1B9E510364443DB9423B36BC5F16DF95DE58A544A64B9D80B0FEBA78065'
);

INSERT INTO Users VALUES
(
    'donatello4',
    'Donatello',
    'Turtle',
    '4567456745674567',
    '8E0A1B0ADA42172886FD1297E25ABF99F14396A9400ACBD5F20DA20289CFF02F'
);

INSERT INTO Users VALUES
(
    'mastersplinter10',
    'Master',
    'Splinter',
    '5678567856785678',
    '6B649D9C83A8E2E01B9B34F442AF5A25797EFE2187F9528DA0C481CDF4A4E1E0'
);

INSERT INTO PrivilegedUser VALUES
(
    1,
    'mastersplinter10'
);

INSERT INTO User_IsIn VALUES
(
    'Dojo, New York',
    'mastersplinter10',
    2
);

INSERT INTO User_IsIn VALUES
(
    'The Sewer Lair, New York',
    'leonardo1',
    1
);

INSERT INTO User_IsIn VALUES
(
    'Shredder Hideout, New York',
    'raphael2',
    3
);

INSERT INTO User_IsIn VALUES
(
    'Area 51, Nevada',
    'donatello4',
    5
);

INSERT INTO User_IsIn VALUES
(
    'The Sewer Lair, New York',
    'michaelangelo3',
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

INSERT INTO PizzaOrder VALUES
(
    1,
    'Boat',
    'Vegitarian',
    0,
    'michaelangelo3',
    'The Sewer Lair, New York'
);

INSERT INTO PizzaOrder VALUES
(
    2,
    'Boat',
    'Cheese',
    0,
    'leonardo1',
    'The Sewer Lair, New York'
);

INSERT INTO PizzaOrder VALUES
(
    3,
    'Boat',
    'Hawaiian',
    1,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    4,
    'Car',
    'Pepperoni',
    1,
    'mastersplinter10',
    'Dojo, New York'
);

INSERT INTO PizzaOrder VALUES
(
    5,
    'Boat',
    'Cheese',
    1,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    6,
    'Boat',
    'Pepperoni',
    0,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    7,
    'Car',
    'Meat Lover',
    1,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    8,
    'Boat',
    'Pepperoni',
    0,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    9,
    'Car',
    'Cheese',
    0,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    10,
    'Boat',
    'Meat Lover',
    1,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    11,
    'Car',
    'Meat Lover',
    1,
    'raphael2',
    'Shredder Hideout, New York'
);

INSERT INTO PizzaOrder VALUES
(
    12,
    'Boat',
    'Pepperoni',
    0,
    'raphael2',
    'Shredder Hideout, New York'
);
