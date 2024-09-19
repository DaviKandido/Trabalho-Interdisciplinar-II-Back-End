--POPULANDO AS TABELAS DO BANCO DE DADOS (INSERINDO DADOS)

-- Populando a tabela Fornecedor na ordem armazenada
INSERT INTO Fornecedor VALUES('001', 'Fornecedor 1');

-- Populando a tabela Fornecedor fora da ordem armazenada
INSERT INTO Fornecedor (NOME, Codigo) VALUES ('Fornecedor 2', '002');

--não se pode inserir apenas um dos dados da tabela que não seja PRIMARY KEY, caso configurado
--se pode inserir um valor padrão caso não informado

    INSERT INTO Fornecedor(codigo) VALUES ('004'); --É aceito,pois codigo é um PK

    -- Assim o campo nome não aceitara inserções nulas
    CREATE TABLE Categoria(
        Codigo char(3) PRIMARY KEY,
        Nome char(15) NOT NULL
    );

    --Também se é possivel adicionar mais de um conteudo de uma vez
    INSERT INTO produto VALUES ('4', 'Mouse Logitech', 80, '3', '002'), 
    ('5', 'Mesa Digitalizadora Wacom', 460, '3', '003');



    --mostra todas as linhas e colunas
    SELECT * FROM fornecedor;

-- Alterando/ATUALIZANDO um dado em uma tabela
UPDATE Fornecedro SET Nome = 'Fornecedor 4' WHERE codigo = '004';

--Excluindo linhas/registros de uma tabela
DELETE FROM produto WHERE categoria = '2';

    --Cuidado ao usar este comando sem filtro, sem restrição indicada através da cláusula where
        --DELETE FROM produto

--UPDATE
UPDATE Fornecedor
    set Cidade = 'São Paulo', UF = 'SP'
    WHERE Nome in ('Fornecedor 2', 'Fornecedor 4');