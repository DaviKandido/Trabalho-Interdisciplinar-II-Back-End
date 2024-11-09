const apiUrl1 = "https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/form"

const LCapiUrl = 'https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/';

const apiUrl6 = 'https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/users';

const apiUrl5 = 'https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/ong';

const apiUrlJP = 'https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/animais';

const apiUrlCad = 'https://2133d8a5-e072-4649-b506-f9e5e3cd1051-00-ktmrhyp8n02v.janeway.replit.dev/users';


// ------------------------------------------------------------ Gabriel Silva -------------------------------------------------------------------

// Set para rastrear IDs únicos
const generatedIds = new Set();

function generateUniqueRandomId(min, max) {
    let id;
    // Gera um novo ID até que ele seja único
    do {
        id = Math.floor(Math.random() * (max - min + 1)) + min;
    } while (generatedIds.has(id));

    // Adiciona o novo ID ao conjunto de IDs gerados
    generatedIds.add(id);
    return id;
}


async function createForm(form) {
    // Tentar fazer a chamada
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(apiUrl1, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(form),
        });
        // Mostrar resultado
        const result = await response.json();
        console.log(result);
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
    }
}

async function Nome() {
    // Definir dados locais
    let strContent = '';
    let novoForm = '';

    strContent = document.getElementById('nome').value;
    document.getElementById('nome').value = '';
    // Testar se há algo a ser publicado
    if (strContent) {
        // Criar objeto novoForm
        novoForm = {
            "nome": strContent
        };
        // Salvar dados no JSON Server
        createForm(novoForm);
        // Atualizar os dados na tela
        mostrarDados();
    }
}


let perguntas = document.getElementsByClassName("pergunta");
let indicePerguntaAtual = 0;
let campos = [];


async function perguntaFinal() {
    // definir dados da url
    let urlParams = new URLSearchParams(window.location.search);
    let animalId = urlParams.get('id');
    let objAnimal = await readAnimalJP(animalId);
    // buscar animal especifico
    let uniqueId = generateUniqueRandomId(1, 100);
    let novoForm = {
        "id": uniqueId,
        "animal": objAnimal.nome,
        "aondefica": campos[3],
        "nome": campos[0],
        "sexo": campos[5],
        "idade": campos[4],
        "cidade": campos[6],
        "telefone": campos[2],
        "email": campos[1],
        "mora": campos[7],
        "cientes": campos[9],
        "apliberado": campos[8],
        "teveanimal": campos[10],
        "animalsozinho": campos[11],
        "permisao": campos[12],
        "imagem": objAnimal.imagem[0]
    };
    console.log(campos[12]);
    console.log(novoForm);
    createForm(novoForm);
    window.location.href = '../../index.html';
}

function mostrarProximaPergunta() {
    perguntas[indicePerguntaAtual].style.display = "none"; // Oculta a pergunta atual

    let id = 'pergunta' + (indicePerguntaAtual + 1);
    let campoInput = document.querySelector(`#${id} .resposta`);
    //let valor = campoInput ? campoInput.value.trim() : null; // Obtém o valor do campo e remove espaços em branco
    let valor = campoInput.value;

    if (valor !== "") {
        // Verifica se o valor não está vazio
        campos[indicePerguntaAtual] = valor;
        // Armazena o valor no array campos
        indicePerguntaAtual++;
        // Avança para a próxima pergunta
        if (indicePerguntaAtual < perguntas.length) {
            perguntas[indicePerguntaAtual].style.display = "block"; // Exibe a próxima pergunta
        }
        else {
            perguntaFinal();
        }
    }
    else {
        alert("Por favor, preencha a resposta antes de prosseguir."); // Alerta se o campo estiver vazio
        perguntas[indicePerguntaAtual].style.display = "block";
    }
}


// ----------------------------------------------- Letícia Silva ----------------------------------------------------------------
async function readRequirements() {
    let requirements = {};
    try {
        const response = await fetch(apiUrl1);
        console.log("Response:", response);
        requirements = await response.json();
        console.log("Requirements:", requirements);
    } catch (error) {
        console.error("Error:", error);
    }
    return requirements;
}

