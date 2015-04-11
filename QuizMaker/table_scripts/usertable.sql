CREATE TABLE `usertable` (
  `UserId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `quizmaker`.`usertable`
(`UserId`,
`UserName`,
`Password`,
`FirstName`,
`LastName`,
`Email`)
VALUES
('1', 
'admin',
'admin',
'admin',
'admin',
'parth.9365@gmail.com'
)