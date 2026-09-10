# Biblioteca - Sistema de Gestão de Biblioteca

## Visão geral do projeto

Este projeto é um CRUD para uma biblioteca fictícia, pensado para gerenciar livros, autores, leitores e empréstimos de exemplares. A ideia central é permitir o cadastro, consulta, atualização e exclusão de registros em um banco PostgreSQL, tudo em uma aplicação Java de console.

O sistema foi desenvolvido para representar um ambiente simples de gestão bibliotecária, incluindo:

- Cadastro de livros
- Cadastro de autores
- Relacionamento entre livros e autores
- Cadastro de leitores
- Registro de livros emprestados
- Atualização de dados do catálogo e de empréstimos
- Exclusão de registros

A aplicação inicia pelo menu principal e, ao rodar, também executa as migrações do banco via Flyway para garantir que as tabelas existam.

## Estrutura do projeto

O código está organizado em pacotes por responsabilidade:

- `Persistence` — conexão com o PostgreSQL
- `SQL.Commands` — operações de criação, atualização e exclusão
- `SQL.Queries` — consultas para leitura de dados
- `UI` — menu interativo do sistema
- `Exceptions` — validações de opções do usuário
- `src/main/resources/db/migration` — scripts de migração do banco

As tabelas principais são:

- `authors` — autores
- `books` — livros
- `book_authors` — associação entre livro e autor
- `readers` — leitores
- `borrowed_books` — registros de empréstimos

## Tecnologias utilizadas

O projeto usa uma stack simples e eficiente para um CRUD em Java:

- Java 21
- PostgreSQL 16
- JDBC para conexão com o banco
- Flyway para versionamento e migração do schema
- Maven para gerenciamento de dependências
- Docker + Docker Compose para subir o banco localmente
- Lombok para reduzir boilerplate

Essa combinação permite que o projeto seja fácil de configurar, manter e expandir com novos módulos.

## Setup do PostgreSQL e conexão com o código

A aplicação conecta ao PostgreSQL usando a URL:

```java
jdbc:postgresql://localhost:5432/biblioteca
```

Credenciais padrão configuradas no projeto:

- Usuário: `postgres`
- Senha: `postgres`
- Banco: `biblioteca`

A conexão é feita pela classe `Persistence.ConnectionUtil`, que usa `DriverManager.getConnection(...)`.

### 1. Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- Java 21+
- Maven
- Docker Desktop ou Docker Engine
- DBeaver, pgAdmin ou outro cliente PostgreSQL opcional

### 2. Clonar o projeto

```powershell
git clone <url-do-repositorio>
cd Library
```

### 3. Subir o banco com Docker

Na raiz do projeto, execute:

```powershell
docker compose up --build -d
```

Se o container já estiver criado, basta subir novamente com:

```powershell
docker compose up -d
```

### 4. Verificar se o PostgreSQL está rodando

```powershell
docker compose ps
```

Você deve ver o serviço `postgres` ou o container `biblioteca-postgres` com status `Up`.

Também pode verificar os logs:

```powershell
docker compose logs -f postgres
```

### 5. Confirmar disponibilidade do banco

Uma forma simples de validar é usar o cliente PostgreSQL:

- Host: `localhost`
- Porta: `5432`
- Banco: `biblioteca`
- Usuário: `postgres`
- Senha: `postgres`

No DBeaver:

1. Abra o DBeaver
2. Clique em "Arquivo" → "Nova Conexão"
3. Selecione "PostgreSQL"
4. Preencha os dados acima
5. Clique em "Testar Conexão"
6. Caso tudo esteja correto, clique em "Concluir"

### 6. Rodar a aplicação

A aplicação principal fica em `src/main/java/Main.java`.

Ao iniciar a aplicação, ela tenta conectar ao banco e, em seguida, executa as migrações do Flyway automaticamente. Isso garante que as tabelas do sistema sejam criadas antes do uso.

### 7. Resetar o banco, se necessário

Se houver algum problema de persistência ou se quiser limpar o banco do zero:

```powershell
docker compose down --volumes --remove-orphans
```

Depois:

```powershell
docker compose up --build -d
```

Esse comando remove os dados antigos do volume Docker e recria o banco em um estado limpo.

## Arquivos de configuração importantes

- `Dockerfile` — define a imagem do PostgreSQL
- `docker-compose.yml` — monta o container e expõe a porta 5432
- `pom.xml` — configurações do Maven e dependências do projeto
- `src/main/resources/db/migration/*.sql` — scripts de criação das tabelas e dados iniciais

## Como o projeto funciona na prática

Ao rodar o sistema, o usuário acessa um menu de console com opções para:

- cadastrar um novo livro ou autor
- listar os dados das tabelas
- atualizar informações de livros, autores e leitores
- registrar empréstimos
- remover itens do catálogo

Assim, o sistema funciona como um gerenciador básico de biblioteca, com foco em operações CRUD e persistência em banco relacional.

## Observações finais

Este projeto é uma excelente base para aprender:

- Java com acesso a banco de dados
- PostgreSQL no ambiente local
- Versionamento de schema com Flyway
- Docker para desenvolvimento de bancos
- Estrutura de projetos Java com organização por pacotes

Se quiser evoluir o sistema no futuro, possibilidades incluem:

- autenticação de usuários
- controle de multas por atraso
- busca por título/autor
- relatório de livros mais emprestados
- interface gráfica com Swing ou JavaFX