async function imprimeRequerimentos() {
    let tela = document.getElementById('tela');
    let strHtml = '';
    let objRequerimentos = await readRequirements();
    console.log(objRequerimentos);
    strHtml += '<div class="row">';
    if (objRequerimentos.length > 0) {
        for (let i = 0; i < objRequerimentos.length; i++) {
            strHtml += `
                    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 Requerimentos" id="card-${i}">
                        <div class="card caixa">
                            <div class="card imagemreq" id="imagem-${i}" style="font-size: 3vw;">
                                <img class = img-animal src="${objRequerimentos[i].imagem}" alt="img-animal" style="width: 100%;">
                                <div class="card-text">
                                    <h5 class="req-animal">${objRequerimentos[i].animal}</h5>
                                </div>
                            </div>
                            <div class="centerB">
                                <div class="button">
                                    <button type="button" class="adotou" data-id="${objRequerimentos[i].id}">Adotou</button>
                                    <button type="button" class="nao-adotou" data-id="${objRequerimentos[i].id}">Não Adotou</button>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
        }

        strHtml += '</div>';
        tela.innerHTML = strHtml;




        // Configura os botões de imagem
        for (let i = 0; i < objRequerimentos.length; i++) {
            document.getElementById(`imagem-${i}`).addEventListener('click', function() {
                imprimeformulario(objRequerimentos[i]);
              });
        }


        let botoesNaoAdotou = document.querySelectorAll('.nao-adotou');

        botoesNaoAdotou.forEach(botao => {
            botao.addEventListener('click', function(event) {
                let id = event.target.getAttribute('data-id');
                // document.getElementById('card-' + id).remove();

                alert('Requerimento descartado! ');
                // Envia a solicitação de DELETE para o servidor
                fetch(apiUrl1 + '/' + id, {
                    method: 'DELETE',
                }).then(response => {
                    if (!response.ok) {
                        throw new Error('Erro na requisição HTTP: ' + response.status);
                    }
                    else {
                        location.reload();
                    }
                }).catch(error => {
                    console.error('Erro na remoção do servidor:', error);
                });
            });

        });

        document.querySelectorAll('.adotou').forEach(botao => {
            botao.addEventListener('click', async function(event) {
                try {
                    // Obtém o ID do formulário do atributo data-id do botão clicado
                    let formId = event.target.getAttribute('data-id');
                    if (!formId) {
                        throw new Error('ID do formulário não encontrado no botão clicado.');
                    }

                    alert('Concluido! ');
                    // Busca o formulário pelo ID para obter o nome do animal
                    const formResponse = await fetch(apiUrl1 + '/' + formId);
                    if (!formResponse.ok) {
                        throw new Error('Erro na requisição HTTP para obter formulário: ' + formResponse.status);
                    }
                    const form = await formResponse.json();
                    let animalNome = form.animal;

                    // Busca o animal pelo nome para obter o ID do animal
                    const animaisResponse = await fetch(apiUrlJP + '?nome=' + animalNome);
                    if (!animaisResponse.ok) {
                        throw new Error('Erro na requisição HTTP para obter animal: ' + animaisResponse.status);
                    }
                    const animais = await animaisResponse.json();
                    if (animais.length === 0) {
                        throw new Error('Animal não encontrado: ' + animalNome);
                    }
                    let animalId = animais[0].id;

                    // Envia a solicitação de DELETE para o animal
                    const deleteAnimalResponse = await fetch(apiUrlJP + '/' + animalId, {
                        method: 'DELETE',
                    });
                    if (!deleteAnimalResponse.ok) {
                        throw new Error('Erro na requisição HTTP para deletar animal: ' + deleteAnimalResponse.status);
                    }

                    // Envia a solicitação de DELETE para o formulário
                    const deleteFormResponse = await fetch(apiUrl1 + '/' + formId, {
                        method: 'DELETE',
                    });
                    if (!deleteFormResponse.ok) {
                        throw new Error('Erro na requisição HTTP para deletar formulário: ' + deleteFormResponse.status);
                    }
                    if (deleteFormResponse.ok && deleteAnimalResponse.ok) {
                        location.reload();
                    }

                } catch (error) {
                    console.error('Erro:', error);
                }
            });
        });





    }
    else {
        strHtml += `
            <div class="Nreque">
              <h1>Não há requerimentos!!</h1>
            </div>
        `;
        strHtml += '</div>';
        tela.innerHTML = strHtml;
    }

}

imprimeRequerimentos();

function imprimeformulario(contato) {
    document.getElementById('requerimentosDisplay').style.display = 'block';
    let req = document.getElementById('requerimentosDisplay');
    let formHtml = '';

    // Adiciona botão de fechar
    formHtml += '<button id="closeButton">Fechar</button>';
    formHtml += '<div>';

    // Cria um card para o contato selecionado
    formHtml += `
        <div id="requerimento1">
            <div class="requerimento2">
                <div class="requerimento1-text">
                <h4 class="req-animal">Formulário da pessoa que está interessada em adotar: </h4>
                    <ul>
                        <li class="req">Nome: <p class="preq">${contato.nome}</p></li>
                        <li class="req">Sexo: <p class="preq">${contato.sexo}</p></li>
                        <li class="req">Idade: <p class="preq">${contato.idade}</p></li>
                        <li class="req">Cidade: <p class="preq">${contato.cidade}</p></li>
                        <li class="req">Telefone: <p class="preq">${contato.telefone}</p></li>
                        <li class="req">E-mail: <p class="preq">${contato.email}</p></li>
                        <li class="req">Mora em casa ou apartamento?  <p class="preq">${contato.mora}</p></li>
                        <li class="req">Todos da sua casa estão cientes da adoção? <p class="preq">${contato.cientes}</p></li>
                        <li class="req">Em caso de morar em apartamento, é liberado ter animais? <p class="preq">${contato.apliberado}</p></li>
                        <li class="req">Você já teve algum animal? <p class="preq">${contato.teveanimal}</p></li>
                        <li class="req">Aonde o seu animal irá ficar quando você viajar? <p class="preq">${contato.aondefica}</p></li>
                        <li class="req">O animal ficará muito tempo sozinho? <p class="preq">${contato.animalsozinho}</p></li>
                        <li class="req">Você permite a ONG ir até a sua casa para verificar se as respostas são verdadeiras? <p class="preq">${contato.permisao}</p></li>
                    </ul>
                </div>
            </div>
        </div>
    `;
    formHtml += '</div>';

    req.innerHTML = formHtml;

    // Adiciona evento de clique ao botão de fechar
    document.getElementById('closeButton').addEventListener('click', function() {
        document.getElementById('requerimentosDisplay').style.display = 'none';
    });
}

//Ana Clara

/*
Tela de Cadastro
*/

/*
Funcao para criar objeto json
*/
async function AC_botaoCadastrarOnClick() {
    //    obter dados preenchidos
    const cpnjInputValue = document.getElementById("cnpj-input").value;
    const enderecoInputValue = document.getElementById("endereco-input").value;
    const telefoneInputValue = document.getElementById("telefone-input").value;
    const emailInputValue = document.getElementById("email-input").value;
    const nomeInputValue = document.getElementById("nome-input").value;
    const senhaInputValue = document.getElementById("senha-input").value;
    const confirmarSenhaInputValue = document.getElementById("confirmar-senha-input").value;
    const logoInputValue = document.getElementById("input-logo").value;
    const id = 0;
    //    confirmar senha
    if (senhaInputValue != confirmarSenhaInputValue) {
        document.getElementById("confirmar-senha-msg-erro").style.display = "block";
    } else {
        document.getElementById("confirmar-senha-msg-erro").style.display = "none";
        //    criar objeto JSON
        const objetoResultado = {
            id: "0",
            nome: nomeInputValue,
            cnpj: cpnjInputValue,
            endereco: enderecoInputValue,
            telefone: telefoneInputValue,
            email: emailInputValue,
            senha: senhaInputValue,
            logo: logoInputValue
        }
        console.log(objetoResultado);        //    mostra no console o objeto criado 
        AC_updateONG(id, objetoResultado);     //    executa a funcao para lancar para o Json Server
    }
} // end AC_botaoCadastrarOnClick ( )

/*
Lancar objeto JSON para o Json Server
*/
async function AC_updateONG(id, objetoResultado) {
    //  tentar fazer a chamada
    try {
        //  definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrl5}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(objetoResultado),
        });
        //  mostrar resultado
        //alert("Cadatro efetuado com sucesso!");
    }
    //  chamada de erro
    catch (error) {
        console.error("Error:", error);
        //alert("Erro ao efetuar cadastro (JSON Server indisponível).");
    }
} // end updateONG ( )

/*
Sobre a ONG
*/

/*
Ler Dados do JsonServer
*/
async function AC_obterDados() {
    //  definir dados locais
    let dados = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(apiUrl5);
        dados = await response.json();
        //  mostrar resultado
        console.log("Success:", dados);
        console.log("Sucesso ao ler dados!");
    }
    catch (error) {
        console.error("Error:", error);
        console.log("Erro ao ler dados (JSON Server indisponível).");
    }
    //  retornar
    return (dados);
} // end AC_obterDados ( )

/*
Função que lê os Dados do JSON e chama as funções dos elementos
*/
async function ACmostrarDados() {
    //  definir dados locais
    let objDados = {};
    //  chamar funcao para ler dados do JSON Server
    objDados = await AC_obterDados();
    console.log("OBJ:", objDados);

    //testar se existe objeto e executar funcoes
    if (objDados) {
        AC_mostrarCNPJ(objDados);
        AC_mostrarNome(objDados);
        AC_mostrarLogo(objDados);
        AC_mostrarEmail(objDados);
        AC_mostrarEndereco(objDados);
        AC_mostrarHistoria(objDados);
        AC_mostrarTelefone(objDados);
    }

    //retorne
    return objDados;
}// end mostrarDados  ( )

/*
Função de mostrar o CNPJ
*/
async function AC_mostrarCNPJ(dados) {
    console.log("dados no CNPJ:", dados);
    let cnpjHtml = '';
    // Obtém o elemento <span> pelo id
    var elementoCNPJ = document.getElementById("CNPJONG");
    cnpjHtml += `${dados[0].cnpj}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementoCNPJ.innerHTML = cnpjHtml;

}//end mostrarCNPJ

/*
Função que mostra a historia
*/
async function AC_mostrarHistoria(dados) {
    console.log("dados na historia:", dados);
    let HistHtml = '';
    // Obtém o elemento <span> pelo id
    var elementohistoria = document.getElementById("historiaONG");
    HistHtml += `${dados[0].historia}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementohistoria.innerHTML = HistHtml;

}//end mostrarHistoria

/*
Função de mostrar o Nome
*/
async function AC_mostrarNome(dados) {
    console.log("dados no Nome:", dados);
    let NomeHtml = '';
    // Obtém o elemento <span> pelo id
    var elementoNome = document.getElementById("nomeONG");
    NomeHtml += `${dados[0].nome}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementoNome.innerHTML = NomeHtml;

}//end mostrarNome

/*
Função de mostrar o endereco
*/
async function AC_mostrarEndereco(dados) {
    console.log("dados no Endereco:", dados);
    let EnderecoHtml = '';
    // Obtém o elemento <span> pelo id
    var elementoEndereco = document.getElementById("enderecoONG");
    EnderecoHtml += `${dados[0].endereco}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementoEndereco.innerHTML = EnderecoHtml;

}//end mostrarEndereco

/*
Função de mostra o Email
*/
async function AC_mostrarEmail(dados) {
    console.log("dados no Email:", dados);
    let EmailHtml = '';
    // Obtém o elemento <span> pelo id
    var elementoEmail = document.getElementById("emailONG");
    EmailHtml += `${dados[0].email}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementoEmail.innerHTML = EmailHtml;

}//end mostrarEmail

/*
Função de mostra o Telefone
*/
async function AC_mostrarTelefone(dados) {
    console.log("dados no Telefone:", dados);
    let TelefoneHtml = '';
    // Obtém o elemento <span> pelo id
    var elementotelefone = document.getElementById("telefoneONG");
    TelefoneHtml += `${dados[0].telefone}`;

    // Define o conteúdo do elemento <span> com o resultado da função obterNome
    elementotelefone.innerHTML = TelefoneHtml;

}//end mostrarTelefone

/*
Função de mostra a Logo
*/
async function AC_mostrarLogo(dados) {
    console.log("dados na logo:", dados);
    let LogoHtml = dados[0].logo;
    // Obtém o elemento <span> pelo id
    var elementologo = document.getElementById("imagem");
    // Define o atributo src do elemento <img> com o resultado da função obterImagem
    var imagemSrc = LogoHtml
    if (imagemSrc !== "Imagem não encontrada" && imagemSrc !== "Erro ao converter JSON") {
        elementologo.src = imagemSrc;
    } else {
        // Caso a imagem não seja encontrada ou haja erro, remove a imagem do display
        elementologo.style.display = "none";
    }

}//end mostrarLogo


//Henrique 
//const apiUrl6 = 'https://17888d69-e5c6-41a4-a17c-98ca11856607-00-f4ikhqwcowcp.janeway.replit.dev/users';

// Exibe mensagem em um elemento de ID msg
function HG_displayMessage(message) {
    document.getElementById('msg').innerHTML = '<div class="alert alert-warning">' + message + '</div>';
}

async function HG_alterUser(user) {
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrl6}/${user.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        });
        // Mostrar resultado
        HG_displayMessage("Sucesso ao editar usuário!");
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
        HG_displayMessage("Erro ao editar usuário (JSON Server indisponível).");
    }
} // end HG_alterUser()

