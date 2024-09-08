# Processo seletivo - QA

Bem vindo, candidato. 

Estamos felizes que você esteja participando do processo seletivo para a vaga de QA do Senai - Soluções Digitais.

A prova deverá utilizar as seguintes tecnologias: 
- Linguagem de programação orientada a objeto
- Banco de dados PostgreSQL
- Testes unitários
- Testes de API
- Testes E2E
- Docker
- GIT

Na etapa da entrevista deverá ser apresentado a aplicação em funcionamento.

## Instruções para a execução da prova

***O documento com os requisitos do que precisa ser desenvolvido será enviado por e-mail no horário previsto em edital.***

A prova será uma aplicação web dividida em backend e frontend. Você pode escolher a tecnologia que achar conveniente (PHP, JAVA, etc...).

Fica a escolha do candidato quais frameworks e servidores serão utilizados, desde que seja uma aplicação web. 

***O Banco utilizado na prova deverá ser PostgreSQL.***

Esse repositório possui apenas esse Readme com as instruções da prova. No entanto, **todo desenvolvimento deve ser commitado nesse repositório** até a data citada no email, enviado por nossa equipe.

Commite nesse repositório o script utilizado na criação do banco de dados (se aplicável).

Por fim, altere esse arquivo com as instruções de como poderemos testar o seu código (quais libs usar, qual servidor, etc) abaixo.

## Informações extras

- Descreva ao final deste documento (Readme.md) o detalhamento de funcionalidades implementadas, sejam elas já descritas na modelagem e / ou extras.
- Detalhar também as funcionalidades que não conseguiu implementar e o motivo.
- Caso tenha adicionado novas libs ou frameworks, descreva quais foram e porque dessa agregação.

### Planejamento e organização do tempo

No ponto de planejamento e organização de tempo , eu inicialmente, tive um pequeno problema de não recebimento do link com as informações e documento de requisitos, porem após contato com os responsavel, consegui acesso ao documento de requisitos por volta das 18:28 do sexta feita (06/08).

Assim que obtive o documento, iniciei uma análise detalhada e estabeleci um planejamento dividido em partes ou módulos. Cada módulo incluiria tanto o desenvolvimento da funcionalidade quanto a implementação dos testes correspondentes, evitando que todo o tempo fosse dedicado apenas ao desenvolvimento da aplicação, sem deixar espaço para a fase de testes.

Essa divisão de módulos ficou organizada da seguinte maneira:

- Cliente
    -  Desenvolver as telas de listagem, cadastro, edição e remoção de clientes.

- Agendamento
    - Implementar a tela de cadastro e listagem de agendamentos, além da funcionalidade de edição.

- Consulta
    - Criar a tela para a realização de consultas.

- Login e permissões
    - Gerenciar o login dos usuários e definir as permissões de acesso para cada funcionalidade.

Em termos de organização do tempo, é importante destacar que não pude me dedicar 100% à avaliação, pois, no início do processo, eu estava em Curitiba e já tinha uma viagem de retorno para casa marcada para o domingo. Isso exigiu que eu dividisse meu tempo entre arrumar o local onde estava hospedado (AirBnB), organizar minhas malas e, ao mesmo tempo, trabalhar na avaliação.

Essa situação impactou diretamente meu planejamento inicial, o que me levou a perceber que não seria possível concluir todos os módulos que havia planejado. Mesmo assim, decidi seguir em frente, com o objetivo de entregar pelo menos um módulo completo, funcionando plenamente e com todos os testes devidamente implementados.

Minha organização do tempo seguiu a seguinte estrutura:

-   Dia 1 (Sexta-feira 06/09)
    -   Analise dos requisitos
    -   Configuração inicial das aplicações
    -   Modelagem do banco de dados
    -   Início do desenvolvimento da API de clientes

-   Dia 2 (Sábado 07/09)
    -   Conclusão do desenvolvimento da API de clientes
    -   Desenvolvimento do frontend para a gestão de clientes
    -   Implementação de uma camada de segurança na API
    -   Desenvolvimento dos testes unitários e de API

-   Dia 3 (Domingo 08/09)
    -   Desenvolvimento dos testes E2E (End-To-End)
    -   Elaboração da documentação
    -   Realização de um code review final
    -   Análise com o Sonar

### Desenvolvimento da aplicação

A aplicação foi desenvolvida utilizando a linguagem de programação orientada a objetos **Java** e o framework **Spring Boot**. A arquitetura foi dividida em duas camadas principais: o frontend, implementado com a versão web do Spring Boot, e o backend, utilizando a versão REST do Spring Boot para criação das APIs.

Já o banco de dados foi utilizado o **PostgreSQL**, conforme solicitado no documento de requisitos.

Além disso, o **Robot Framework** foi utilizado para a automação de testes E2E (End-to-End) com a **Browser Library**, e para os testes de API com a **RequestsLibrary**.

### Como executar a aplicação (Docker Compose)

Ao executar o comando abaixo na raiz do projeto, o **Docker Compose** irá iniciar as aplicações **frontend** e **backend**, além do banco de dados **PostgreSQL**:

```
docker-compose up --build
```

Esse processo também criará duas redes (networks) distintas. A primeira conecta o **backend** ao **frontend**, enquanto a segunda conecta o **backend** ao **PostgreSQL**. Essa configuração garante que o frontend não tenha acesso direto ao banco de dados, aumentando a segurança da aplicação.

### Como executar a aplicação (IntelliJ)

Para executar a aplicação utilizando o **IntelliJ**, siga os seguintes passos:

- Crie projetos separados no **IntelliJ** para as aplicações **frontend** e **backend**.

- Inicie as aplicações diretamente pela IDE, selecionando a opção de execução padrão.

Além disso, é necessário ter um servidor **PostgreSQL** rodando localmente na porta 5432, para que o **backend** possa se conectar corretamente ao banco de dados.