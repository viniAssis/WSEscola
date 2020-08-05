CREATE DATABASE  IF NOT EXISTS `escola` ;
USE `escola`;


DROP TABLE IF EXISTS `estudante`;

CREATE TABLE `estudante` (
  `matricula` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sobrenome` varchar(100) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de estudantes';

