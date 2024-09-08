*** Settings ***
Library    RequestsLibrary

*** Variables ***
${BASE_URL}    http://localhost:8081
${API_KEY}     8KxwLF0cAc
${CLIENT_ID}   None

*** Test Cases ***
Deve Inserir Cliente
    Create Session    cliente    ${BASE_URL}    headers={'X-API-KEY': '${API_KEY}'}
    &{enderecoA}=    Create dictionary    cep=24456-500    logradouro=Rua Lindóia    numero=65    bairro=Trindade    estado=RJ    cidade=São Gonçalo
    &{enderecoB}=    Create dictionary    cep=78131-108    logradouro=Rua Ianomani    numero=12    bairro=Vitória Régia    estado=MT    cidade=Várzea Grande
    &{cliente}=    Create dictionary    id=31    cpf=656.796.578-95    nome=Evelyn    sobrenome=Betina    dataNascimento=1945-05-07    telefone=(11) 3802-4828    whatsapp=(11) 98207-6508    email=evelyn_nunes@emitec.com.br    enderecoDomiciliar=${enderecoA}    enderecoCobranca=${enderecoB}
    ${resp_cliente}=    PUT On Session    cliente    /cliente    json=${cliente}
    Should Be Equal As Numbers    ${resp_cliente.status_code}    201
    Set Global Variable    ${CLIENT_ID}    ${resp_cliente.json()['id']}
    Log    ${resp_cliente.json()}

Deve Obter Cliente Por ID
    Create Session    cliente    ${BASE_URL}    headers={'X-API-KEY': '${API_KEY}'}
    ${resp_cliente}=    GET On Session    cliente    /cliente/1
    Should Be Equal As Numbers    ${resp_cliente.status_code}    200
    Log    ${resp_cliente.json()}

Deve Obter Listagem Clientes
    Create Session    cliente    ${BASE_URL}    headers={'X-API-KEY': '${API_KEY}'}
    ${resp_cliente}=    GET On Session    cliente    /cliente/listagem/0
    Should Be Equal As Numbers    ${resp_cliente.status_code}    200
    Log    ${resp_cliente.json()}

Deve Remover Cliente
    Create Session    cliente    ${BASE_URL}    headers={'X-API-KEY': '${API_KEY}'}
    Should Not Be Equal    ${CLIENT_ID}    None
    ${resp_cliente}=    DELETE On Session    cliente    /cliente/${CLIENT_ID}
    Should Be Equal As Numbers    ${resp_cliente.status_code}    200
    
