*** Settings ***
Documentation  Suite de testes
Resource    ../pages/autenticacao_page.robot

*** Test Cases ***
Entrar na plataforma como Cliente com conta existente
    Dado que o usuario entra no site
    E clica no botão "Como Cliente"
    Quando o cliente fornece credenciais válidas
    E clica no botão "Entrar"
    Então o usuario é redirecionado para a página da conta

Entrar na plataforma como Barbeiro com conta existente
    Dado que o usuario entra no site
    E clica no botão "Como Barbeiro"
    Quando o barbeiro fornece credenciais válidas
    E clica no botão "Entrar"
    Então o usuario é redirecionado para a página da conta

Agendar corte de cabelo
    Dado que o usuario entra no site
    E clica no botão "Como Cliente"
    E o cliente fornece credenciais válidas
    E clica no botão "Entrar"
    Quando preencho o campo agendar
    E clico no botão "Agendar Cortes"
    Então o usuario é redirecionado para a página da conta
    
#Inválidos
Entrar na plataforma como Cliente com senha inválida
    Dado que o usuario entra no site
    E clica no botão "Como Cliente"
    Quando o cliente fornece credenciais inválidas
    E clica no botão "Entrar"
    Então o usuário recebe uma mensagem de erro
Entrar na plataforma como Barbeiro com conta inexistente
    Dado que o usuario entra no site
    E clica no botão "Como Barbeiro"
    Quando o barbeiro fornece credenciais inválidas
    E clica no botão "Entrar"
    Então o usuário recebe uma mensagem de erro