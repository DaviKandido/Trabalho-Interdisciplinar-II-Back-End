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

//iniciando o processo de integração de JAVA com Js

const obj = {
    id: 1,
    conteudo: "sdadasds",
    id_animal: 12,
    id_pessoa: 21,
};

document.addEventListener('DOMContentLoaded', function(e){
    fetch('/comentario', {
        method: 'POST', // Definindo o método como POST
        headers: {
            'Content-Type': 'application/json' // Definindo o tipo do conteúdo como JSON
        },
        body: JSON.stringify(obj) // Convertendo o objeto para JSON antes de enviar
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Se a resposta for bem-sucedida, retorna o JSON da resposta
        } else {
            throw new Error('Erro na requisição: ' + response.status);
        }
    })
    .then(data => {
        console.log('Sucesso:', data); // Exibe a resposta do servidor
    })
    .catch(error => {
        console.error('Erro:', error); // Trata qualquer erro que ocorra durante a requisição
    });
})
