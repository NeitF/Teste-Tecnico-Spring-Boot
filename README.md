# Teste-Tecnico-Spring-Boot

Objetivo:
Praticar codificação utilizando Spring Boot, visando aplicar princípios de Clean Code e qualidade de software

- Fazer instalação das dependências a partir do Maven
- Iniciar aplicação a partir da classe: SimpleApiApplication
- Executar testes a partir da classe: SimpleApiApplicationTests
- Versão Java: 17

Observações breves sobre decisões estruturais tomadas:

<h4>Uso do WireMock nos testes de integração</h4>
Usado para simular o comportamento da API externa, visando testar como a aplicação se comporta quando retorna status code 200 ou 400

<h4>Url base no application.properties</h4>
Supondo que a API externa tenha uma URL base para ambiente de testes e outra distintas para ambiente de produção, optar por criar uma variável no application.properties facilita que uma pipeline de CD injete dinamicamente a URL correta a depender do ambiente em que está fazendo deploy

<h4>Service depende de interface ao invés de implementação</h4>
Visando maior flexibilidade, supondo que a api externa (airportsApi) seja alterada por outra no futuro, fazer a service depender de uma interface e não de uma implementação permite que no cenário  descrito não seja necessário alterar nada da Service. Bastaria criar uma nova implementação que herda da interface provider (IAirportProvider)