async function HG_deleteUser(id) {
    // Tentar fazer a chamada
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrl6}/${id}`, {
            method: 'DELETE',
        });
        // Mostrar resultado
        HG_displayMessage("Sucesso ao excluir usuário!");
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
        HG_displayMessage("Erro ao excluir usuário (JSON Server indisponível).");
    }
} // end HG_deleteUser()

async function HG_readUser(id) {
    console.log('entrou');
    let perfil = {}; console.log('id = ' + id);
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrl6}/${id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        perfil = await response.json();
        // Mostrar resultado
        //console.log("Success:", perfil);
    } catch (error) {
        console.error("Error:", error);
        //HG_displayMessage("Erro ao ler perfil");
    }
    return perfil;
} // end HG_readUser()

// ------------------------------------------- LUCAS CARNEIRO --------------------------------------------------------

function LCdisplayMessage(mensagem) {
    msg = document.getElementById('LCmsg');
    msg.innerHTML = '<div class="alert alert-warning">' + mensagem + '</div>';
} // end displayMessage ( )

async function LCreadComments(animalId) {
    //  definir dados locais
    let comments = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(`${LCapiUrl}comments?animalid=${animalId}`);
        comments = await response.json();
        //  mostrar resultado
        //console.log("Success:", comments);
        //  displayMessage("Sucesso ao ler comentario!");
    }
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler comentario (JSON Server indisponível).");
    }
    //  retornar
    return (comments);
} // end readComments ( )

async function LCreadComment(commentId) {
    //  definir dados locais
    let comment = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(`${LCapiUrl}comments/${commentId}`);
        comment = await response.json();
        //  mostrar resultado
        //console.log("Success:", comment);
        //  displayMessage("Sucesso ao ler comentario!");
    }
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler comentario (JSON Server indisponível).");
    }
    //  retornar
    return (comment);
} // end readComments ( )

async function LCcreateComment(comment) {
    //  tentar fazer a chamada
    try {
        //  definir a chamada HTTP do JSON Server
        const response = await fetch(`${LCapiUrl}comments`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(comment),
        });
        //  mostrar resultado
        LCdisplayMessage("Sucesso ao criar comentario!");
        //  atualizar a pagina
        LCmostrarComentarios();
    }
    //  chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao criar comentario (JSON Server indisponível).");
    }
} // end createComment ( )

async function LCupdateComment(id, comentario) {
    //  tentar fazer a chamada
    try {
        //  definir a chamada HTTP do JSON Server
        const response = await fetch(`${LCapiUrl}comments/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(comentario),
        });
        //  mostrar resultado
        LCdisplayMessage("Sucesso ao editar comentario!");
    }
    //  chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao editar comentario (JSON Server indisponível).");
    }
} // end updateComment ( )

