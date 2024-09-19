
-- Criação do banco de dados (Execução feita em MySQL)
CREATE DATABASE ComercioTI

USE ComercioTI;

-- Criando as tabelas do banco de dados
CREATE TABLE Fornecedor (
    Codigo char(3) PRIMARY KEY,
     Nome char(20)
);
        
create table categoria (
    Codigo char(3) PRIMARY KEY,
    Nome char(15)
);

    -- Assim o campo nome não aceitara inserções nulas
    CREATE TABLE Categoria(
        Codigo char(3) PRIMARY KEY,
        Nome char(15) NOT NULL
    );


create table Produto (
    CodigoProduto char(3) PRIMARY KEY,
    NomeProduto char(30),
    PrecoProduto numeric(6,2),
    Categoria char(3),
    Fornecedor char(3),
    CONSTRAINT FK_Produto_Categoria FOREIGN KEY (Categoria) REFERENCES categoria(Codigo)

);


-- Criando as tabelas no PostgreSQL

-- CREATE TABLE fornecedor (
--     codigo VARCHAR(3) PRIMARY KEY,
--     nome VARCHAR(20)
-- );

-- CREATE TABLE categoria (
--     codigo VARCHAR(3) PRIMARY KEY,
--     nome VARCHAR(15)
-- );

-- CREATE TABLE produto (
--     codigoproduto VARCHAR(3) PRIMARY KEY,
--     nomeproduto VARCHAR(30),
--     precoproduto NUMERIC(6,2),
--     categoria VARCHAR(3),
--     fornecedor VARCHAR(3),
--     CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria) REFERENCES categoria(codigo),
--     CONSTRAINT fk_produto_fornecedor FOREIGN KEY (fornecedor) REFERENCES fornecedor(codigo)
-- );
