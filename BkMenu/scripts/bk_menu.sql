CREATE TABLE `bk_menu` (
  `id` int(11) NOT NULL,
  `name` varchar(57) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(113) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;