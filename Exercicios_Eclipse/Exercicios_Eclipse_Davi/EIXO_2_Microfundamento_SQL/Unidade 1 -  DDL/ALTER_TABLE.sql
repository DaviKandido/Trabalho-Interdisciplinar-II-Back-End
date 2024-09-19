--Alteração da Estrutura do Banco de Dados

--ALTER TABLE
ALTER TABLE produto
ADD CONSTRAINT FK_Produto_Fornecedor FOREIGN KEY (Fornecedor) REFERENCES Fornecedor(Codigo);

--Alterando o nome de uma coluna
ALTER TABLE produto RENAME COLUMN PrecoProduto TO Preco;

--Altera o nome de uma tabela
--RENAME TABLE TabelaTemporaria TO NovaTabela;
ALTER TABLE TabelaTemporaria RENAME TO NovaTabela;

--Adicionar colunas a tabela já existente
ALTER TABLE Fornecedor
 ADD column UF char(2);
ALTER TABLE Fornecedor
 ADD column Cidade char(30);