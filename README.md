# NetMovie
Aplicativo de filmes para testes de tecnologias

# Tecnologias Usadas

#### 1 - Kotlin Language
#### 2 - Clean Architecture
#### - Modulus App | Core
#### - Firebase

----------------------------------------------------------------------------------------------------

## Clean Architecture:

    - Arquitetura que visa a separação de conceitos dividindo o software em camadas que são
    dependentes, onde cada camada tem sua responsabilidade muito bem definida.

    - A Clean Architecture não é uma arquitetura exclusiva para desenvolvimento mobile. Em outras
    palavras, a Clean Architecture é uma arquitetura que visa a organização de um projeto, em 
    camadas, de forma a alcançar manutenibilidade, escalabilidade e testabilidade.

----------------------------------------------------------------------------------------------------

#### :app => Contém componentes de fremework do Android (view, database, context, navigation, framework)

    - Presentation: Activity, Fragment, Adapters, View Components
    - Framework: ViewModel, Navigation, Room Database, Firebase, Retrofit, Injeção de Dependência

#### :core => Contém regras de negócio, casos de uso e repositórios (nada relacionado com o framework do Android)

    - Use cases: Interfaces e implementações de todos os casos de uso da aplicação
    - Repository: Repositórios de acesso aos dados e implementações de fontes de dados diversos
    - Domain: Classes de domínio da aplicação

----------------------------------------------------------------------------------------------------

#### Firebase:

    - Com o Firebase, você desenvolve apps de alta qualidade, expande sua base de usuários e gera 
    mais receita. Cada recurso funciona de maneira independente.

    - O Firebase Realtime Database é um banco de dados NoSQL hospedado na nuvem. Com ele, você 
    armazena e sincroniza dados entre os seus usuários em tempo real.