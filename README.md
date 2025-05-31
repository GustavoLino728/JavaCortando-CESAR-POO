# ✂️ **JavaCortando - Sistema de Agendamento para Barbearias**

## 📌 Descrição
**JavaCortando** é um sistema desenvolvido para facilitar o agendamento de cortes de cabelo em barbearias.  
O projeto visa modernizar o processo de marcação de horários, permitindo que clientes façam reservas online, editem ou cancelem compromissos, além de gerenciar seus perfis de forma intuitiva.

---

## 🔗 Links do Projeto

<div style="display: inline_block">
  <a href="https://trello.com/invite/b/67db31973176211a647f7134/ATTI70be2f70dd5fde3bf304746c8d65367a25D5ED40/trabalho-programacao-orientada-a-objeto-barbearia">
    <img align="center" alt="Trello" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/trello/trello-original.svg">
  </a>

  <a href="https://www.figma.com/design/eUPMeF3Suui8iQ5W0dQsY4/JaVaCortando?node-id=0-1&t=rCHhRQjuv40g83jF-1">
    <img align="center" alt="Figma" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/figma/figma-original.svg">
  </a>
</div>

---

## 🚀 Deploy do Projeto

🔗 Acesse: [Projeto rodando na Render](https://front-projeto-poo.onrender.com/)

***Obs: Caso de "Not Found" busque pela url: //front-projeto-poo.onrender.com/***
---

## 🧑🏻‍💻 Documentação de Como Rodar a Aplicação
**Adendo**: Atualmente a aplicação roda em um domínio, logo para poder rodar a aplicação algumas alterações no código precisaram ser feitas

### Para rodar o frontend, você precisa ter os seguintes requisitos instalados:

- **Visual Studio Code** ou outra IDE de sua preferência

### Passo 1: GIT clone e Acesse o diretório do frontend

No terminal, clone o repositório e navegue até o diretório do backend:

```bash
git clone https://github.com/GustavoLino728/JavaCortando-CESAR-POO
```

```bash
cd client
```

* Pode também apenas baixar o arquivo.

---

### Passo 2: Instale as dependências

Execute o comando abaixo para instalar todas as dependências do React.JS listadas no arquivo package.json:

```bash
npm install
```

### Passo 3: Inicie a aplicação

Agora, inicie a aplicação React.js com o comando:

```bash
npm start
```

Isso irá rodar o frontend na sua máquina local.

<br>

## Para rodar o backend, você precisa ter os seguintes requisitos instalados:

- **Java** versão 17 (JDK)
- **Docker** e **Docker Compose** instalados

## Instruções de execução

### Passo 1: Acesse o diretório do backend

No terminal, do repositório já clonado navegue até o diretório do backend:

```bash
cd server
```

**AGORA VOCÊ TEM DUAS OPÇÕES, UTILIZAR O DOCKER OU O POSTGRES**

### Alternativa 1 (Melhor): Rodar o Postgres

Vá até o aplication.properties do backend, dentro da aplicação Spring Boot, encontre essas linhas:

```bash
spring.datasource.url=jdbc:postgresql://dpg-d0os91je5dus73d799bg-a.ohio-postgres.render.com:5432/banco_poo
spring.datasource.username=user
spring.datasource.password=zoIjukv1lGDv4HeDcvycXAtHq99NAEk5
```

E troque por essas linhas

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/nomeDoBanco
spring.datasource.username=user
spring.datasource.password=password
```

Normalmente o user é "postgres"

Pronto a partir de agora, você vai precisar criar um banco de dados no postgres e rodar ele.

### Passo 3.2: Suba os containers do Docker

Use o Docker Compose para rodar os containers necessários para o seu backend:

```bash
docker-compose up -d
```

Este comando irá iniciar os containers em segundo plano.

### Pronto agora pode rodar a aplicação e acessar "localhost:3000" no navegador 

### LEMBRANDO QUE A APLICAÇÃO ESTÁ RODANDO PELA INTERNET ENTÃO NÃO PRECISA DESSE TRABALHO TODO

## 📃 Entregas

### 📦 Entrega 1
- 🎥 [Vídeo no YouTube](https://youtu.be/8Q1tQ8D2EZQ?si=sDhOYCMUrof_788Q)
- 📋 [Histórias de usuário no Trello](https://trello.com/invite/b/67db31973176211a647f7134/ATTI70be2f70dd5fde3bf304746c8d65367a25D5ED40/trabalho-programacao-orientada-a-objeto-barbearia)

---

### 📦 Entrega 2
- ✅ Entrega via commit das 2 histórias definidas no Trello.
- 📊 Diagrama de classes:
  <img src="diagramas/DiagramaEntrega2.png" width="1000px;" alt="Diagrama de Classes"/>

---

### 📦 Entrega 3
- ✅ Entrega via commit de 3 histórias, testes, diagramas e vídeo screencast.
- 📊 Diagrama de classes:
  <img src="diagramas/DiagramaEntrega3.png" width="1000px;" alt="Diagrama de Classes"/>
- 🎥 [Vídeo das atualizações](https://youtu.be/7tUJrM01ImM)

---

### 📦 Entrega 4
- ✅ Entrega via commit de 3 histórias, testes, diagramas e vídeo screencast.
- 📊 Diagramas de classes:
  <img src="diagramas/DiagramaEntrega4Parte1.png" width="1000px;" alt="Diagrama de Classes"/>
  <img src="diagramas/DiagramaEntrega4Parte2.png" width="1000px;" alt="Diagrama de Classes"/>
- 🎥 Link para vídeo das atualizações: _[Em breve]_

---

## 🌐 Autores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/DavidaMendes">
        <img src="https://avatars.githubusercontent.com/u/166074227?v=4" width="100px;" alt="Davi Mendes"/><br/>
        <sub><b>Davi Mendes</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/giulms">
        <img src="https://avatars.githubusercontent.com/u/163376922?v=4" width="100px;" alt="Giulliano Lucas"/><br/>
        <sub><b>Giulliano Lucas</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/GustavoLino728">
        <img src="https://avatars.githubusercontent.com/u/161669997?v=4" width="100px;" alt="Gustavo Lino"/><br/>
        <sub><b>Gustavo Lino</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/ItaloVasconcelos05">
        <img src="https://avatars.githubusercontent.com/u/163598100?v=4" width="100px;" alt="Ítalo Artur"/><br/>
        <sub><b>Ítalo Artur</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/henryzera">
        <img src="https://avatars.githubusercontent.com/u/171767864?v=4" width="100px;" alt="Emanuel Henry"/><br/>
        <sub><b>Emanuel Henry</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/brittola">
        <img src="https://avatars.githubusercontent.com/u/99913525?v=4" width="100px;" alt="Gabriel Rodrigues"/><br/>
        <sub><b>Gabriel Rodrigues</b></sub>
      </a>
    </td>
  </tr>
</table>

---

### Feito com ❤️ por:

- Davi Mendes  
- Giulliano Lucas  
- Gustavo Lino  
- Ítalo Artur  
- Emanuel Henry  
- Gabriel Rodrigues

---
