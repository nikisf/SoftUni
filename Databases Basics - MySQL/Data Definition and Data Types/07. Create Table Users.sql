create table `users`(
`id` int primary key auto_increment,
`username` varchar(30),
`password` varchar(26),
`profile_picture` blob null,
`last_login_time` time null,
`is_deleted` boolean
);

INSERT INTO `users`
(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES
(1, 'gosho', 'asdf', null, null, '0'),
(2, 'ivan', 'asdf', null, null, '1'),
(3, 'pesho', 'asdf', null, null, '0'),
(4, 'tosho', 'asdf', null, null, '1'),
(5, 'bla', 'asdf', null, null, '0');