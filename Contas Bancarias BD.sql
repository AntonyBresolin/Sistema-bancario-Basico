CREATE SCHEMA IF NOT EXISTS `dbcontasbancarias` DEFAULT CHARACTER SET utf8 ;
USE `dbcontasbancarias`;

CREATE TABLE IF NOT EXISTS `dbcontasbancarias`.`conta`(
`idConta` INT NOT NULL auto_increment,
`nomeUsuario` varchar(45) not null,
`saldoUsuario` double ,
`loginUsuario` varchar(20) Not Null,
`senhaUsuario`varchar(20) Not Null,
primary key(`idConta`));


