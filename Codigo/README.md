# Código do Projeto

Mantenha neste diretório todo o código do projeto. Se necessário, descreva neste arquivo aspectos relevantes da estrutura de diretórios criada para organização do código.

---

### Link do Resplit Contendo o Front-End Desenvolvido anteriormente e agora aprimorado em TI2

[Link do Replit](https://replit.com/@Leticia-dada1/TI2)

# SOSBichinhos - Organização das Pastas

Este documento explica a estrutura do projeto **SOSBichinhos**, que está organizado conforme descrito abaixo.

## Estrutura de Pastas

### 1. `.metadata`, `.settings`, `.vscode`
- Essas pastas contêm arquivos de configuração específicos para o ambiente de desenvolvimento e IDE (Eclipse/VSCode).
- **.metadata**: Configurações do projeto em Eclipse.
- **.settings**: Definições de preferências do projeto.
- **.vscode**: Configurações do editor Visual Studio Code, como extensões e ajustes específicos para este projeto.

### 2. `src/main/java`
- Contém o código-fonte Java principal da aplicação.
  - **app**: A pasta `app` contém a classe principal de cada tela, responsável por iniciar a aplicação.
  - **dao**: Implementa a camada de acesso a dados (Data Access Object). Aqui ficam as classes que fazem a interação com o banco de dados.
    - **ClassesDAO.java**: Classe responsável pelas operações CRUD relacionadas aos objetos .
    - **DAO.java**: Interface genérica de DAO para abstração das operações básicas.
  - **model**: Contém as classes que definem os modelos de dados (entidades).
  - **service**: A pasta `service` contém as classes de lógica de negócios, como por exemplo `AnimalService_CRUD_Animal.java`, que encapsula as operações de negócio envolvendo `Animal`.

### 3. `resources/public`
- Armazena os arquivos estáticos utilizados pela aplicação.
  - **assets**: Arquivos de estilos (CSS) que definem a aparência visual da aplicação e Arquivos JavaScript que gerenciam a interação e o comportamento dinâmico da interface;
  - **modules**:Aquivos Html de cada tela.
  - **documentacao**: Contém arquivos de documentação do projeto.
  - **imgs**: Imagens utilizadas no frontend.

### 4. `modules`
- Contém os diferentes módulos funcionais da aplicação.
  - **comentarios**: É possível comentar sobre um animal especifico.
  - **encontreiAnimal**: Módulo responsável pela funcionalidade de reportar animais encontrados.
  - **formulario**: Contém a tela de formulário necessária para a adoção de um animal.
  - **login**: Gerencia a funcionalidade de login para usuários e ONGs.
  - **exibicaoAnimais**: Exibe a lista de animais disponíveis para adoção, com base em filtros e preferências.
  - **login**: Responsável pela autenticação de usuários e ONGs, fornecendo uma interface de login.
  - **requerimentos**: Gerencia os requisitos de formulários.
  - **sobreNos**: Contém a página "Sobre Nós", onde é apresentada um pouco sobre os integrantes do grupo.
  - **sobreONG**: Fornece detalhes sobre a ONG parceira da plataforma, com informações específicas.
  - **tela_cadastro_usuario**: Módulo que contém a interface para o cadastro de novos usuários no sistema.
  - **tela_perfil_usuario**: Exibe o perfil dos usuários, onde podem visualizar e editar suas informações pessoais.

  - **teladecadastrodeanimalONG**: Página de cadastro de animais pelas ONGs, permitindo que elas registrem novos animais para adoção.
  
  - **telaInicialONG**: Página inicial para a ONG, onde ela pode acessar suas principais funcionalidades.

  - **index.html**: Página inicial principal da aplicação, onde os usuários podem navegar pelas funcionalidades.
