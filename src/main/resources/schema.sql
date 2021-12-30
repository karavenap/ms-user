 CREATE TABLE IF NOT EXISTS user (
uuid varchar (40) not null,
name varchar (50),
email varchar (60) NOT NULL,
password varchar (70) NOT NULL,
created datetime NOT NULL,
isActive boolean NOT NULL,
token varchar(100) NOT NULL,
lastLogin datetime not null,
PRIMARY KEY (uuid)
);

 CREATE TABLE IF NOT EXISTS phone (
    number varchar,
    citycode varchar (20) NOT NULL,
    countrycode varchar (20) NOT NULL,
    uuid varchar (40) not null
);


