--Agrupando dados em consulta

    SELECT UF, COUNT(*) --quantidade de fornecedores em cada estado
    FROM Fornecedor
    GROUP BY UF;

    SELECT UF, COUNT(*) 
    FROM Fornecedor
    GROUP BY UF
    ORDER BY UF; -- Agora mostrara de forma ordenada por estado

    SELECT UF, COUNT(*) 
    FROM Fornecedor
    GROUP BY UF
    ORDER BY COUNT(*); -- Agora mostrara de forma ordenada por quantidade

    SELECT UF, COUNT(*) 
    FROM Fornecedor
    GROUP BY UF
    ORDER BY 2; -- Indica qual nº da coluna será usada para a ordenação, nesse caso a 2 COUNT(*)

    -- MEDIA de preço por categoria
    SELECT Categoria, AVG(Preco) 
    FROM Produto
    GROUP BY Categoria;


--Filtro, exemplo só vai trazer se o preço medio for maior que 100

    SELECT categoria, avg(preco)
    from produto
    GROUP BY categoria
    HAVING avg(preco) > 1000; --filtra apenas para categorias com medias acima de 1000

    --Soma dos produtos agrupados por fornecedor
    SELECT Fornecedor, SUM(preco)
    FROM produto
    GROUP BY fornecedor

-- JOINS

-- Join entre Fornecedor e Produto