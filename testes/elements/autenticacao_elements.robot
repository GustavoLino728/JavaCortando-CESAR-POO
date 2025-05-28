*** Settings ***
Documentation    Elementos da página de autenticação

*** Variables ***
${BTN_CLIENTE}    xpath=//button[text()='Como Cliente']
${BTN_BARBEIRO}    xpath=//button[text()='Como Barbeiro']
${INPUT_USUARIO}    id=username
${INPUT_SENHA}    id=senha
${BOTAO_ENTRAR}    xpath=//button[text()='Entrar']
${CAMPO_DATA_PLACEHOLDER}    xpath=//input[@placeholder='DD/MM/AAAA']
${DROPDOWN_HORARIO}    id=horario
${BTN_AGENDAR_CORTES}    xpath=//button[text()='Agendar Cortes']