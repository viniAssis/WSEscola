# API REST WSEscola #


	API desenvolvida com JAX-RS e Maven  na IDE Netbeans 11.3 .


## URIs ##
	
	**GET:**  http://localhost:8080/WSEscola/webresources/estudante/{matricula}
		- matricula
		
	**POST:** http://localhost:8080/WSEscola/webresources/estudante/excluir
		- matricula
		
	**POST:** http://localhost:8080/WSEscola/webresources/estudante/editar
		- matricula
		- nome
		- sobrenome
		- matriculaNova ( se esse parâmentro estiver vazio, não atualizará o numero da matricula)
		
	**POST:** http://localhost:8080/WSEscola/webresources/estudante/salvar
		- matricula
		- nome
		- sobrenome
	

## Banco de dados ##
	```shell
	CREATE DATABASE  IF NOT EXISTS `escola` ;
	USE `escola`;


	DROP TABLE IF EXISTS `estudante`;

	CREATE TABLE `estudante` (
	  `matricula` varchar(100) NOT NULL,
	  `nome` varchar(100) NOT NULL,
	  `sobrenome` varchar(100) NOT NULL,
	  PRIMARY KEY (`matricula`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de estudantes';
	```