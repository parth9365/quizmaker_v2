CREATE TABLE `quiztable` (
  `QuizId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CourseId` bigint(20) NOT NULL,
  `Title` varchar(216) NOT NULL,
  `Description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`QuizId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
