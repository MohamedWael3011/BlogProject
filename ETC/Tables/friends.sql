create table friends (
    user_id1 int FOREIGN KEY REFERENCES users(user_id),
    user_id2 int FOREIGN KEY REFERENCES users(user_id)
)