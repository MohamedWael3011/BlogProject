create table post(
post_id int IDENTITY(1,1) primary key,
user_id int foreign key references users(user_id)  ON DELETE CASCADE,
title varchar(150),
content varchar(255),
create_time datetime,
image_src  varchar(255),
privacy_status varchar(10),
shared_id int  null foreign key references post(post_id)
)
