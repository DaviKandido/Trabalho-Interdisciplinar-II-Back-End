--Apagar uma tabela

    create table TabelaTemporaria (
        Codigo char(3),
        Nome char(15)
    ); --Tabela criada temporariamente

-- Deleta ou elimina ou exclui uma tabela do banco de dados
DROP TABLE tabelatemporaria;

--LImpa os dados da tabela, mantendo a tabela no banco de dados
TRUNCATE TABLE TabelaTemporaria;

