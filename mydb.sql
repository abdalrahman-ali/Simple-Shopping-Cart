CREATE DATABASE if not exists `mydb` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prodname` varchar(45) NOT NULL,
  `proddisc` varchar(45) DEFAULT NULL,
  `prodprice` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isadmin` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


insert into users values (1,"admin","admin",1),(2,"user","user",0);


insert into products values
  (1,"Steel Knife","a knife made of high quality stainless steel",5.95),
  (2,"wooden Knife","a knife made of wood",3.45),
  (3,"valyrian Knife","Ned Stark's knife",199.95),
  (4,"Katana","a very sharp samurai sword",50.95);
