drop user if exists 'admin'@'localhost';
drop user if exists 'admin'@'%';
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'Czy.190815';
GRANT ALL ON *.* TO 'admin'@'localhost' WITH GRANT OPTION;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'Czy.190815';
GRANT ALL ON *.* TO 'admin'@'%' WITH GRANT OPTION;