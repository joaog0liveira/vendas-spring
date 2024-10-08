# Sistema de gerenciamento de Vendas

O projeto é uma mini aplicação de um sistema de gerenciamento de vendas para fins de estudos de algumas funcionalides.
Desenvolvido em Spring Boot, utilizando Java 17, foi implementada uma API RESTful, com tratamentos de erros de forma simples
e especificada, realização de operações POST, DELETE, GET, PUT, PATCH e retorno de códigos de status apropriados. Também foi
utilizado o LOMBOK visando reduzir o boilerplate.
Foram explorados a utilização da especificação Bean Validation para criação de Annotations e customização de validadores e
a configuração de Internacionalização.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- LOMBOK
- H2 Database

## Recursos e Funcionalidades

- **Gestão de Produtos:** Adicione, atualize, consulte e exclua produtos.

- **Gestão de Clientes:** Registre novos clientes, atualize informações.

- **Gestão de Pedidos:** Crie, atualize, consulte e exclua pedidos, incluindo informações sobre os produtos comprados e quantidades.

## Configuração do Ambiente

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/joaog0liveira/vendas-spring
   ```

2. **Compilação e Execução:**

   ```shell
   mvn spring-boot:run
   ```

## Teste da API

Os testes foram realizado pelo Postman e irei fornecer logo abaixo as requisições para testes.

## Endpoints da API

### 1. Cliente

#### 1.1 Criar Cliente

- **Método**: `POST`
- **URL**: `http://localhost:8080/api/clientes`
- **Descrição**: Cria um novo cliente.
- **Corpo da Requisição**:
  ```json
  {
    "nome": "João Gabriel",
    "cpf": "70674305183"
  }
  ```

#### 1.2 Buscar Cliente

- **Método**: `GET`
- **URL**: `http://localhost:8080/api/clientes/1`
- **Descrição**: Busca um cliente pelo ID.

#### 1.3 Deletar Cliente

- **Método**: `DELETE`
- **URL**: `http://localhost:8080/api/clientes/1`
- **Descrição**: Deleta um cliente pelo ID.

#### 1.4 Atualizar Cliente

- **Método**: `PUT`
- **URL**: `http://localhost:8080/api/clientes/1`
- **Descrição**: Atualiza os dados de um cliente.
- **Corpo da Requisição**:
  ```json
  {
    "nome": "Pedro",
    "cpf": "42591244120"
  }
  ```

---

### 2. Produto

#### 2.1 Criar Produto

- **Método**: `POST`
- **URL**: `http://localhost:8080/api/produtos`
- **Descrição**: Cria um novo produto.
- **Corpo da Requisição**:
  ```json
  {
    "descricao": "Mouse",
    "preco": 200
  }
  ```

#### 2.2 Buscar Produto

- **Método**: `GET`
- **URL**: `http://localhost:8080/api/produtos`
- **Descrição**: Busca todos os produtos.

#### 2.3 Atualizar Produto

- **Método**: `PUT`
- **URL**: `http://localhost:8080/api/produtos/3`
- **Descrição**: Atualiza os dados de um produto.
- **Corpo da Requisição**:
  ```json
  {
    "descricao": "Mouse",
    "preco": 500
  }
  ```

#### 2.4 Deletar Produto

- **Método**: `DELETE`
- **URL**: `http://localhost:8080/api/produtos/1`
- **Descrição**: Deleta um produto pelo ID.

---

### 3. Pedido

#### 3.1 Criar Pedido

- **Método**: `POST`
- **URL**: `http://localhost:8080/api/pedidos`
- **Descrição**: Cria um novo pedido.
- **Corpo da Requisição**:
  ```json
  {
    "cliente": 1,
    "total": 400,
    "items": [
      {
        "produto": 1,
        "quantidade": 2
      }
    ]
  }
  ```

#### 3.2 Buscar Pedido por ID

- **Método**: `GET`
- **URL**: `http://localhost:8080/api/pedidos/1`
- **Descrição**: Busca um pedido pelo ID.

#### 3.3 Atualizar Status do Pedido

- **Método**: `PATCH`
- **URL**: `http://localhost:8080/api/pedidos/1`
- **Descrição**: Atualiza o status de um pedido.
- **Corpo da Requisição**:
  ```json
  {
    "novoStatus": "CANCELADO"
  }
  ```

## Contato

Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato:

- Nome: João Gabriel de Oliveira Meireles
- Email: joaog.meireles@outlook.com
- LinkedIn: https://www.linkedin.com/in/joaogomeireles/