async function LCdeleteComment(id) {
    //  tentar fazer a chamada
    try {
        //  definir a chamada HTTP do JSON Server
        const response = await fetch(`${LCapiUrl}comments/${id}`, {
            method: 'DELETE',
        });
        //  mostrar resultado
        LCdisplayMessage("Sucesso ao excluir comentario!");
    }
    //  chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao excluir comentario (JSON Server indisponível).");
    }
} // end deleteComment ( )

/*
    Manipular a obtenção do usuário
*/

function LCgetUser() {
    //  definir dados locais
    let strUser = localStorage.getItem('db');
    let objUser = {};
    //  testar leitura
    if (strUser) {
        objUser = JSON.parse(strUser);
    }
    //  retornar
    return (objUser);
} // end getUser ( )

/*
    Manipular a exibição de dados
*/

async function LCmostrarComentarios() {
    //  definir dados locais
    const urlParams = new URLSearchParams(window.location.search);
    let strAnimalId = urlParams.get('id');
    let animalId = parseInt(strAnimalId);
    let objComentarios = {}
    let strHTML = ''
    let commentsShowDiv = document.querySelector('#LCcommentsShowDiv');
    //  chamar funcao para ler dados do JSON Server
    objComentarios = await LCreadComments(animalId);
    //  testar se há comentarios
    if (Object.keys(objComentarios).length === 0) {
        strHTML += `<div class=Lcomment>
                      <p class="LCcommentContent">Ainda não há comentários publicados. Seja o primeiro a comentar!</p>
                    </div>`
        document.querySelector('#LCcommentsContainer').style = 'margin-bottom: 255px';
    }
    else {
        //  repetir para cada elemento dos dados
        for (let x = 0; x < objComentarios.length; x++) {
            //  definir dados locais
            let user = await HG_readUser(objComentarios[x].userid);
            //  testar o usuario
            if (Object.keys(user).length > 0) {
                //  definir dados locais
                let username = user.nome;
                let content = objComentarios[x].content;
                let id = objComentarios[x].id;
                //  adicionar os valores à formatação de string a ser inserida
                strHTML += `<div class="LCcomment" id=${id}>
                              <div class="LCcontentHolder">
                                  <p class="LCcommentP"><span class="LCcommentUsername">${username}</span>:<span class="LCcommentContent">${content}</span></p>
                              </div>
                              <div class="LCdropdown">
                                  <button class="LCbtnDropdown" id="${id}">
                                      <svg xmlns="http://www.w3.org/2000/svg" wclassth="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16"><path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/></svg>
                                  </button>
                                  <div class="LCdropdownContent">
                                      <button class="LCbtnHidden LCbtnEditar">Editar</button>
                                      <button class="LCbtnHidden LCbtnExcluir">Excluir</button>
                                  </div>
                              </div>
                          </div>`
            }
        }
    }
    //  inserir string na div dos comentários
    commentsShowDiv.innerHTML = strHTML;
    //  chamar funcao para lidar como botao Dropdown
    LChandleDropdownButton();
    /*
        Impedir a atualização da página com cada envio do formulário
    */
    document.getElementById('LCform').addEventListener('submit', (evento) => {
        evento.preventDefault();
        LClerInputComentario();
    })
} // end mostrarComentarios  ( )

