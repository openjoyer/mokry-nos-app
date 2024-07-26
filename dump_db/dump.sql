CREATE DATABASE `mokriy-nos-app-db`;
CREATE TABLE `mokriy-nos-app-db`.messages (
                            id int NOT NULL AUTO_INCREMENT,
                            title varchar(50) NOT NULL,
                            type int NOT NULL,
                            text varchar(1000) NOT NULL,
                            created_date timestamp NULL DEFAULT NULL,
                            person_name varchar(100) NOT NULL,
                            is_marked tinyint(1) DEFAULT NULL,
                            PRIMARY KEY (id),
                            UNIQUE KEY id (id),
                            UNIQUE KEY title (title)
);

INSERT INTO `mokriy-nos-app-db`.messages VALUES (1,'Пример сообщения',2,'При ненадобности удалить','2024-07-24 17:08:16','Иванов Иван',0);

CREATE TABLE `mokriy-nos-app-db`.users (
                         id int NOT NULL AUTO_INCREMENT,
                         username varchar(50) NOT NULL,
                         password varchar(100) NOT NULL,
                         role varchar(20) NOT NULL,
                         PRIMARY KEY (id),
                         UNIQUE KEY id (id),
                         UNIQUE KEY username (username)
);

INSERT INTO `mokriy-nos-app-db`.users VALUES (1,'admin','$2a$10$AGQ7eTmySwy1cvAPdVC5k.B9kCe3cytctQ1TgpiGfPLUM2CKcGYVO','ROLE_ADMIN');
