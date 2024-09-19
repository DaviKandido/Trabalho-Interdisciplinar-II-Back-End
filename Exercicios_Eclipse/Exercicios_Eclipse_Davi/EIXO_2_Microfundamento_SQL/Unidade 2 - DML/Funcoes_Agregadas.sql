-- FUNÇÕES AGREGADAS OU FUNÇÕES DE AGREGAÇÃO: AVG, COUNT, MAX, MIN, SUM


-- AVG(Coluna): Calcula a média de todos os valores para aquela coluna
    --Valores nulos não são considerados

    SELECT AVG(Preco)
    FROM Produto;

    SELECT AVG(Preco) AS "Preço Médio" --Renomeia a apresentação da coluna AVG
    FROM Produto;

-- COUNT: há duas possibilidade
    -- COUNT(*) retorna a quantidade de linhas em uma tabela, contando todas as colunas, incluindo as colunas com valores nulos
    -- COUNT(Coluna) retorna a quantidade de linhas em uma tabela, contando apenas as colunas com valores não nulos

    SELECT COUNT(*) AS "Total de Produtos"
    FROM Produto;

    SELECT COUNT(NomeProduto) AS "Total de Produtos"
    FROM Produto;

    -- Conta quantos produtos tem a categoria 1
    SELECT COUNT(*)
    FROM Produto
    WHERE Categoria = '1';


-- MAX(coluna): retorna o valor máximo para coluna, o maior valor
    -- Valores nulos não são considerados

    SELECT MAX(Preco) AS "Preço Máximo"
    FROM Produto;

    SELECT MAX(Preco) AS "Preço Máximo na categoria 1"
    FROM Produto
    WHERE categoria = '1';


-- MIN(coluna): retorna o valor Mínimo para coluna, o maior valor
    -- Valores nulos não são considerados

    SELECT MIN(Preco)
    FROM Produto;

    SELECT MIN(Preco)
    FROM Produto
    WHERE Categoria = '1';


-- SUN(coluna): Calcula a soma dos valores para coluna
    -- Valores nulos não são considerados

    SELECT sum(preco)
    FROM produto;

    SELECT sum(preco)
    FROM produto
    WHERE fornecedor = '002';

-- O SQL permite operação livres

SELECT 2 + 3;

SELECT 'o produto de 2 e 3 = ', 2 * 3;


SELECT 'o produto de 2 e 3 = ', 2 * 3 -- mostra na quantidade de linhas da tabela
FROM produto;