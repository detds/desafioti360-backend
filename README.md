#  Microsserviço para Cadastro e Consulta de Alunos e Cursos
Microsserviço que permite cadastro e consulta de alunos e cursos de forma simples, desenvolvido em linguagem Java utilizando framework Spring. Abaixo, você encontrará um guia rápido sobre como utilizar este serviço.


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

## Como Utilizar a Aplicação
A API já está hospedada em um ambiente externo, portanto, você não precisa baixá-la ou executá-la localmente. Você pode interagir com a API usando ferramentas como o Postman ou diretamente através de solicitações HTTP.

Aqui estão os passos básicos para começar:

1. Abra o seu aplicativo Postman ou qualquer outra ferramenta que você prefira para fazer solicitações HTTP.
2. Configure as solicitações com os endpoints relevantes conforme descrito na seção [Endpoints Disponíveis e Funcionalidades](#endpoints-disponíveis-e-funcionalidades) acima.
3. Faça solicitações para a API, incluindo os métodos HTTP apropriados (por exemplo, POST, GET, PUT, DELETE) e os URLs dos endpoints.
4. Forneça os parâmetros necessários nas solicitações, como dados de entrada em JSON ou parâmetros de consulta, dependendo da operação desejada.
5. Envie as solicitações para a API e aguarde as respostas correspondentes.

## Tecnologias Utilizadas
Este microsserviço foi desenvolvido utilizando as seguintes tecnologias e ferramentas:
- Java
- Spring
- Banco de Dados Relacionais:
  -  PostgreSQL: Banco de dados relacional utilizado para armazenar dados em produção.
  -  H2: Banco de dados de teste em memória para desenvolvimento e testes.
- Hospedagem em Nuvem: 
  - Fly.io: Serviço de hospedagem em nuvem usado para implantar e executar o microsserviço em produção.
- Postman: Utilizado para testar e documentar as APIs.
- Swagger: Utilizado para criar documentação interativa da API, facilitando a compreensão e teste dos endpoints. Pode acessar através do endpoint `/swagger-ui.html`

## Contribuição
Este projeto é de código aberto e está aberto a contribuições. Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar este microsserviço.

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter mais detalhes.