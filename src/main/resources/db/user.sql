-- create user with name 
CREATE USER 'hrms'@'localhost' IDENTIFIED BY 'hrms';

-- provide all the permission
GRANT ALL PRIVILEGES ON * . * TO 'hrms'@'localhost';

-- create password 
ALTER USER 'hrms'@'localhost' IDENTIFIED WITH mysql_native_password BY 'hrms';