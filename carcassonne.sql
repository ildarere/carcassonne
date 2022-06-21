-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               10.6.5-MariaDB - mariadb.org binary distribution
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных carcassonne
CREATE DATABASE IF NOT EXISTS `carcassonne` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `carcassonne`;

-- Дамп структуры для таблица carcassonne.friends
CREATE TABLE IF NOT EXISTS `friends` (
  `first_id` int(11) NOT NULL,
  `second_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы carcassonne.friends: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` (`first_id`, `second_id`) VALUES
	(1, 2),
	(1, 12),
	(12, 2),
	(1, 13),
	(14, 1),
	(14, 2),
	(1, 1),
	(2, 25),
	(2, 21);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;

-- Дамп структуры для таблица carcassonne.rooms
CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `max_size` int(11) NOT NULL DEFAULT 0,
  `ready` int(11) DEFAULT 0,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы carcassonne.rooms: ~11 rows (приблизительно)
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` (`id`, `max_size`, `ready`, `name`) VALUES
	(1, 4, 0, 'testRoom'),
	(9, 3, 0, 'rooo'),
	(10, 4, 0, 'lildar'),
	(16, 4, 0, 'lildarxfa'),
	(17, 4, 0, 'lildar22'),
	(18, 4, 0, 's23'),
	(19, 4, 0, 's3241'),
	(20, 4, 0, 's4125416564'),
	(21, 4, 0, 'sadfasf'),
	(22, 4, 0, 'lildar2'),
	(23, 4, 0, 'fdsf');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;

-- Дамп структуры для таблица carcassonne.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(320) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `enabled` int(11) DEFAULT 1,
  `token` varchar(32) DEFAULT NULL,
  `roles` varchar(128) DEFAULT 'USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;

-- Дамп данных таблицы carcassonne.user: ~18 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `name`, `password`, `enabled`, `token`, `roles`) VALUES
	(1, 'admin@demo.loc', 'Admin', '$2a$10$oIgilrenfZgb7E5S0jZZ2urA1S/wHSJhXztj3M8PhtOn0e3UefuS.', 1, NULL, 'ADMIN,USER'),
	(2, 'maxutov999@gmail.com', 'lildar', '$2a$10$scOajpFfonh/HTXpY.7SkuHopV1scmvtUrDnwLETx2mJhOwRgTPIK', 1, NULL, 'USER'),
	(12, 'maxutov29241@gmail.com', '1232', '$2a$10$9BXURgYzaPa2lL5ckpWwuuubpEyuTJqnNuX2W6sYZt4QI.YTOREja', 1, NULL, 'USER'),
	(13, 'ma@gmail.com', '1234', '$2a$10$69BbBfXIGcLmxJEeHdbolODF5eRznkuH7gxxyR4/bEPaxBxQ1Refy', 1, NULL, 'USER'),
	(14, 'a@gmail.com', 'qwert', '$2a$10$4pMy0L9l6jOYBUVulJ3JQeAMpaXL3yi8liDBCyikHaFVZGWufkySa', 1, NULL, 'USER'),
	(15, 'w@gmailcom', '12345', '$2a$10$nfrIj7tgkap3kqzOoWJd9.TtGbsjnhEjap4bn8./ffcs/DtYxkbOS', 1, NULL, 'USER'),
	(16, 'q@gmail.com', '123456', '$2a$10$po1.RqMSbYu7vC6wZDtAcuEeqTQZMjKZVB6W5WNrJPvM6q5EEyPpe', 0, NULL, 'USER'),
	(17, 'r@mail.com', '123456', '$2a$10$jQmak2l2b3WYXtZOUBolaO9RX1FhXMQMai4A/hXwhhdI74A.QnE62', 1, NULL, 'USER'),
	(18, 'ww@mail.com', '1423545', '$2a$10$3jPCLBVfGIoMjG0KUxT8bOrev3lf1FI784LwJxiq72dlFrJfdM3zu', 1, NULL, 'USER'),
	(19, '12@gmail.com', '23123', '$2a$10$BztnSLEeuuxhkHQyAI4nLeL.h.CVo0oBo4WpZlZh65Wr6fm.NL5zm', 1, NULL, 'USER'),
	(20, 'r2@gmail.com', '123322', '$2a$10$Z5RBbcg3j2P.1ahwZNs8CeFmOwh5WBBXCVgN2fk.am.Jd3W3PsIS.', 1, NULL, 'USER'),
	(21, '14@f.c', 'qwe124', '$2a$10$rbs/wz5xUfYmXd4iq6FvtOHE1ZvcIQ/bvN2E/8tUqdZpAW5EPYBv2', 1, NULL, 'USER'),
	(22, '2@d.d', '12452', '$2a$10$yybYBnyqh1MUauAA8qBZEOhXSf.JErICigpLEEmv296XYfSBFXEVW', 1, NULL, 'USER'),
	(23, 'f@c.c', 'cccccccccc', '$2a$10$8y5.xTOnFTIbdwfDIyGpBePX3dg8dOa/TJqsWaKjqPYVFczCT8sKW', 1, NULL, 'USER'),
	(24, '1@d.d', 'qwerty', '$2a$10$vAj7Aq78hCm8eMsU27ADwOE2giAkW2Lrf17QNFgjBQzdbfOnQiCJC', 1, NULL, 'USER'),
	(25, '1@d.s', 'aaasdada', '$2a$10$2myIt2qSQpTrHtU3CE.ItO5jNUX6Xd6QoF46EvzN4tp5nWCBBYmku', 1, NULL, 'USER'),
	(26, 's@dc.c', '1244545', '$2a$10$EuDuGNDvvC15DP4jHoKtxuNeDNNGuBolkvFwT8HUiSy6OgNwjpDt6', 0, NULL, 'USER'),
	(27, '1@sd.com', '12345', '$2a$10$tJPb98rZ2Z2UyMLvuEDHFezqte5qzqfD0pdv0uR4dmsRm1f9q8bHu', 1, NULL, 'USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Дамп структуры для таблица carcassonne.users_in_rooms
CREATE TABLE IF NOT EXISTS `users_in_rooms` (
  `roomId` int(11) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы carcassonne.users_in_rooms: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `users_in_rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_in_rooms` ENABLE KEYS */;

-- Дамп структуры для таблица carcassonne.user_information
CREATE TABLE IF NOT EXISTS `user_information` (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `rating` int(5) DEFAULT NULL,
  `games_count` int(7) DEFAULT NULL,
  `wins` int(7) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы carcassonne.user_information: ~18 rows (приблизительно)
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` (`id`, `rating`, `games_count`, `wins`, `name`) VALUES
	(1, 0, 0, 0, 'Admin'),
	(2, 0, 0, 0, 'lildar'),
	(12, 0, 0, 0, '1232'),
	(13, 0, 0, 0, '1234'),
	(14, 0, 0, 0, 'qwert'),
	(15, 0, 0, 0, '12345'),
	(16, 0, 0, 0, '123456'),
	(17, 0, 0, 0, '123456'),
	(18, 0, 0, 0, '1423545'),
	(19, 0, 0, 0, '23123'),
	(20, 0, 0, 0, '123322'),
	(21, 0, 0, 0, 'qwe124'),
	(22, 0, 0, 0, '12452'),
	(23, 0, 0, 0, 'cccccccccc'),
	(24, 0, 0, 0, 'qwerty'),
	(25, 0, 0, 0, 'aaasdada'),
	(26, 0, 0, 0, '1244545'),
	(27, 0, 0, 0, '12345');
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
