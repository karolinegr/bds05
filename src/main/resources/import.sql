INSERT INTO tb_user (name, email, password) VALUES ('Bob', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_genre (name) VALUES ('Comédia');
INSERT INTO tb_genre (name) VALUES ('Ação');

INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_id) VALUES ('Minha mãe é uma peça', null, 2013, 'https://m.media-amazon.com/images/I/71-VEjLwx8L._AC_UF894,1000_QL80_.jpg', 'Minha mãe é uma peça', 1);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_id) VALUES ('Duro de matar', null, 1988, 'https://br.web.img2.acsta.net/medias/nmedia/18/92/25/88/20188922.jpg', 'Duro de matar', 2);

INSERT INTO tb_review (text, user_id, movie_id) VALUES ('Muito bom',1,1);
INSERT INTO tb_review (text, user_id, movie_id) VALUES ('Não gostei',1,2);
INSERT INTO tb_review (text, user_id, movie_id) VALUES ('Engraçado demais, amei',2,1);
