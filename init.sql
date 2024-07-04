
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 12:34:56', '2024-07-01 12:34:56', 1, 'Post content 1', 'Post Title 1', 10);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 13:34:56', '2024-07-01 13:34:56', 2, 'Post content 2', 'Post Title 2', 20);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 14:34:56', '2024-07-01 14:34:56', 3, 'Post content 3', 'Post Title 3', 30);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 15:34:56', '2024-07-01 15:34:56', 4, 'Post content 4', 'Post Title 4', 40);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 16:34:56', '2024-07-01 16:34:56', 5, 'Post content 5', 'Post Title 5', 50);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 17:34:56', '2024-07-01 17:34:56', 1, 'Post content 6', 'Post Title 6', 60);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 18:34:56', '2024-07-01 18:34:56', 2, 'Post content 7', 'Post Title 7', 70);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 19:34:56', '2024-07-01 19:34:56', 3, 'Post content 8', 'Post Title 8', 80);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 20:34:56', '2024-07-01 20:34:56', 4, 'Post content 9', 'Post Title 9', 90);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 21:34:56', '2024-07-01 21:34:56', 5, 'Post content 10', 'Post Title 10', 100);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 22:34:56', '2024-07-01 22:34:56', 1, 'Post content 11', 'Post Title 11', 110);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-01 23:34:56', '2024-07-01 23:34:56', 2, 'Post content 12', 'Post Title 12', 120);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 00:34:56', '2024-07-02 00:34:56', 3, 'Post content 13', 'Post Title 13', 130);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 01:34:56', '2024-07-02 01:34:56', 4, 'Post content 14', 'Post Title 14', 140);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 02:34:56', '2024-07-02 02:34:56', 5, 'Post content 15', 'Post Title 15', 150);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 03:34:56', '2024-07-02 03:34:56', 1, 'Post content 16', 'Post Title 16', 160);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 04:34:56', '2024-07-02 04:34:56', 2, 'Post content 17', 'Post Title 17', 170);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 05:34:56', '2024-07-02 05:34:56', 3, 'Post content 18', 'Post Title 18', 180);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 06:34:56', '2024-07-02 06:34:56', 4, 'Post content 19', 'Post Title 19', 190);
INSERT INTO post (created_at, modified_at, users_id, content, title, likes) VALUES ('2024-07-02 07:34:56', '2024-07-02 07:34:56', 5, 'Post content 20', 'Post Title 20', 200);


select c1_0.id,c1_0.content,c1_0.created_at,c1_0.like_count,c1_0.modified_at,c1_0.post_id,u1_0.id,u1_0.created_at,u1_0.modified_at,u1_0.name,u1_0.password,u1_0.password1,u1_0.password2,u1_0.password3,u1_0.refresh_token,u1_0.status,u1_0.username from comment c1_0 left join likes l1_0 on c1_0.id=l1_0.content_id left join users u1_0 on u1_0.id=c1_0.users_id where c1_0.users_id=4 order by c1_0.created_at desc limit 0,5;

select c1_0.id,c1_0.content,c1_0.created_at,c1_0.like_count,c1_0.modified_at,c1_0.post_id,u1_0.id,u1_0.created_at,u1_0.modified_at,u1_0.name,u1_0.password,u1_0.password1,u1_0.password2,u1_0.password3,u1_0.refresh_token,u1_0.status,u1_0.username from comment c1_0 left join likes l1_0 on c1_0.id=l1_0.content_id left join users u1_0 on u1_0.id=c1_0.users_id where l1_0.users_id=c1_0.users_id order by c1_0.created_at desc limit 5,5;

select c1_0.id,c1_0.content,c1_0.created_at,c1_0.like_count,c1_0.modified_at,c1_0.post_id,u1_0.id,u1_0.created_at,u1_0.modified_at,u1_0.name,u1_0.password,u1_0.password1,u1_0.password2,u1_0.password3,u1_0.refresh_token,u1_0.status,u1_0.username from comment c1_0 left join likes l1_0 on c1_0.id=l1_0.content_id left join users u1_0 on u1_0.id=c1_0.users_id where l1_0.users_id=4 order by c1_0.created_at desc limit 0,5;
