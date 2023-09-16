create table users(
    user_id int primary key IDENTITY(1,1) ,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(100),
    password varchar(255),
    image varchar(300),
    date_of_birth date
)