/*
    Manipular a leitura do input e a criação de comentário
*/

async function LClerInputComentario() {
    //  definir dados locais
    const urlParams = new URLSearchParams(window.location.search);
    let strAnimalId = urlParams.get('id');
    let animalId = parseInt(strAnimalId);
    let strContent = '';
    let novoComentario = {};
    let objUser = LCgetUser();
    let login = LCverificarLogin();
    //  ler um comentario novo
    strContent = document.getElementById('LCcampoComment').value;
    document.getElementById('LCcampoComment').value = ''
    //  testar se há algo a ser publicado
    if (strContent) {
        // testar se esta logado
        if (!login) {
            window.location.href = "../../modules/login/login.html";
        }
        else {
            //  criar objeto novoComentario
            novoComentario = {
                userid: objUser.id,
                animalid: animalId,
                id: LCrandomNumber(),
                content: strContent
            }; console.log(novoComentario);
            //  salvar dados no JSON Server
            LCcreateComment(novoComentario);
            //  atualizar os dados na tela
            LCmostrarComentarios();
        }
    }
} // end lerInput ( )

/*
    Manipular a edição de comentário
*/

async function LCeditarComentario(e) {
    //  definir dados
    let id = e.currentTarget.closest('.LCcomment').id;
    let contentHolder = document.querySelector(`div.LCcomment[id="${id}"] .LCcontentHolder`);
    let previousComment = await LCreadComment(id);
    let user = await HG_readUser(previousComment.userid);
    let strHTML = '';
    //  testar se o usuario pode editar
    if (user.id != LCgetUser().id) {
        LCdisplayMessage('Não é possível editar o comentário de outro usuário!');
    }
    else {
        //  adicionar caixa de texto para edicao
        strHTML += `<div class="LCcommentEdit" id=${id}>
                      <div style="display: inline-block;">
                          <p class="LCcommentP"><span class="LCcommentUsername">${user.nome}</span>:</p>
                      </div>
                      <div>
                          <div class="LCformEdit">
                              <input type="text" placeholder="Digite o seu novo comentário..." name="comment" value="${previousComment.content}" class="LCcampoUpdateComment">
                              <button type="submit" class="LCbtnEdit">OK</button>
                              <button type="submit" class="LCbtnCancelEdit">Cancelar</button>
                          </div>   
                      </div>
                  </div>`;
        //  mostrar caixa de texto na tela
        contentHolder.innerHTML = strHTML;
        //  controlar o botao de confirmacao
        let btnEdit = document.querySelector('.LCbtnEdit');
        btnEdit.addEventListener('click', async () => {
            //  indentificar o campo input
            let inputEdit = document.querySelector('.LCcampoUpdateComment');
            //  resgatar o conteúdo digitado
            let strContent = inputEdit.value;
            //  esvaziar o input
            inputEdit.value = '';
            //  editar o objeto
            previousComment.content = strContent;
            //  editar o JSON Server
            await LCupdateComment(id, previousComment);
            //  mostrar os comentarios novamente
            LCmostrarComentarios();
        });
        //  controlar o botao de cancelamento
        let btnCancelEdit = document.querySelector('.LCbtnCancelEdit');
        btnCancelEdit.addEventListener('click', () => {
            //  recarregar a pagina
            LCmostrarComentarios();
        });
    }
} // end editarComentario ( )

