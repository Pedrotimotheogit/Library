# Configuração PostgreSQL 16 + DBeaver

## Visão geral

Este projeto usa PostgreSQL 16, definido no [Dockerfile](Dockerfile). O arquivo [docker-compose.yml](docker-compose.yml) monta um volume nomeado em `/var/lib/postgresql/data` para manter os dados persistidos mesmo quando o container é parado.

> Importante: o `version: '3.8'` no Compose é apenas a versão do formato do Compose e não define a versão do PostgreSQL.

## Iniciar o Banco de Dados

Na pasta do projeto, rode:

```powershell
docker-compose up --build -d
```

Se o container já foi criado e você quer apenas subir novamente:

```powershell
docker-compose up -d
```

## Verificar se o banco subiu

```powershell
docker-compose ps
```

Você deve ver o container `biblioteca-postgres` com status `Up`.

## Conectar no DBeaver

1. Abra o DBeaver
2. Clique em **Arquivo** → **Nova Conexão de Banco de Dados**
3. Selecione **PostgreSQL**
4. Preencha:
   - **Host:** localhost
   - **Port:** 5432
   - **Database:** biblioteca
   - **Username:** postgres
   - **Password:** postgres
5. Clique em **Testar Conexão**
6. Clique em **Concluir**

## Parar o Banco de Dados

```powershell
docker-compose down
```

Isso para o container, mas preserva os dados no volume Docker.

## Resetar o banco do zero

Se o PostgreSQL falhar com erro como:

```text
initdb: error: directory "/var/lib/postgresql/data" exists but is not empty
```

faça:

```powershell
docker-compose down --volumes --remove-orphans
docker volume rm library_postgres_data --force
docker-compose up --build -d
```

Esse comando remove o volume antigo e recria o banco em um estado limpo.

## Dicas importantes sobre persistência

O banco fica salvo no volume Docker nomeado `postgres_data` porque este trecho do compose:

```yaml
volumes:
  - postgres_data:/var/lib/postgresql/data
```

faz o mapping do diretório interno do PostgreSQL para um volume Docker. Isso significa:

- `docker-compose down` → para o container, mas mantém os dados
- `docker-compose down -v` → remove também o volume e apaga os dados

## Comandos úteis do dia a dia

### Subir
```powershell
docker-compose up -d
```

### Parar
```powershell
docker-compose down
```

### Reset completo
```powershell
docker-compose down --volumes --remove-orphans
docker-compose up --build -d
```

### Ver logs
```powershell
docker-compose logs -f postgres
```

### Ver volumes
```powershell
docker volume ls
```

## Variáveis do banco

Esses valores ficam configurados em [docker-compose.yml](docker-compose.yml):
- `POSTGRES_USER`: postgres
- `POSTGRES_PASSWORD`: postgres
- `POSTGRES_DB`: biblioteca
- `ports`: 5432:5432
