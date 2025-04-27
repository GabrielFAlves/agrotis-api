# 📦 Agrotis API

API para gestão de pessoas, propriedades e laboratórios — desenvolvida como desafio técnico.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- Lombok
- MySQL 8
- Docker e Docker Compose
- Maven
- Swagger OpenAPI (`springdoc-openapi`)

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Maven](https://maven.apache.org/)

### Passos para execução

```bash
git clone https://github.com/GabrielFAlves/agrotis-api.git
cd agrotis-api
cd api
```

```bash
mvn clean package
```

```bash
docker-compose up --build
```

Isso irá:

- Subir um container MySQL na porta `3306`
- Subir a aplicação Spring Boot na porta `8080`

---

## 📚 Documentação da API

Após subir o projeto, acesse:

```bash
http://localhost:8080/swagger-ui.html
```

A documentação interativa do Swagger estará disponível para testar todos os endpoints.

---

## 📌 Endpoints Principais

### 🧑 Pessoa

- `POST /api/pessoas` — Criar uma nova pessoa
- `GET /api/pessoas` — Listar todas as pessoas
- `GET /api/pessoas/{id}` — Buscar uma pessoa por ID
- `PUT /api/pessoas/{id}` — Atualizar uma pessoa
- `DELETE /api/pessoas/{id}` — Deletar uma pessoa

### 🌾 Propriedade

- `GET /api/propriedades` — Listar todas as propriedades

### 🧪 Laboratório

- `GET /api/laboratorios` — Listar laboratórios com filtros

---

## 🛠️ Estrutura do Projeto

```bash
api
├── application
│   ├── controller      # Controllers REST
│   ├── dto             # DTOs de entrada e saída
│   └── service         # Serviços auxiliares (validações, etc)
├── domain
│   ├── model           # Modelos do domínio
│   ├── repository      # Interfaces de repositórios
│   └── usecase         # Casos de uso
├── infrastructure
│   └── persistence     # Implementações de repositórios (JPA)
├── AgrotisApiApplication.java
├── application.properties
└── docker-compose.yml
```

---

## 🧪 Testes

Foram implementados testes de unidade para:

- `PessoaUseCase`
- `PessoaValidationService`

### Executar os testes:

```bash
mvn test
```

---

✅ Projeto estruturado com boas práticas de código, preparado para desenvolvimento e manutenção contínua.