/*
    Manipular a exclusão de comentário
*/

async function LCexcluirComentarios(e) {
    //  definir dados
    let id = e.currentTarget.closest('.LCcomment').id;
    let comment = await LCreadComment(id);
    let user = LCgetUser();
    if (comment.userid != user.id) {
        LCdisplayMessage('Não é possível excluir o comentário de outro usuário!');
    }
    else {
        //  remover comentario do JSON Server
        await LCdeleteComment(id);
        //  recarregar a pagina
        LCmostrarComentarios();
    }
}

function LCrandomNumber() {
    //  gerar um numero de 1 a 99;
    let random = Math.floor(Math.random() * 1000);
    //  retornar
    return random;
} // end randomNumber ( )

/*
    Funcao para controlar o evento de mostrar cada dropdownButton
*/

function LChandleDropdownButton() {
    //  definir dados locais
    let btnsDropdown = document.getElementsByClassName('LCbtnDropdown');
    // repetir para cada botão
    for (let x = 0; x < btnsDropdown.length; x++) {
        // ação individual para cada botão
        btnsDropdown[x].addEventListener('click', (e) => {
            LCdropdownButton(e.currentTarget);
        })
    }
} // end handleDropdownButton ( )

/*
    Funcao para controlar o Dropdown Button
*/

function LCdropdownButton(individualDropdownButton) {
    // definir dados locais
    let id = individualDropdownButton.id;
    let container = document.querySelector(`div.LCcomment[id="${id}"] .LCdropdownContent`);
    let btnEditar = document.querySelector(`div.LCcomment[id="${id}"] .LCbtnEditar`);
    let btnExcluir = document.querySelector(`div.LCcomment[id="${id}"] .LCbtnExcluir`);
    // testar se os botões estão escondidos
    if (!container.classList.contains('show')) {
        // mostrar
        container.classList.add('show');
        // criar eventos individuais Editar e Excluir
        btnEditar.addEventListener('click', (e) => {
            LCeditarComentario(e);
            container.classList.remove('show');
        });
        btnExcluir.addEventListener('click', (e) => {
            LCexcluirComentarios(e);
            container.classList.remove('show');
        });
    }
    else {
        // esconder
        container.classList.remove('show');
    }
} // end dropdownButton ( )

// ------------------------ login ----------------------



/*
  Controlar o login do usuario
*/

function LCpaginaInicial() {
    window.location.href = "../../index.html";
} // end LCpaginaInicial ( )

function LCpaginaInicialONG() {
    window.location.href = "../../modules/telaInicialONG/telainicialONG.html";
} // end LCpaginaInicialONG ( )

function LCcriarUsuarioLS(user) {
    localStorage.setItem('db', JSON.stringify(user));
} // end LCcriarUsuarioLS ( )

function LCexcluirUsuarioLS() {
    localStorage.removeItem('db');
} // end LCexcluirUsuarioLS ( )

function LCverificarLogin() {
    // definir dados locais
    let user = LCgetUser();
    let controle = false;
    // testar se há usuário logado
    if (!(Object.keys(user).length > 0))
        window.location.href = "../../modules/login/login.html";
    else
        controle = true;
    // retornar
    return (controle);
} // end LCverificarLogin( )


function LCverificarPerfil() {
    //  testar login
    if (LCverificarLogin()) {
        console.log('logado');
    }
} // end LCverificarPerfil ( )

async function readUsers() {
    //  definir dados locais
    let users = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(apiUrl6);
        users = await response.json();
        //  mostrar resultado
        console.log("Success:", users);
        //  displayMessage("Sucesso ao ler usuarios!");
    }
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler usuarios (JSON Server indisponível).");
    }
    //  retornar
    return (users);
} // end readUsers ( )

async function checkUser() {
    //  definir dados locais
    let inputEmailValue = document.querySelector('#inputEmail').value;
    let inputSenhaValue = document.querySelector('#inputSenha').value;
    //  testar se há algo digitado
    if (inputEmailValue != '' || inputSenhaValue != '') {
        //  ler dados do JSON Server
        let users = await readUsers();
        //  ler usuario da ong
        let objONG = await HG_readUser(0);
        //  testar se e' ong
        if (inputEmailValue === objONG.email && inputSenhaValue === objONG.senha) {
            LCpaginaInicialONG();
        }
        else {
            //  filtrar os usuarios de acordo com os valores digitados
            let userFilter = users.find((e) => e.email === inputEmailValue && e.senha === inputSenhaValue);
            //  testar o resultado
            if (!userFilter) {
                clearLogin();
                LCdisplayMessage('ERRO: Email ou senha incorretos.');
            }
            else {
                LCcriarUsuarioLS(userFilter);
                LCdisplayMessage('Usuário válido.');
                LCpaginaInicial();
            }
        }
    }
} // end checkUser ()

function clearLogin() {
    document.querySelector('#inputEmail').value = '';
    document.querySelector('#inputSenha').value = '';
}

function startLogin() {
    let btnInsert = document.querySelector('#btnInsert');
    btnInsert.addEventListener('click', () => checkUser());

    let btnClear = document.querySelector('#btnClear');
    btnClear.addEventListener('click', () => clearLogin());
}

