create schema devm211;

use devm211;

CREATE TABLE pessoa (
    idPessoa INT PRIMARY KEY AUTO_INCREMENT,
    nomePessoa VARCHAR(60) NOT NULL,
    cpf VARCHAR(20) UNIQUE,
    endereco VARCHAR(60),
    telefone VARCHAR(20),
    idade INT,
    status BOOLEAN
);

create table carro(
	idCarro int primary key auto_increment,
    placa varchar(8) unique not null,
    marca varchar(20),
    modelo varchar(30),
    anoFabricacao int,
    anoModelo int,
    cor varchar(10),
    nPortas int,
    idPessoa int,
    foreign key (idPessoa) references pessoa(idPessoa)
);

