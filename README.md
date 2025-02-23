
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
Desenvolvi todas as rotas conforme solicitado, cumprindo os requisitos do projeto. Esta API contém todas as operações de CRUD, utilizando DTOs para definir os parâmetros de cada método. Os DTOs são fundamentais para padronizar as requisições de dados e evitar a exposição das nossas entidades durante as chamadas.

### Persistencia com JPA 
Implementei a persistência dos dados utilizando JPA, garantindo uma gravação eficiente dos objetos. No banco de dados, criei duas tabelas: tb_address e tb_person. Na tabela tb_person, configurei uma chave estrangeira que referencia tb_address, permitindo associar um endereço a cada pessoa de maneira consistente e eficaz.

### Exception

Tratei as exceções das rotas `deleteById`, `updatePerson` e `associateAddress` de forma global, criando uma classe responsável por lançar mensagens de exceção caso os dados não sejam encontrados ou caso um ID em formato incorreto seja passado. Com isso, conseguimos antecipar e tratar futuros erros de consistência de maneira eficaz.
