# Microsserviço para Cadastro e Consulta de Alunos e Cursos

[![Website](https://img.shields.io/website?down_message=offline&label=Application%20Status&up_message=online&url=https%3A%2F%2Fdesafio-backend-api.fly.dev%2Fhealth)](https://desafio-backend-api.fly.dev/)
[![License MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://github.com/detds/desafioti360-backend/blob/main/LICENSE)

- [Microsserviço para Cadastro e Consulta de Alunos e Cursos](#microsserviço-para-cadastro-e-consulta-de-alunos-e-cursos)
  - [Sobre](#sobre)
  - [Endpoints disponíveis e funcionalidades](#endpoints-disponíveis-e-funcionalidades)
  - [Como executar a aplicação](#como-executar-a-aplicação)
    - [Pré-requisitos](#pré-requisitos)
    - [Ambiente de Desenvolvimento](#ambiente-de-desenvolvimento)
    - [Ambiente de Teste](#ambiente-de-teste)
  - [Como utilizar a aplicação hospedada no Fly.io](#como-utilizar-a-aplicação-hospedada-no-flyio)
  - [Tecnologias utilizadas](#tecnologias-utilizadas)
  - [Contribuição](#contribuição)
  - [Licença](#licença)

## Sobre
Microsserviço que permite cadastro e consulta de alunos e cursos de forma simples, desenvolvido em linguagem Java utilizando framework Spring. Abaixo, você encontrará um guia rápido sobre como utilizar este serviço.

**Link para acessar a API:** https://desafio-backend-api.fly.dev/

## Endpoints disponíveis e funcionalidades
Aqui estão os endpoints disponíveis para interação com o microsserviço:

1. Cadastro de Aluno
   - Método: POST
   - Endpoint: `/alunos`
   - Descrição: Permite o cadastro de novos alunos com informações como nome, idade e e-mail. Envie os campos em um objeto JSON no corpo da solicitação:
   ```json
   {
        "nome": "Nome do Aluno",
        "idade": 21,
        "email": "email@exemplo.com"
    }
   ```
2. Cadastro de Curso
   - Método: POST
   - Endpoint: `/cursos`
   - Descrição: Permite o cadastro de novos cursos com informações como nome. Envie os campos em um objeto JSON no corpo da solicitação:
   ```json
   {
        "nome": "Nome do Curso"
    }
   ```
3. Consulta de Aluno
   - Método: GET
   - Endpoint: `/alunos/{numero-matricula}`
   - Descrição: Possibilita a consulta de um aluno específico através do número de matrícula.
4. Consulta de Curso
   - Método: GET
   - Endpoint: `/cursos/{codigo-curso}`
   - Descrição: Possibilita a consulta de um curso específico através do código do curso.
5. Atualização de Dados do Aluno
   - Método: PUT
   - Endpoint: `/alunos/{numero-matricula}`
   - Descrição: Permite a atualização dos dados de um aluno, buscando pelo número de matrícula. É necessário enviar um objeto JSON com os campos do aluno que serão alterados no corpo da solicitação.
6. Atualização de Dados do Curso
   - Método: PUT
   - Endpoint: `/cursos/{codigo-curso}`
   - Descrição: Permite a atualização dos dados de um curso, buscando pelo código do curso. É necessário enviar um objeto JSON com os campos do curso que serão alterados no corpo da solicitação.
7. Exclusão de Aluno
   - Método: DELETE
   - Endpoint: `/alunos/{numero-matricula}`
   - Descrição: Permite a exclusão de um aluno a partir do número de matrícula.
8. Exclusão de Curso
   - Método: DELETE
   - Endpoint: `/cursos/{codigo-curso}`
   - Descrição: Permite a exclusão de um curso a partir do código do curso.
9. Adicionar Lista de Alunos em um Curso
    - Método: POST
    - Endpoint: `/cursos/{codigo-curso}/alunos`
    - Permite adicionar uma lista de alunos a um curso específico, usando o código do curso como parâmetro. Envie no corpo da solicitação os números das matrículas dos alunos conforme o modelo abaixo:
    ```json
    {
        "matrículas": [
            1,
            2,
            3
        ]    
    }
    ```
10. Remover Lista de Alunos de um Curso
    - Método: DELETE
    - Endpoint: `/cursos/{codigo-curso}/alunos`
    - Descrição: Permite remover uma lista de alunos de um curso específico, usando o código do curso como parâmetro. Envie no corpo da solicitação os números das matrículas dos alunos conforme o modelo abaixo:
    ```json
    {
        "matrículas": [
            1,
            2,
            3
        ]    
    }
    ```

## Como executar a aplicação
A aplicação possui três tipos de configurações de ambiente:

- Ambiente de Desenvolvimento: Use este ambiente para desenvolver e testar localmente. Requer configuração local do banco de dados PostgreSQL.
- Ambiente de Teste: Use este ambiente para testes locais. Utiliza o banco de dados H2-Database.
- Ambiente de Produção: Configuração específica para implantação em um ambiente de produção. Não é abordado neste guia.
### Pré-requisitos
Antes de executar a aplicação, certifique-se de que você tenha os seguintes pré-requisitos instalados em sua máquina:
- Java 17 ou superior
- PostgreSQL (para teste no ambiente de desenvolvimento)

### Ambiente de Desenvolvimento
1. Clone o repositório em sua máquina:
   ```bash
   git clone https://github.com/detds/desafioti360-backend.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd desafioti360-backend
   ```
3. Edite o arquivo application-dev.yml para configurar as informações específicas do banco de dados em sua própria máquina:
   ```yml
   spring:
      datasource:
         url: jdbc:postgresql://localhost:5432/seu-banco-de-dados
         username: seu-usuario
         password: sua-senha
   ```
4. Salve as alterações no arquivo application-dev.yml.
5. Compile o código-fonte para gerar o arquivo JAR usando o Maven Wrapper:
   ```bash
   ./mvnw clean package
   ```
6. Execute a aplicação usando o perfil de desenvolvimento:
   ```bash
   java -jar -Dspring.profiles.active=dev target/sua-aplicacao.jar
   ```

### Ambiente de Teste
Para testar a aplicação no ambiente de teste que utiliza banco de dados H2-Database:

1. Clone o repositório para sua máquina (caso ainda não tenha feito):
   ```bash
   git clone https://github.com/detds/desafioti360-backend.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd desafioti360-backend
   ```
3. Compile o código-fonte para gerar o arquivo JAR usando o Maven Wrapper:
   ```bash
   ./mvnw clean package
   ```
4. Execute a aplicação usando o perfil de desenvolvimento:
   ```bash
   java -jar -Dspring.profiles.active=test target/sua-aplicacao.jar
   ```


## Como utilizar a aplicação hospedada no Fly.io
A API já está hospedada no Fly.io através do endereço https://desafio-backend-api.fly.dev/. Você pode interagir com a API usando ferramentas como o Postman ou diretamente através de solicitações HTTP.

Aqui estão os passos básicos para começar:

1. Abra o seu aplicativo Postman ou qualquer outra ferramenta que você prefira para fazer solicitações HTTP.
2. Configure as solicitações com os endpoints relevantes conforme descrito na seção [Endpoints Disponíveis e Funcionalidades](#endpoints-disponíveis-e-funcionalidades).
3. Faça solicitações para a API, incluindo os métodos HTTP apropriados (por exemplo, POST, GET, PUT, DELETE) e os URLs dos endpoints.
4. Forneça os parâmetros necessários nas solicitações, como dados de entrada em JSON ou parâmetros de consulta, dependendo da operação desejada.
5. Envie as solicitações para a API e aguarde as respostas correspondentes.

Você pode importar uma coleção do Postman onde já está definido todos os endpoints para testes na pasta /docs ou através do link: https://github.com/detds/desafioti360-backend/tree/main/docs/Postman

## Tecnologias utilizadas
Este microsserviço foi desenvolvido utilizando as seguintes tecnologias e ferramentas:
- **Java**
- **Spring**
- Banco de Dados Relacionais:
  -  **PostgreSQL**: Banco de dados relacional utilizado para armazenar dados em produção.
  -  **H2**: Banco de dados de teste em memória para desenvolvimento e testes.
- Hospedagem em Nuvem: 
  - **Fly.io**: Serviço de hospedagem em nuvem usado para implantar e executar o microsserviço em produção.
- **Postman**: Utilizado para testar e documentar as APIs. Coleção para este microsserviço pode ser acessada no repositório em [/docs](https://github.com/detds/desafioti360-backend/tree/main/docs/Postman).
- **Swagger**: Utilizado para criar documentação interativa da API, facilitando a compreensão e teste dos endpoints. Pode acessar através do endpoint `/swagger-ui.html`.
  
![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![H2-Database Badge](https://img.shields.io/badge/H2Database-1021FF?style=for-the-badge)
![Fly.io Badge](https://img.shields.io/badge/Fly.io-A572ED?style=for-the-badge)
![Postman Badge](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
![Swagger Badge](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)

## Contribuição
Este projeto é de código aberto e está aberto a contribuições. Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar este microsserviço.

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](https://github.com/detds/desafioti360-backend/blob/main/LICENSE) para obter mais detalhes.