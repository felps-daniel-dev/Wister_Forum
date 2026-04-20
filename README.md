# 🚀 Projeto Spring Boot - Estudos de API REST

E aí! Este é um projeto que desenvolvi para colocar em prática meus aprendizados com **Java** e **Spring Boot**. A ideia aqui foi construir uma API robusta seguindo as boas práticas de mercado, como o uso de DTOs, tratamento de erros e mapeamento de entidades.

---

### 🧠 O que eu pratiquei aqui:

* **Spring Boot 3**: Configuração e inicialização do projeto.
* **Spring Data JPA**: Mapeamento de entidades (como `Usuario`, `Topico`, `Curso`) e criação de relacionamentos.
* **Mapeamento de Relacionamentos**: Pratiquei como o JPA lida com tabelas de ligação sem precisar criar classes intermediárias desnecessárias (focando no `@ManyToMany` e `@ManyToOne`).
* **Padrão DTO (Data Transfer Object)**: Implementei para garantir que a API não exponha dados sensíveis e aceite apenas o necessário nas requisições (uso de `Form` e `Dto`).
* **Validação de Dados**: Uso de anotações (Bean Validation) para validar o que chega no controller e criação de um `Handler` personalizado para tratar erros de formulário e retornar mensagens amigáveis.
* **Controllers e Endpoints**: Criação de rotas seguindo o padrão REST.

---

### 🏗️ Estrutura do Projeto

O projeto está organizado de uma forma que facilita a manutenção:

* `controller`: Onde ficam os endpoints da API.
* `dto` / `form`: Classes para trânsito de dados (entrada e saída).
* `model`: Nossas entidades que o JPA transforma em tabelas no banco.
* `repository`: Interfaces que fazem a mágica de salvar e buscar dados no banco.
* `config.validacao`: Tratamento customizado de erros.

---

### 🛠️ Tecnologias usadas:

* **Java 21**
* **Spring Boot**
* **Maven** (Gerenciador de dependências)
* **Lombok** (Para evitar código repetitivo)
* **MySQL** 

---


*Este projeto tem fins puramente didáticos.*
