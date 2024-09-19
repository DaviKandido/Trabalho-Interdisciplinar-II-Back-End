-- OPERADORES DE COMPARAÇÃO: = , <> , > , < , >= , <=

    SELECT NomeProduto, Preco, categoria
    FROM Produto
    WHERE Categoria = '3';

    SELECT NomeProduto, Preco, categoria
    FROM Produto
    WHERE Preco > '3'
    ORDER BY preco;

    SELECT NomeProduto, Preco
    FROM Produto
    WHERE categoria <> '3' -- Categorias diferentes de 3
    ORDER BY preco;

    SELECT NomeProduto, Preco
    FROM Produto
    WHERE preco <= 1000 -- Categorias diferentes de 3
    ORDER BY preco DESC;



-- OPERADORES LÓGICOS: AND , OR , BETWEEN , IN , NOT , IS NULL , IS NOT NULL


    SELECT NomeProduto, Preco
    FROM produto
    WHERE preco >= 1000 and preco <= 2000;

    SELECT NomeProduto, Preco
    FROM produto
    WHERE preco > 2000 and preco > 100;

    --BETWEEN faz a mesma função de procurar dentro do intervalo
    SELECT NomeProduto, Preco
    FROM produto
    WHERE preco BETWEEN 1000 and 2000;


    -- IN comprara valores dentro de uma lista
    SELECT Nome
    FROM Categoria
    WHERE nome in ('Acessório', 'Suprimentos') -- vai apresentar apenas 'Acessório' e 'Suprimentos

    SELECT Nome
    FROM Categoria
    WHERE nome not in ('Acessório', 'Suprimentos') -- vai apresentar apenas os que não são 'Acessório' e 'Suprimentos

    SELECT NomeProduto, preco
    FROM produto
    WHERE preco in (80, 1000);

    --procura um preco que seja nulo
    SELECT NomeProduto, preco
    FROM produto
    WHERE preco IS NULL;

    --procura um preco que NÃO seja nulo
    SELECT NomeProduto, preco
    FROM produto
    WHERE preco IS NOT NULL;

-- OPERADORES DE TEXTO: LIKE

    SELECT Nome, cidade, uf
    from fornecedor
    where cidade = 'Belo Horizonte';

    -- LIKE procura de forma coringa ( % - substitui o que falta no campo)
    SELECT Nome, cidade, uf
    from fornecedor
    where cidade LIKE 'Belo%';

    SELECT Nome, cidade, uf
    from fornecedor
    where cidade LIKE '%Horizonte';

    SELECT Nome, cidade, uf
    from fornecedor
    where cidade LIKE '%Ho%';

    -- LIKE procura de forma coringa ( _ - substitui um carácter daquela posição)

    SELECT Nome, cidade, uf
    from fornecedor
    where cidade LIKE '_elo Horizonte';
