# 💰 mais-barato-API

**Mais Barato** é uma API RESTful desenvolvida em Java com Spring Boot, criada para ajudar os usuários a comparar produtos e encontrar a melhor opção de compra com base no preço por unidade.

## 🚀 **Tecnologias Utilizadas**

- Java 21
- Spring Boot 3
- Spring Security
- JPA/Hibernate
- Lombok
- JWT (JSON Web Token) para autenticação
- Maven (gerenciador de dependências)

---

## ⚙️ **Como Rodar o Projeto**

### 1️⃣ **Pré-requisitos**

- Java 21
- Maven 3.8+
- IDE (IntelliJ, Eclipse, VS Code)

### 2️⃣ **Clone o repositório**

```bash
git clone https://github.com/pedroleonez/mais-barato-api.git
cd mais-barato-api
```

### 3️⃣ **Configure o arquivo `application.properties`**

Defina as propriedades de conexão do banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mais_barato_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4️⃣ **Build e execução**

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 🔐 **Autenticação (JWT)**

A API utiliza autenticação via JWT. Siga os passos:

### 🔑 **Registrar um novo usuário**

**Endpoint:** `POST /auth/register`

**Exemplo de corpo da requisição:**

```json
{
  "name": "Pedro Leonez",
  "email": "pedro@example.com",
  "password": "123456"
}
```

**Resposta esperada:**

```json
{
  "name": "Pedro Leonez",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 🔓 **Login**

**Endpoint:** `POST /auth/login`

**Exemplo de corpo da requisição:**

```json
{
  "email": "pedro@example.com",
  "password": "123456"
}
```

**Resposta esperada:**

```json
{
  "name": "Pedro Leonez",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Utilize o token recebido no cabeçalho `Authorization` para acessar os endpoints protegidos:

```
Authorization: Bearer <seu_token>
```

---

## 📦 **Endpoints de Produtos**

### ➕ **Adicionar Produto**

**Endpoint:** `POST /products`

**Requisição:**

```json
{
  "name": "Leite",
  "price1": 4.50,
  "size1": 1.0,
  "price2": 8.00,
  "size2": 2.0,
  "unit": "LITER"
}
```

**Resposta esperada:** `201 Created`

---

### 📃 **Listar Produtos**

**Endpoint:** `GET /products`

**Resposta:**

```json
[
  {
    "id": "uuid-do-produto",
    "name": "Leite",
    "price1": 4.50,
    "size1": 1.0,
    "price2": 8.00,
    "size2": 2.0,
    "unit": "LITER"
  }
]
```

---

### ✏️ **Atualizar Produto**

**Endpoint:** `PUT /products/{id}`

**Requisição:**

```json
{
  "name": "Leite Integral",
  "price1": 5.00,
  "size1": 1.0,
  "price2": 9.50,
  "size2": 2.0,
  "unit": "LITER"
}
```

---

### ❌ **Deletar Produto**

**Endpoint:** `DELETE /products/{id}`

**Resposta esperada:** `204 No Content`

---

### 🔍 **Comparar Opções de Preço**

**Endpoint:** `GET /products/{id}/best-option`

**Resposta esperada:**

```json
"The second option (2.0 liters) is 5.26% more economical."
```

---

## 📝 **Licença**

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 **Autor**

Desenvolvido por [Pedro Leonez](https://github.com/pedroleonez) 🚀

