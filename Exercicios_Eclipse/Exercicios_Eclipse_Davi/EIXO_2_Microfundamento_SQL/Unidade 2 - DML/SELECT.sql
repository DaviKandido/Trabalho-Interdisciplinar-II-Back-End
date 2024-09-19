-- Recuperar/Selecionar/Consultar ou traz das tebelas os dados lá armazenados

SELECT Codigo --Seleciona o atributo codigo
FROM Categoria --da tabela categoria

SELECT nome, codigo --Seleciona a lista de atributos (devolve na ordem que for pedido)
FROM Categoria -- da tabela categoria

SELECT * --Seleciona todos os atributos
FROM Categoria -- da tabela categoria


--Filtra dados de acordo com um filtro
SELECT Codigo, nome, Cidade, UF
FROM Fornecedor
WHERE UF = 'MG'; -- Filtra fornecedores que residem na UF MG

SELECT Codigo, nome, Cidade, UF
FROM Fornecedor
WHERE UF = 'MG' OR UF = 'BA'; -- Filtra fornecedores que residem na UF MG ou BA

SELECT Codigo, nome, Cidade, UF
FROM Fornecedor
WHERE UF = 'MG' AND UF = 'BA'; -- ERRO NÃO É POSSÍVEL RESIDIR EM DOIS ESTADOS AO MESMO TEMPO


--Filtra dados em uma ordem CRESCENTE especifica
SELECT Codigo, nome, Cidade, UF
FROM Fornecedor
WHERE UF = 'MG' OR UF = 'BA' -- Filtra fornecedores que residem na UF MG ou BA
ORDER BY UF;

--Filtra dados em uma ordem DE-CRESCENTE especifica
SELECT Codigo, nome, Cidade, UF
FROM Fornecedor
WHERE UF = 'MG' OR UF = 'BA' -- Filtra fornecedores que residem na UF MG ou BA
ORDER BY Nome DESC;