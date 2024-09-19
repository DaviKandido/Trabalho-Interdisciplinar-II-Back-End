-- SUBCONSULTAS (ou subqueries ou consultas aninhadas)

    -- Produtos que possuem o preço maior que a media
    SELECT NomeProduto, Preco
    FROM Produto
    WHERE preco > (SELECT AVG(preco)
                    from Produto);


    -- Mostra os produtos que possuem o preço menor que os preços da categoria impressoras
    SELECT nomeProduto, preco, nome
    from produto join categoria on categoria = codigo
    WHERE preco < ALL (SELECT preco
                        from produto join categoria on categoria = codigo
                        where nome = 'impressoras');


    -- Mostra os produtos que possuem o preço menor que QUALQUER preço da categoria impressoras
    SELECT nomeProduto, preco, nome
    from produto join categoria on categoria = codigo
    WHERE preco < ANY (SELECT preco
                        from produto join categoria on categoria = codigo
                        where nome = 'impressoras');


-- SUBCONSULTA COM CORRELAÇÃO


    -- Mostra os produtos com o preço menor que a media dos preços do produtos
    SELECT NomeProduto, Preco
    from produto as P --Apelido dado ao produto
    where preco < (SELECT avg(preco)
                    FROM produto as P2
                    WHERE P2.categoria = P.categoria);


    -- pergunta se o produto existe
    SELECT NomeProduto, preco
    FROM Produto as P
    WHERE EXISTS (SELECT 1                      --Seleciona produtos que atendam essa condição
                    FROM Fornecedor             -- EXISTS retorna um booleano
                    WHERE codigo = P.Fornecedor
                    AND Cidade like 'Belo%'); 