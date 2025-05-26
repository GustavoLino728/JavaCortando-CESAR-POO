    import random

*** Settings ***
Library    SeleniumLibrary
Resource    ../resources/variables.robot
Resource    ../elements/autenticacao_elements.robot

*** Keywords ***

Dado que o usuario entra no site
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window
E clica no botão "Como Cliente"
    Click Element    ${BTN_CLIENTE}
    Sleep    2s
E clica no botão "Como Barbeiro"
    Click Element    ${BTN_BARBEIRO}
    Sleep    2s
Quando o barbeiro fornece credenciais válidas
    Input text    ${INPUT_USUARIO}    ${USUARIO_BARBEIRO}
    Input Password    ${INPUT_SENHA}    ${SENHA_BARBEIRO}
Quando o cliente fornece credenciais válidas
    Input text    ${INPUT_USUARIO}    ${USUARIO_CLIENTE}
    Input Password    ${INPUT_SENHA}    ${SENHA_CLIENTE}
E o cliente fornece credenciais válidas
    Input text    ${INPUT_USUARIO}    ${USUARIO_CLIENTE}
    Input Password    ${INPUT_SENHA}    ${SENHA_CLIENTE}
Quando o cliente fornece credenciais inválidas
    Input text    ${INPUT_USUARIO}    ${USUARIO_CLIENTE}
    Input Password    ${INPUT_SENHA}    ${SENHA_CLIENTE_INVALIDA}

Quando o barbeiro fornece credenciais inválidas
    Input text    ${INPUT_USUARIO}    ${USUARIO_BARBEIRO}
    Input Password    ${INPUT_SENHA}    ${SENHA_BARBEIRO_INVALIDA}
E clica no botão "Entrar"
    Click Element    ${BOTAO_ENTRAR}
    Sleep    30s
Então o usuario é redirecionado para a página da conta
    Capture Page Screenshot
    Sleep    3s
Então o usuário recebe uma mensagem de erro
    Capture Page Screenshot
Quando preencho o campo agendar
    Input Text    ${CAMPO_DATA_PLACEHOLDER}    15/${NUMERO_ALEATORIO}/2025
    Input Text    ${DROPDOWN_HORARIO}    ${HORARIO}
    Click Element    ${BTN_AGENDAR_CORTES}
    Sleep    2s
E clico no botão "Agendar Cortes"
    Click Element    ${BTN_AGENDAR_CORTES}
    Sleep    5s