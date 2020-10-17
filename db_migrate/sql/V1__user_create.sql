use `filmood`;

create table `user`
(
    `id` int auto_increment not null ,
    `username` varchar(32) not null,
    `password` varchar(128) not null,
    `email` varchar(50) not null,
    `birthdate` date default null,
    `fullname` varchar(150) default null,

    constraint `user_primary_key` primary key (`id`),
    constraint `user_unique` unique (`id`, `username`, `email`),
    constraint `user_check` check ( (`username` != '')
        and (`password` != '')
        and (`email` != ''))
);