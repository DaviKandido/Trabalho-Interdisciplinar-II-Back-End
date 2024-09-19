-- SEGURANÇA: LINGUAGEM DE CONTROLE DE DADOS (DCL): COMANDOS GRANT E REVOKE


    -- Criação de usuários e suas senhas
    CREATE USER "Gerente" IDENTIFIED BY '1234';
    CREATE USER "Vendedor" IDENTIFIED BY '5678';
    CREATE USER "Governanca" IDENTIFIED BY 'abcd';


    -- Concede todos (ALL) os privilegios ao usuario "Gerente"
    GRANT ALL PRIVILEGES ON ComercioTI.Produto TO "Gerente";
    GRANT ALL PRIVILEGES ON ComercioTI.Produto TO "Gerente";
    GRANT ALL PRIVILEGES ON ComercioTI.Produto TO "Gerente";


    -- Concede os privilegios específicos ao usuario "Vendedor"
    GRANT INSERT, SELECT, UPDATE, DELETE ON ComercioTI.Produto TO "Vendedor";

    --  Concede privilegios específicos ao usuario "Governanca"
    GRANT INSERT, SELECT, UPDATE, DELETE ON ComercioTI.fornecedor TO "Governanca";
    GRANT INSERT, SELECT, UPDATE, DELETE ON ComercioTI.categoria TO "Governanca";


    --  RETIRA privilegios específicos do usuario "Governanca"
    REMOKE DELETE ON ComercioTI.Produto FROM "Vendedor";
    REMOKE INSERT ON ComercioTI.Produto FROM "Gerente";