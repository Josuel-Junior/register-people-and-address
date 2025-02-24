
# API RESTFul com Java e Spring boot

Objetivo do Projeto

Desenvolver uma API RESTful para gerenciamento de pessoas, permitindo a realização completa de operações CRUD e a associação de um endereço a cada pessoa.

Requisitos Funcionais:

- Cadastro de Pessoa: Registrar novas pessoas no sistema.

- Edição de Pessoa: Atualizar os dados de pessoas já cadastradas.

- Listagem de Pessoas: Consultar todas as pessoas registradas.

- Busca por ID: Recuperar os dados de uma pessoa específica através de seu identificador único.

- Criação e Associação de Endereço: Cadastrar um endereço e vinculá-lo à pessoa correspondente.

Adotar os princípios do Clean Code para desenvolver um código limpo, escalável e de fácil manutenção.

O relacionamento entre Person (Pessoa) e Address (Endereço) deve ser de ManyToOne (Muitos para Um), onde muitas pessoas podem estar associadas a um único endereço, mas um endereço pertence apenas a uma pessoa.

![Logo](https://www.datocms-assets.com/115877/1740257823-diagrama-sem-nome.jpg)

# Sobre o Desenvolvimento do projeto

## Tecnologias utilizadas

- Spring Boot
- Java
- Spring Data JPA
- Hibernate
- Spring Initializr
- MySQL
- Docker Compose
- Maven
- JUnit e Mockito
- Swagger

## Desenvolvimento
Esta API foi desenvolvida com o intuito de aplicar meus conhecimentos no desenvolvimento back-end. Todo o código foi escrito por mim, aplicando conceitos que domino e dos quais tenho total entendimento, garantindo a qualidade e a consistência do projeto.



### Rotas
Desenvolvi todas as rotas conforme solicitado, cumprindo os requisitos do projeto. Esta API contém todas as operações de CRUD, utilizando DTOs para definir os parâmetros de cada método, garantindo padronização nas requisições de dados e evitando a exposição direta das nossas entidades. Além disso, foi criada a operação de associar endereço à pessoa, que permite vincular um endereço existente ou criar um novo e associá-lo, conforme a necessidade.

**URL Base:** `/person`

---

## Endpoints

### Endpoints Resumidos

- **Criar Pessoa**  
  - **Método:** `POST`  
  - **URL:** `/person`

- **Listar Pessoas**  
  - **Método:** `GET`  
  - **URL:** `/person`

- **Consultar Pessoa**  
  - **Método:** `GET`  
  - **URL:** `/person/{personId}`

- **Atualizar Pessoa**  
  - **Método:** `PUT`  
  - **URL:** `/person/{personId}`

- **Excluir Pessoa**  
  - **Método:** `DELETE`  
  - **URL:** `/person/{personId}`

- **Associar Endereço a Pessoa**  
  - **Método:** `POST`  
  - **URL:** `/address/{personId}`


### Persistencia com JPA 
Implementei a persistência dos dados utilizando JPA, garantindo uma gravação eficiente dos objetos. No banco de dados, criei duas tabelas: tb_address e tb_person. Na tabela tb_person, configurei uma chave estrangeira que referencia tb_address, permitindo associar um endereço a cada pessoa de maneira consistente e eficaz.


### Associação de endereço a pessoa
Criei uma rota que permite associar um endereço a uma pessoa. Antes de realizar a associação, verifico se o endereço já existe no banco de dados. Se ele existir, recupero o registro pelo ID e vinculo à pessoa; caso contrário, crio o novo endereço e o associo à pessoa. Dessa forma, evito a criação de registros duplicados no banco de dados.

### Exception

Tratei as exceções das rotas `deleteById`, `updatePerson` e `associateAddress` de forma global, criando uma classe responsável por lançar mensagens de exceção caso os dados não sejam encontrados ou caso um ID em formato incorreto seja passado. Com isso, conseguimos antecipar e tratar futuros erros de consistência de maneira eficaz.


### Teste Unitarios com JUnit e Mockito

Para garantir a qualidade do meu código, cubri todos os métodos do meu `SERVICE` com testes utilizando JUnit e Mockito, incluindo testes para as exceções, a fim de assegurar o tratamento adequado dos erros.

Testes na class AnddressService
![Logo](https://www.datocms-assets.com/115877/1740424333-01.png)

Teste na class PersonService

![Logo](https://www.datocms-assets.com/115877/1740424333-02.png)

### Documentação com Swagger


Além da documentação escrita, utilizei o Swagger para documentar toda a API, facilitando a realização de testes práticos ao acessar a rota "http://localhost:8080/swagger-ui/index.html".

## Rodar projeto

### Requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados em sua máquina:

- **Java Development Kit (JDK) 21**: Necessário para compilar e executar o projeto.
- **Docker**: Utilizado para rodar o banco de dados em um contêiner.
- **Maven**: Ferramenta de build e gerenciamento de dependências do projeto.
- **Git**: Para clonar o repositório do projeto.

## Como rodar o projeto

### 1. Clonar o repositório

git clone https://github.com/Josuel-Junior/register-people-and-address.git

### 2. Inicie o banco de dados a partir da pasta raiz do projeto com o comando:
- docker compose up

### 3. Iniciar projeto com o comando:
- mvn spring-boot:run

## Rodar testes

### 1. A partir da pasta raiz do projeto rode o comando:
- mvn test

