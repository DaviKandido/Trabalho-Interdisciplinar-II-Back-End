-- Transação: usando os COMMIT e ROLLBACK

    -- ROLLBACK: encerra uma transação sem efetivar as operações executadas na transação
    BEGIN TRANSACTION;
    INSERT INTO Produto VALUES ('100', 'Teclado Mecânico', 300, '3', '005');
    SELECT * FROM Produto;
    ROLLBACK; --CANCELA A TRANSAÇÃO FEITA ANTERIORMENTE

    --COMMIT: encerra uma transação efetivando as operações executadas na transação

    BEGIN TRANSACTION;
    INSERT INTO Produto VALUES ('100', 'Teclado Mecânico', 300, '3', '005');
    SELECT * FROM Produto;
    COMMIT; -- EFETIVA A TRANSAÇÃO FEITA ANTERIORMENTE


    --também pode ser usado em outros comandos como DELETE
    BEGIN TRANSACTION;
    DELETE FROM Produto WHERE CodigoProduto = '100';
    SELECT * FROM Produto;
    COMMIT; -- EFETIVA A TRANSAÇÃO FEITA ANTERIORMENTE



    --No MySQL usa-se o START TRANSACTION EM VEZ DO BEGIN TRANSAACTION