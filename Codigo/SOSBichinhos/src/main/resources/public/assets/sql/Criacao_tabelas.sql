-- Criando as tabelas do banco de dados

-- Tabela pessoa
CREATE TABLE pessoa (
    id_pessoa int NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    imagem VARCHAR(30) NOT NULL,
    moradia VARCHAR(15) NOT NULL CHECK (moradia IN ('Casa', 'Apartamento', 'Outros')),
    idade int NOT NULL,
    sexo char(1) NOT NULL CHECK (sexo IN ('M', 'F'))
);


-- Tabela Animal
CREATE TABLE animal (
    id_animal int NOT NULL PRIMARY KEY ,
    imagem VARCHAR(30) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sexo char (1) NOT NULL CHECK (sexo IN ('M', 'F')),
    idade VARCHAR(20) NOT NULL,
    castrado BOOLEAN NOT NULL,
    raca VARCHAR(30) NOT NULL,
    vacinas VARCHAR(50) NOT NULL,
    historia text,
    porte CHAR(1) NOT NULL CHECK (sexo IN ('P', 'M', 'G')),
    especie VARCHAR(30) NOT NULL
);

-- Tabela comentario
CREATE TABLE comentario(
    id_comentario int NOT NULL PRIMARY KEY,
    conteudo text,
    
    id_animal int NOT NULL,
    CONSTRAINT FK_Comentario_Animal FOREIGN KEY (id_animal) REFERENCES animal(id_animal),
    id_pessoa int NOT NULL,
    CONSTRAINT FK_Comentario_Pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);


-- Tabela formulario
CREATE TABLE formulario (
    id_formulario  int NOT NULL PRIMARY KEY,
    animal_sozinho VARCHAR(30),
    familia_ciente BOOLEAN NOT NULL,
    permissao BOOLEAN NOT NULL,
    teve_animal BOOLEAN NOT NULL,

    id_animal int NOT NULL,
    CONSTRAINT FK_formulario_Animal FOREIGN KEY (id_animal) REFERENCES animal(id_animal),
    id_pessoa int NOT NULL,
    CONSTRAINT FK_formulario_Pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);


-- Tabela tagsAnimal
CREATE TABLE tagsAnimal (
   id_tagAnimal int NOT NULL PRIMARY KEY,
   conteudo_tag VARCHAR(15),

   id_animal int NOT NULL,
   CONSTRAINT FK_tagsAnimal_animal FOREIGN KEY (id_animal) REFERENCES animal(id_animal)

);

-- Tabela tagPessoas
CREATE TABLE tagsPessoa (
   id_tagPessoa int NOT NULL PRIMARY KEY,
   conteudo_tag VARCHAR(15),

   id_pessoa int NOT NULL,
   CONSTRAINT FK_tagsPessoa_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

CREATE TABLE endereco (
    id_endereco int NOT NULL PRIMARY KEY,
    bairro VARCHAR(30) NOT NULL,
    rua VARCHAR(30) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL,

   id_pessoa int NOT NULL ,
   CONSTRAINT FK_tagsPessoa_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);
