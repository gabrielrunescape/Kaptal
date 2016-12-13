-- CURRENT VERSION APLICATION 0.2 --
CREATE TABLE IF NOT EXISTS `Transaction` (
    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Description VARCHAR(16) NOT NULL,
    Amount REAL NOT NULL,
    Category INTEGER NOT NULL,
    Date_transaction DATE NULL
);

CREATE TABLE IF NOT EXISTS `Category` (
    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Description VARCHAR(16) NOT NULL,
    Operation CHAR NOT NULL
);

INSERT INTO `Category` VALUES (1, 'Compras', '-');
INSERT INTO `Category` VALUES (2, 'Pagamentos', '-');
INSERT INTO `Category` VALUES (3, 'Remuneração', '+');
INSERT INTO `Category` VALUES (4, 'Serviços', '+');