Projeto: Pesquisa de Filmes Vencedores
Este projeto consiste em uma aplicação Spring Boot que processa dados de filmes a partir de um arquivo CSV e oferece uma API REST para consultar informações sobre filmes vencedores de prêmios e intervalos de anos entre os prêmios dos produtores.

Pré-requisitos
Certifique-se de que os seguintes pré-requisitos estão instalados:

Java 11
Maven 3.6 ou superior
Banco de dados (H2)

Execução do Projeto
1. Clonando o repositório
Clone o repositório do projeto para sua máquina local:
git clone https://github.com/usuario/projeto-filmes.git
cd projeto-filmes

2. Executando o Projeto
Para executar o projeto localmente, basta usar o Maven:
mvn clean install
mvn spring-boot:run

3. Acessando a API
A aplicação oferece uma API REST com o endpoint para consultar filmes vencedores:

GET /movie/pesquisar-filmes: Retorna os produtores que venceram mais de um prêmio e os intervalos entre os anos dos prêmios.
http://localhost:8080/movie/pesquisar-filmes
curl -X GET http://localhost:8080/movie/pesquisar-filmes

4. Swagger UI
O Swagger UI fornece uma interface gráfica interativa para explorar a API. Para acessar o Swagger UI, basta abrir o navegador e ir para o seguinte endereço:
http://localhost:8080/swagger-ui.html

Testes
1. Executando Testes Unitários
Para rodar os testes unitários, use o comando Maven:
mvn test