function LCpreencherONG(objONG) {
    document.querySelector('#imagemONG').src = objONG.logo;
    document.querySelector('#inputImagem').value = objONG.logo;
    document.querySelector('#inputNome').value = objONG.nome;
    document.querySelector('#inputCNPJ').value = objONG.cnpj;
    document.querySelector('#inputEndereco').value = objONG.endereco;
    document.querySelector('#inputTelefone').value = objONG.telefone;
    document.querySelector('#inputEmail').value = objONG.email;
    document.querySelector('#inputSenha').value = objONG.senha;
    document.querySelector('#inputConfirma').value = objONG.senha;
} // end LCpreencherONG ( )

function LCeditarONG() {
    let objONG = {
        id: "0",
        nome: "",
        cnpj: "0",
        endereco: "",
        telefone: "",
        email: "",
        senha: "",
        logo: "",
        historia: "A ONG Aumigos foi fundada em 2015, com o objetivo de ajudar as animais abandonados e desaparecidos."
    }

    objONG.logo = document.querySelector('#inputImagem').value;
    objONG.nome = document.querySelector('#inputNome').value;
    objONG.cnpj = document.querySelector('#inputCNPJ').value;
    objONG.endereco = document.querySelector('#inputEndereco').value;
    objONG.telefone = document.querySelector('#inputTelefone').value;
    objONG.email = document.querySelector('#inputEmail').value;
    objONG.senha = document.querySelector('#inputSenha').value;
    objONG.senha = document.querySelector('#inputConfirma').value;

    console.log(objONG.telefone);

    AC_updateONG(0, objONG)
    location.href = location.href;
} // end LCeditarONG ( )

function LCsairONG() {
    LCpaginaInicial();
} // end LCsairONG ( )

async function startEditarONG() {
    // definir dados locais
    let objONG = await AC_obterDados();
    // testar se ONG foi encontrada
    if (!(Object.keys(objONG[0]).length > 0)) {
        LCdisplayMessage("ERRO: Cadastro não encontrado.");
    }
    else {
        // preencher os dados da ong
        LCpreencherONG(objONG[0]);
        // definir eventos
        document.querySelector('#btnUpdate').addEventListener('click', () => LCeditarONG());
        document.querySelector('#btnSair').addEventListener('click', () => LCsairONG());
    }
} // end startEditarONG ( )

//---------------------------cadastro--------------------------

// Exibe mensagem em um elemento de ID msg
function HG_displayMessage(message) {
    document.getElementById('msg').innerHTML = '<div class="alert alert-warning">' + message + '</div>';
}

async function HG_createUser(user) {
    //  tentar fazer a chamada
    try {
        //  definir a chamada HTTP do JSON Server
        const response = await fetch(apiUrlCad, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });
        console.log(user);
        HG_displayMessage("Sucesso ao criar usuário");
    }
    //  chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao criar usuário");
    }
}

//-----------------------------perfil--------------------------
async function HG_alterUser(user) {
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrlCad}/${user.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        });
        // Mostrar resultado
        LCdisplayMessage("Sucesso ao editar usuário!");
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao editar usuário (JSON Server indisponível).");
    }
} // end HG_alterUser()

async function HG_deleteUser(id) {
    // Tentar fazer a chamada
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrlCad}/${id}`, {
            method: 'DELETE',
        });
        // Mostrar resultado
        LCdisplayMessage("Sucesso ao excluir usuário!");
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao excluir usuário (JSON Server indisponível).");
    }
} // end HG_deleteUser()

async function HG_readUser(id) {
    let perfil = {};
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${apiUrlCad}/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        perfil = await response.json();
        // Mostrar resultado
        //console.log("Success:", perfil);
    } catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler perfil");
    }
    return perfil;
} // end HG_readUser()

//-----------------------------inicial ong---------------------
// Função para exibir mensagens na tela
function exibirMensagem(mensagem) {
    const msg = document.getElementById('msg');
    if (msg) {
        msg.innerHTML = `<div class="alert alert-warning">${mensagem}</div>`;
    }
}

// Função para ler animais do servidor JSON
async function lerAnimais() {
    let animais = [];
    try {
        const resposta = await fetch(apiUrlJP);
        animais = await resposta.json();
        console.log("Animais recebidos:", animais); // Verifica os dados recebidos
        return animais;
    } catch (erro) {
        console.error("Erro ao ler animais:", erro);
        return [];
    }
}

// Função para editar animais no servidor JSON
async function atualizarAnimal(id, animal) {
    try {
        await fetch(`${apiUrlJP}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(animal),
        });
        exibirMensagem("Sucesso ao editar animal!");
    } catch (erro) {
        console.error("Erro:", erro);
        exibirMensagem("Erro ao editar animal (servidor JSON indisponível).");
    }
}

