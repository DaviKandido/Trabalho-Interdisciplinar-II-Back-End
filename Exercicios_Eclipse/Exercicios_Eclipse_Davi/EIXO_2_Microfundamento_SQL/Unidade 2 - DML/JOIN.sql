-- Junção de tabelas

    SELECT Nome, NomeProduto
    FROM Categoria, Produto;

    -- Resulta na mesma coisa que o de cima
    SELECT Nome, NomeProduto
    FROM Categoria CROSS JOIN Produto;

    --Pega a interseção entre as duas tabelas e os seus atributos pedidos
    SELECT Nome, NomeProduto
    FROM Categoria INNER JOIN Produto ON Categoria = Codigo; -- conjunto de colunas que se relacionam entre as tabelas

    -- Dessa forma é mais legível
    SELECT Categoria.Nome as Categoria, Produto.NomeProduto as Produto
    FROM Categoria INNER JOIN Produto ON Produto.Categoria = Categoria.Codigo


    --LEFT JOIN (Correspondentes e as não correspondestes da tabela a direita)
    SELECT Categoria.Nome as Categoria, Produto.NomeProduto as Produto
    FROM Categoria LEFT JOIN Produto ON Produto.Categoria = Categoria.Codigo


    -- RIGHT JOIN (Correspondentes e as não correspondestes da tabela a ESQUERDA)
    SELECT P.NomeProduto as Produto, C.Nome as Categoria
    FROM Categoria as C RIGHT JOIN Produto as P ON P.Categoria = C.Codigo;


    -- FULL JOIN (Não esta implementado no MySQL)  (Correspondentes e as não correspondestes de ambas as tabelas)
    SELECT P.NomeProduto as Produto, C.Nome as Categoria
    FROM Categoria as C FULL JOIN Produto as P ON P.Categoria = C.Codigo;


    -- UNION (Junta duas ou mais tabelas, ignora as duplicatas)
    SELECT Nome as "Categoria e Produtos"
    FROM Categoria

    UNION ALL

    SELECT NomeProduto
    FROM Produto;


    --Substititui o full join
    SELECT P.NomeProduto as Produto, C.Nome as Categoria
    FROM Categoria as C LEFT JOIN Produto as P ON P.Categoria = C.Codigo
      
    UNION --diferente do UNION ALL ele não coloca duplicado

    SELECT P.NomeProduto as Produto, C.Nome as Categoria
    FROM Categoria as C RIGHT JOIN Produto as P ON P.Categoria = C.Codigo
    
    ORDER BY Produto;    


    -- DISTINCT (Nomes das categorias que tem produto, sem repetições)

    SELECT DISTINCT Nome
    FROM Categoria join Produto on Produto.categoria = categoria.codigo;