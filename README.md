# 🍺 O Buteco — Sistema de Gestão de Estoque

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Flyway-Migration-red?style=for-the-badge&logo=flyway" />
</p>

> Projeto acadêmico desenvolvido no **1º Semestre de 2026** na faculdade **UniAmérica**, com o objetivo de criar um sistema completo de gestão de estoque para um buteco local.

---

## 📋 Sobre o Projeto

O **O Buteco** é um sistema desktop desenvolvido em Java que permite o controle total do estoque de um estabelecimento comercial. O sistema contempla o cadastro de usuários, produtos e o gerenciamento completo de entradas e saídas de estoque, com rastreamento de movimentações.

---

## 🖥️ Telas do Sistema

| Tela | Descrição |
|---|---|
| **Login** | Autenticação com nome, e-mail e senha |
| **Menu Principal** | Acesso aos módulos: Usuário, Produtos e Estoque |
| **Cadastro de Usuários** | Gerenciamento de usuários com cargo e status de login |
| **Cadastro de Produtos** | Cadastro com nome, preço, status, categoria, grupo e insumos |
| **Gestão de Estoque** | Entradas, saídas, movimentações e controle por unidade/local |

---

## 🛠️ Tecnologias Utilizadas

### Linguagem & Plataforma
- **Java 17** — Linguagem principal do projeto;
- **Java Swing** — Framework para construção da interface gráfica desktop.

### Banco de Dados & Migração
- **Flyway** — Controle de versão e migração do banco de dados relacional;
- **PostGres** — Sistema avançado de gerenciamento de banco de dados objeto-relacional.

### Ambiente & Ferramentas
- **Maven / Gradle** — Gerenciamento de dependências;
- **IntelliJ IDEA / NetBeans** — IDEs utilizadas no desenvolvimento;
- **Git** — Controle de versão do código-fonte.

---

## 📦 Funcionalidades

### 👤 Módulo de Usuários
- Cadastro de usuários com nome, cargo e senha;
- Edição e exclusão de usuários;
- Controle de status de login;
- Listagem em tabela com ID, Nome, Usuário, Status Login e Cargo.

### 🛒 Módulo de Produtos
- Cadastro de produtos com nome, preço, status, categoria e grupo;
- Associação de insumos e quantidades por produto;
- Adição, edição e exclusão de produtos;
- Visualização de produtos e insumos utilizados em tabelas.

### 📦 Módulo de Estoque
- Cadastro de entradas e saídas de produtos;
- Definição de unidade de estoque e local de armazenamento;
- Conversão de unidades (ex.: 1kg = 1000g);
- Listagem de estoques por produto, quantidade, local e unidade;
- Histórico completo de movimentações com data, usuário e tipo (Entrada/Saída).

---

## 🗂️ Estrutura do Projeto

```
OButeco/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├──buteco/
│   │   │   │   ├── config/           # Configuração do FlyWay e do Jpa
│   │   │   │   ├── controller/       # Lógica de controle das telas
│   │   │   │   ├── enums/            # Constantes pré-definadas 
│   │   │   │   ├── model/            # Entidades do sistema
│   │   │   │   ├── repositories/     # Acesso ao banco de dados
│   │   │   │   ├── view/             # Telas Swing (GUI)
│   │   │   │   └── service/          # Regras de negociação
│   └── resources/
│       ├── db/migration/         # Scripts SQL do Flyway
│       └── icons/                # Icones padrões do sistema
├── .env                              # Variaveis de ambiente do projeto (credencias de conexao etc) 
├── .env.example                      # Arquivo de exemplo de configuracao das variaveis de ambiente
├── .gitignore                        # Especifica quais arquivos ou pastas devem ser ignorados pelo versionamento de código
├── pom.xml / build.gradle
└── README.md
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17 instalado
- Banco de dados configurado (PostgreSQL)
- Maven instalado

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/HenriqueHBM/OButeco.git

# 2. Acesse o diretório do projeto
cd OButeco

# 3. Crie o arquivo .env com as credenciais do banco
cp .env.example .env
# Edite o .env com seu editor preferido e preencha os dados do banco

# 4. Execute o build (Maven)
mvn clean package
  

# 5. Execute a aplicação
java -jar target/o-buteco-1.0.jar
```

As migrações do Flyway serão executadas automaticamente na primeira inicialização, criando todas as tabelas necessárias.

---

## 👥 Equipe de Desenvolvimento

| Participante            | Papel         |
|-------------------------|---------------|
| **Jonas L. Pereira**    | Líder         |
| **Henrique B. Madeira** | Desenvolvedor |
| **Daniela E. Moreira**  | Scrum Master  |
| **João V. de Mello**    | Desenvolvedor |

---

## 🎓 Informações Acadêmicas

| Campo | Informação |
|---|---|
| **Instituição** | UniAmérica |
| **Curso** | Engenharia de Software |
| **Período** | 1º Semestre de 2026 |
| **Disciplina** | Projeto Integrador / Desenvolvimento de Sistemas |

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos. Todos os direitos reservados à equipe de desenvolvimento — UniAmérica, 2026.

---
