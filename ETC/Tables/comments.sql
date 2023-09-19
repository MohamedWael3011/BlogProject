create table comments(
cid int IDENTITY(1,1) PRIMARY KEY,
pid int FOREIGN KEY references post(post_id) ON DELETE CASCADE,
uid int FOREIGN KEY references users(user_id),
content varchar(500) not null,
create_time datetime
)