// Função para carregar os animais na grade
async function carregarAnimais() {
    const gradeAnimais = document.getElementById('animal-grid');
    const animais = await lerAnimais();

    // Limpa a grade
    gradeAnimais.innerHTML = '';

    // Adiciona cartões de animais
    animais.forEach(animal => {
        const cartaoAnimal = document.createElement('div');
        cartaoAnimal.className = 'animal-card';
        cartaoAnimal.innerHTML = `
            <div>
                <div>
                    <img src="${animal.imagem[0]}" alt="${animal.nome}" class="animal-img">
                </div>
                <p class="label-box">${animal.nome}</p>
            </div>
            <div class="crud-buttons">
                <button onclick="editarAnimal(${animal.id})">✏️</button>
                <button onclick="deletarAnimal(${animal.id})">🗑️</button>
            </div>
        `;
        gradeAnimais.appendChild(cartaoAnimal);
    });

    // Preenche espaços vazios com o botão '+' e adiciona o evento de redirecionamento
    while (gradeAnimais.children.length < 10) {
        const adicionarCartaoAnimal = document.createElement('div');
        adicionarCartaoAnimal.className = 'add-animal-card';
        adicionarCartaoAnimal.innerHTML = '+';
        adicionarCartaoAnimal.onclick = () => window.location.href = '/../modules/TelaCadastroAnimalONG/teladecadastrodeanimaONG.html';
        gradeAnimais.appendChild(adicionarCartaoAnimal);
    }
}

// Função para mostrar o formulário de edição
function mostrarFormularioEdicao() {
    document.getElementById('animal-catalog').style.display = 'none';
    document.getElementById('edit-animal').style.display = 'block';
}

// Função para editar um animal
async function editarAnimal(id) {
    const animais = await lerAnimais();
    const animal = animais.find(animal => animal.id === id);
    if (animal) {
        document.getElementById('form-title').innerText = 'Editar Animal';
        document.getElementById('animal-id').value = animal.id;
        document.getElementById('sexo').value = animal.sexo;
        document.getElementById('idade').value = animal.idade;
        document.getElementById('raca').value = animal.raca;
        document.getElementById('cidade').value = animal.cidade;
        document.getElementById('historia').value = animal.historia;
        document.getElementById('tags').value = animal.tags.join(', ');

        mostrarFormularioEdicao();
    }
}

// Função para salvar um animal
async function salvarAnimal(event) {
    event.preventDefault();
    const id = document.getElementById('animal-id').value;
    const animal = {
        sexo: document.getElementById('sexo').value,
        idade: document.getElementById('idade').value,
        raca: document.getElementById('raca').value,
        cidade: document.getElementById('cidade').value,
        historia: document.getElementById('historia').value,
        tags: document.getElementById('tags').value.split(',').map(tag => tag.trim())
    };

    if (id) {
        await atualizarAnimal(id, animal);
    } else {
        await criarAnimal(animal);
    }

    cancelarEdicao();
    recarregarGradeAnimais();
}

// Função para criar um novo animal no servidor JSON
async function criarAnimal(animal) {
    try {
        await fetch(apiUrlJP, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(animal),
        });
        exibirMensagem("Sucesso ao criar animal!");
    } catch (erro) {
        console.error("Erro:", erro);
        exibirMensagem("Erro ao criar animal (servidor JSON indisponível).");
    }
}

// Função para deletar um animal
async function deletarAnimal(id) {
    const confirmacao = confirm(`Tem certeza que deseja excluir o animal com ID: ${id}?`);
    if (confirmacao) {
        try {
            await fetch(`${apiUrlJP}/${id}`, {
                method: 'DELETE',
            });
            exibirMensagem("Sucesso ao excluir animal!");
            recarregarGradeAnimais();
        } catch (erro) {
            console.error("Erro:", erro);
            exibirMensagem("Erro ao excluir animal (servidor JSON indisponível).");
        }
    }
}

// Função para cancelar a edição
function cancelarEdicao() {
    document.getElementById('animal-catalog').style.display = 'block';
    document.getElementById('edit-animal').style.display = 'none';
    document.getElementById('edit-form').reset();
    document.getElementById('form-title').innerText = 'Cadastrar Animal';
}

// Função para recarregar a grade de animais
function recarregarGradeAnimais() {
    carregarAnimais();
}

// Adiciona o event listener para o formulário
document.getElementById('edit-form').addEventListener('submit', salvarAnimal);

// Verifica se o documento foi carregado antes de executar as funções
document.addEventListener('DOMContentLoaded', carregarAnimais);

//-----------------------------cadastro de animal - ong---------------------
/// Função para exibir mensagens na tela
function displayMessage(mensagem) {
    const msg = document.getElementById('msg');
    msg.innerHTML = `<div class="alert alert-warning">${mensagem}</div>`;
}

// Função para enviar os dados do formulário para o JSON Server
async function submitForm(event) {
    event.preventDefault();

    const form = document.getElementById('animalForm');
    const formData = new FormData(form);

    const tags = Array.from(document.querySelectorAll('input[name="tags"]:checked')).map(checkbox => checkbox.value);

    const animalData = {
        id: Date.now(), // ID único baseado no timestamp
        sexo: formData.get('sexo'),
        idade: formData.get('idade'),
        raca: formData.get('raca'),
        cidade: formData.get('cidade'),
        historia: formData.get('historia'),
        tags: tags
    };

    try {
        const response = await fetch('https://replit.com/@923928/JSONServer-Patinhas-Felizes#db.json', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(animalData),
        });
        if (response.ok) {
            displayMessage("Animal cadastrado com sucesso!");
        } else {
            displayMessage("Erro ao cadastrar animal.");
        }
    } catch (error) {
        console.error("Error:", error);
        displayMessage("Erro ao conectar com o servidor.");
    }

    console.log(animalData);
}

document.getElementById('animalForm').addEventListener('submit', submitForm);