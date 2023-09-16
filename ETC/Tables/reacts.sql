create table reacts(
pid int FOREIGN KEY References post(post_id) ON DELETE CASCADE,
uid int FOREIGN KEY References users(user_id),
type int not null,
PRIMARY KEY (pid,uid)
)