*** Settings ***
Library    Browser
Library    Collections
Library    OperatingSystem

*** Variables ***
${URL}    http://localhost:8080
${BROWSER}    chromium

*** Test Cases ***
Deve Inserir Cliente  
    Open Browser    ${URL}/clientes/novo    ${BROWSER}
    Wait For Elements State    //input[@id='cidade-cobranca']    visible
    Sleep    1
    Fill Text    //input[@id='cpf']    263.741.020-53
    Fill Text    //input[@id='nome']    João
    Fill Text    //input[@id='sobrenome']    Silva
    Fill Text    //input[@id='dataNascimento']    1999-12-15
    Fill Text    //input[@id='telefone']    (48) 99888-7766
    Fill Text    //input[@id='whatsapp']    (48) 99888-7766
    Fill Text    //input[@id='email']    joao@gmail.com
    Fill Text    //input[@id='cep-domiciliar']    883132-001
    Fill Text    //input[@id='logradouro-domiciliar']    Rua 1
    Fill Text    //input[@id='numero-domiciliar']    131
    Fill Text    //input[@id='bairro-domiciliar']    Bairro 1
    Fill Text    //input[@id='estado-domiciliar']    SC
    Fill Text    //input[@id='cidade-domiciliar']    Itajaí
    Fill Text    //input[@id='cep-cobranca']    883132-002
    Fill Text    //input[@id='logradouro-cobranca']    Rua 2
    Fill Text    //input[@id='numero-cobranca']    132
    Fill Text    //input[@id='bairro-cobranca']    Bairro 2
    Fill Text    //input[@id='estado-cobranca']    SC
    Fill Text    //input[@id='cidade-cobranca']    Itajaí
    Click    //button[@type='submit']
    Wait For Elements State    xpath=//*[contains(text(), 'Cliente salvo com sucesso!')]    visible
    Close Browser

Deve Obter Cliente Por ID
    Open Browser    ${URL}/clientes/editar/8    ${BROWSER}
    ${cpf}=    Get Text    //input[@id='cpf']
    ${nome}=    Get Text    //input[@id='nome']
    Should Be Equal    ${cpf}    789.789.789-78
    Should Be Equal    ${nome}    Juliana
    Close Browser

Deve Obter Listagem Clientes
    Open Browser    ${URL}/clientes    ${BROWSER}
    Wait For Elements State    xpath=//*[contains(text(), 'Gestão de Clientes')]    visible
    ${itens}=    Get Element Count    //tr
    ${itens}=    Convert To String    ${itens}
    Should Be Equal    ${itens}    11
    Close Browser

Deve Remover Cliente
    Open Browser    ${URL}/clientes?pagina=2    ${BROWSER}
    Wait For Elements State    xpath=//*[contains(text(), 'Gestão de Clientes')]    visible
    Sleep    1
    Click    //tbody/tr[1]/td/a[2]
    Wait For Elements State    xpath=//*[contains(text(), 'Cliente removido com sucesso!')]    visible
    Close Browser
    