create table users(
    user_id int primary key IDENTITY(1,1) ,
    username varchar(255),
    email varchar(100),
    password varchar(255),
	bio varchar(300),
	phone varchar(20),
    image varchar(300),
	facebook_username varchar(30)
)