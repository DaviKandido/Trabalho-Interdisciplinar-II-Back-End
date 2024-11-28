document.addEventListener("DOMContentLoaded", () => {

// ------------------------------------------- João Pedro --------------------------------------------------------


let controleMatchJP = 0;
let controleSlideMatchJP = 0
let objetoGlobalAnimaisJP = {};
let objetoGlobalUsusariosJP = {};

let seletorGeneroJP = document.querySelector('#generoJP');
let seletorPorteJP = document.querySelector('#tamanhoJP');
let seletorEspecieJP = document.querySelector('#especieJP');

let botaoModalMatchJP = document.querySelector('#botaoModalMatchJP');
let botaoModalMatch2JP = document.querySelector('#botaoModalMatch2JP');
let botaoFecharModalMatchJP = document.querySelector('.fechar-modal-MatchJP');
let botaoFecharModalMatch2JP = document.querySelector('.fechar-modal-Match2JP');
let iconeXMatchJP = document.querySelector('#iconeXJP');
let iconeLikeMatchJP = document.querySelector('#iconeLikeJP');
let slideEsquerdaMatchJP = document.querySelector('#slideEsquerdaJP');
let slideDireitaMatchJP = document.querySelector('#slideDireitaJP');
let voltarMatchResultadoJP = document.querySelector('#voltarMatchJP');
let adotarMatchResultadoJP = document.querySelector('#adotarMatchJP');


const botoesModalJP = document.querySelectorAll('.botaoModalJP');
const botoesFecharModalJP = document.querySelectorAll('.fechar-modalJP');


seletorGeneroJP.addEventListener('change', mostrarDadosJP);
seletorPorteJP.addEventListener('change', mostrarDadosJP);
seletorEspecieJP.addEventListener('change', mostrarDadosJP);

botaoModalMatchJP.addEventListener('click', abrirModalMatchJP);
botaoModalMatchJP.addEventListener('click', carregarModalMatchJP);
botaoModalMatch2JP.addEventListener('click', abrirModalMatch2JP);
botaoFecharModalMatchJP.addEventListener('click', fecharModalMatchJP);
botaoFecharModalMatch2JP.addEventListener('click', fecharModalMatch2JP);
iconeXMatchJP.addEventListener('click', proximoAnimalMatchJP);
iconeLikeMatchJP.addEventListener('click', confirmarMatchJP);
slideEsquerdaMatchJP.addEventListener('click', proximaFotoMatchJP);
slideDireitaMatchJP.addEventListener('click', proximaFotoMatchJP);
voltarMatchResultadoJP.addEventListener('click', fecharModalMatch2JP);

botoesModalJP.forEach(button => button.addEventListener('click', abrirModal2JP));
botoesFecharModalJP.forEach(button => button.addEventListener('click', fecharModalJP));


async function readAnimaisJP(apiUrlXJP) {
    let animais = {};
    try {
        const response = await fetch(apiUrlXJP);
        animais = await response.json();
        console.log("Success:", animais);
    } catch (error) {
        console.error("Error:", error);
    }
    return animais;
}

async function readAnimalJP(id) {
    let animal = {};
    try {
        const response = await fetch(`${apiUrlJP}/${id}`);
        animal = await response.json();
        console.log("Success:", animal);
    } catch (error) {
        console.error("Error:", error);
    }
    return animal;
}

async function mostrarDadosJP() {
    let valorGenero = seletorGeneroJP.value;
    let valorPorte = seletorPorteJP.value;
    let valorEspecie = seletorEspecieJP.value;
    let objDados = {};
    let objDadosU = {};
    let strHTML = '';
    let commentsShowDiv = document.querySelector('#cardsJP');

    let objDadosULocal = LCgetUser();
    const apiUrlUsersJP = `https://17888d69-e5c6-41a4-a17c-98ca11856607-00-f4ikhqwcowcp.janeway.replit.dev/users/${objDadosULocal.id}`;
    objDadosU = await readAnimaisJP(apiUrlUsersJP);
    objetoGlobalUsusariosJP = objDadosU;

    objDados = await readAnimaisJP(apiUrlJP);
    objetoGlobalAnimaisJP = objDados;

    for (const element of objDados) {
        let id = element.id;
        let url = element.imagem[0];
        let species = element.especie;
        let name = element.nome;
        let description = element.descricao;
        let size = element.porte;
        let sex = element.sexo;
        let urlSex = element.urlSexo;

        let isGeneroMatch = valorGenero === 'T' || sex === valorGenero;
        let isPorteMatch = valorPorte === 'T' || size === valorPorte;
        let isEspecieMatch = valorEspecie === 'T' || species === valorEspecie;

        if (isGeneroMatch && isPorteMatch && isEspecieMatch) {
            strHTML += `<div class="cardJP ${sex}" id="${id}">
                    <img src="${url}" alt="">
                    <div>
                        <h1>${name}</h1>
                        <img class="sexoJP" id="${sex}" src="${urlSex}" alt="imagem do sexo">
                        <h2>${description}</h2>
                        <button class="botaoModalJP">Saiba Mais</button>
                    </div>
                </div>`;
        }
    }
    commentsShowDiv.innerHTML = strHTML;

    const botoesModalJP = document.querySelectorAll('.botaoModalJP');
    botoesModalJP.forEach(button => button.addEventListener('click', abrirModalJP));
}

async function abrirModalJP(event) {

    let objDados2 = {};
    let strHTML2 = '';
    let commentsShowDiv2 = document.querySelector('#modaisJP');

    const botaoClicado = event.currentTarget;
    const card = botaoClicado.closest('.cardJP');
    if (!card) {
        console.error("O elemento .card mais próximo não foi encontrado.");
        return;
    }
    const cardId = card.id;

    const apiUrlAnimalJP = `https://17888d69-e5c6-41a4-a17c-98ca11856607-00-f4ikhqwcowcp.janeway.replit.dev/animais/${cardId}`;

    objDados2 = await readAnimaisJP(apiUrlAnimalJP);

    let id = objDados2.id;
    let url = objDados2.imagem[0];
    let name = objDados2.nome;
    let sex = objDados2.sexo;
    let age = objDados2.idade;
    let race = objDados2.raca;
    let vaccination = objDados2.vacinado;
    let castrated = objDados2.castrado;
    let history = objDados2.historia;
    let tags = objDados2.etiquetas;
    let size = objDados2.porte;

    if (size == 'P') {
        size = 'Pequeno';
    } else {
        if (size == 'M') {
            size = 'Médio';
        } else {
            size = 'Grande';
        }
    }

    strHTML2 += `
            <div id = "${id}" class="modalJP ${sex}">
            <div id="cabecalhoModalJP">
                <h1>Vamos Adotar?</h1>
                <span class="fechar-modalJP"> × </span>
            </div>
            <div class="corpo_modalJP">
                <section class="flexPerfilJP">
                    <div>
                        <img id="avatarPrincipalJP" src="${url}" alt="">
                    </div>
                    <div class="informacoesJP">
                        <div id="nomeAutorJP">
                            <p>${name}</p>
                        </div>
                        <p><strong>Idade:</strong> ${age}</p>
                        <p><strong>Raça:</strong> ${race}</p>
                        <p><strong>Porte:</strong> ${size}</p>
                        <p><strong>Vacinação:</strong> ${vaccination}</p>
                        <p><strong>Castrado:</strong> ${castrated ? "Sim" : "Não"}</p>
                        <div id="descricaoJP">
                            <h4>História:</h4>
                            <p>${history}</p>
                        </div>
                        <p><strong>Tags: </strong> ${tags.join(', ')} </p>
                        <button class="button-44JP" onclick="adotouJP(event)">Adotar!</button>
                        <button class="button-44JP" onclick="comentariosJP(event)">Comentários!</button>
                    </div>
                </section>
            </div>
            </div>`;

    commentsShowDiv2.innerHTML = strHTML2;
    const botoesFecharModalJP = document.querySelectorAll('.fechar-modalJP');
    botoesFecharModalJP.forEach(button => button.addEventListener('click', fecharModalJP));
    document.querySelector('.modalJP').classList.add('visivelJP');
}

function fecharModalJP() {
    document.querySelector('.modalJP').classList.remove('visivelJP');
}

function abrirModal2JP(event) {

    document.querySelector('.modalJP').classList.add('visivelJP');
    const botaoClicado = event.currentTarget;
    const card = botaoClicado.closest('.cardJP');
    if (!card) {
        console.error("O elemento .card mais próximo não foi encontrado.");
        return;
    }

    const cardId = card.id;
    console.log(cardId)

}

function adotouJP(event) {

    let login = LCverificarLogin();

    if (!login) {
        window.location.href = "../../modules/login/login.html";
    } else {

        const botaoClicado = event.currentTarget;
        const card = botaoClicado.closest('.modalJP');
        if (!card) {
            console.error("O elemento .card mais próximo não foi encontrado.");
            return;
        }
        console.log(card);
        const cardId = card.getAttribute('id');


        window.location.href = `../formulario/formulario.html?id=${cardId}`;

    }
}

function adotou2JP(id) {

    window.location.href = `../formulario/formulario.html?id=${id}`;

}


function adotouIndexJP(event) {

    let login = LCverificarLogin();

    if (!login) {
        window.location.href = "../../modules/login/login.html";
    } else {

        const botaoClicado = event.currentTarget;
        const card = botaoClicado.closest('.modalJP');
        if (!card) {
            console.error("O elemento .card mais próximo não foi encontrado.");
            return;
        }
        console.log(card);
        const cardId = card.getAttribute('id');


        window.location.href = `../modules/formulario/formulario.html?id=${cardId}`;

    }
}

function comentariosJP(event) {
    const botaoClicado = event.currentTarget;
    const card = botaoClicado.closest('.modalJP');
    if (!card) {
        console.error("O elemento .card mais próximo não foi encontrado.");
        return;
    }

    const cardId = card.id;
    window.location.href = `../comentarios/comentarios.html?id=${   (cardId)}`;
}

function comentariosIndexJP(event) {
    const botaoClicado = event.currentTarget;
    const card = botaoClicado.closest('.modalJP');
    if (!card) {
        console.error("O elemento .card mais próximo não foi encontrado.");
        return;
    }

    const cardId = card.id;
    window.location.href = `../modules/comentarios/comentarios.html?id=${encodeURIComponent(cardId)}`;
}


function abrirModalMatchJP() {

    let login = LCverificarLogin();

    if (!login) {
        window.location.href = "../../modules/login/login.html";
    } else {
        document.querySelector('.modalMatchJP').classList.add('visivelJP');
    }
}

function abrirModalMatch2JP(id, imagemPet, sexo, imagemPessoa) {
    document.querySelector('.modalMatch2JP').classList.add('visivelJP');
    document.querySelector('.modalMatch2JP');
    let imgAvatarAnimalMatch = document.querySelector('#avatarPrincipalMatchAnimalJP');
    let imgAvatarUsuarioMatch = document.querySelector('#avatarPrincipalMatchUsuarioJP');
    let modalMatch2 = document.querySelector('.modalMatch2JP');

    imgAvatarAnimalMatch.src = imagemPet;
    imgAvatarUsuarioMatch.src = imagemPessoa;


    if (sexo == 'M') {
        modalMatch2.classList.add('corFundo');
        voltarMatchResultadoJP.classList.add('corFundo');
        adotarMatchResultadoJP.classList.add('corFundo');
    } else {
        modalMatch2.classList.remove('corFundo');
        voltarMatchResultadoJP.classList.remove('corFundo');
        adotarMatchResultadoJP.classList.remove('corFundo');
    }

    let btMatchJP = document.querySelector('#adotarMatchJP');

    btMatchJP.addEventListener('click', function() {
        adotou2JP(id);
    });

}


function fecharModalMatchJP() {
    document.querySelector('.modalMatchJP').classList.remove('visivelJP');
}

function fecharModalMatch2JP() {
    document.querySelector('.modalMatch2JP').classList.remove('visivelJP');
    abrirModalMatchJP();
}

async function carregarModalMatchJP() {

    //let objDados = {};

    let modalMatch = document.querySelector('.modalMatchJP');
    let imgAnimalMatch = document.querySelector('#imgMatchJP');
    let nomeAnimalMatch = document.querySelector('#nomeMatchJP');
    let descricaoAnimalMatch = document.querySelector('#descricaoMatchJP');


    //objDados = await readAnimaisJP(apiUrlJP);

    if (objetoGlobalAnimaisJP[controleMatchJP].sexo == 'M') {
        modalMatch.classList.add('corFundo');
    } else {
        modalMatch.classList.remove('corFundo');
    }

    imgAnimalMatch.src = objetoGlobalAnimaisJP[controleMatchJP].imagem[0];
    nomeAnimalMatch.innerHTML = objetoGlobalAnimaisJP[controleMatchJP].nome;
    descricaoAnimalMatch.innerHTML = objetoGlobalAnimaisJP[controleMatchJP].descricao;

}

async function proximoAnimalMatchJP() {

    controleSlideMatchJP = 0;

    let modalMatch = document.querySelector('.modalMatchJP');

    const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));

    const animateAndUpdateContent = async () => {
        modalMatch.classList.add('slide-out');

        await sleep(200);

        modalMatch.classList.remove('slide-out');
        modalMatch.classList.add('rotate-in');

        modalMatch.offsetHeight;

        await sleep(200);

        modalMatch.classList.remove('rotate-in');

        controleMatchJP++;

        if (controleMatchJP < objetoGlobalAnimaisJP.length) {
            carregarModalMatchJP();
        } else {
            controleMatchJP = 0;
            carregarModalMatchJP();
        }
    }

    animateAndUpdateContent();

    //let objDadosA = {};

    //objDadosA = await readAnimaisJP(apiUrlJP);

}

async function proximaFotoMatchJP(event) {

    let botaoClicado = event.currentTarget;

    //let objDadosA = {};

    //objDadosA = await readAnimaisJP(apiUrlJP);

    if (objetoGlobalAnimaisJP[controleMatchJP].imagem) {
        if (botaoClicado == slideEsquerdaMatchJP) {
            controleSlideMatchJP--;
            if (controleSlideMatchJP < 0) {
                controleSlideMatchJP = objetoGlobalAnimaisJP[controleMatchJP].imagem.length - 1;
            }
        } else {
            if (botaoClicado == slideDireitaMatchJP) {
                controleSlideMatchJP++;
                if (controleSlideMatchJP >= objetoGlobalAnimaisJP[controleMatchJP].imagem.length) {
                    controleSlideMatchJP = 0;
                }
            }
        }

        let imgAnimalMatch = document.querySelector('#imgMatchJP');
        imgAnimalMatch.src = objetoGlobalAnimaisJP[controleMatchJP].imagem[controleSlideMatchJP];
    }

}

async function confirmarMatchJP() {

    //let objDadosA = {};
    let x = 0;
    let contadorEtiquetasMatch = 0;

    //objDadosA = await readAnimaisJP(apiUrlJP);

    while (x < objetoGlobalUsusariosJP.tags.length) {

        for (let y = 0; y < objetoGlobalAnimaisJP[controleMatchJP].etiquetas.length; y++) {
            if (objetoGlobalAnimaisJP[controleMatchJP].etiquetas[y] == objetoGlobalUsusariosJP.tags[x]) {
                contadorEtiquetasMatch++;
            }
        }
        x++;
    }

    if (contadorEtiquetasMatch >= 3) {
        fecharModalMatchJP();
        abrirModalMatch2JP(objetoGlobalAnimaisJP[controleMatchJP].id, objetoGlobalAnimaisJP[controleMatchJP].imagem[0], objetoGlobalAnimaisJP[controleMatchJP].sexo, objetoGlobalUsusariosJP.imagem);
    } else {
        proximoAnimalMatchJP();
    }

}



async function mostrarDadosIndexJP() {
    let objDados = {};
    let strHTML = '';
    let commentsShowDiv = document.querySelector('#containerCardsJP');

    objDados = await readAnimaisJP(apiUrlJP);

    for (let x = 0; x < 8; x++) {
        let id = objDados[x].id;
        let url = objDados[x].imagem[0];
        let name = objDados[x].nome;
        let description = objDados[x].descricao;

        strHTML += `<div class="col-md-3 d-flex" id="card-${id}">
                      <div class="card w-100">
                        <img src="${url}" class="card-img-top" alt="img-animal">
                        <div class="card-body">
                          <h5 class="card-title">${name}</h5>
                          <p class="card-text">${description}</p>
                          <button class="btn btn-primary botaoModalJP" data-id="${id}">Saiba Mais</button>
                        </div>
                      </div>
                    </div>`;
    }

    commentsShowDiv.innerHTML = strHTML;

    // Adiciona os event listeners aos botões "Saiba Mais"
    document.querySelectorAll('.botaoModalJP').forEach(button => {
        button.addEventListener('click', abrirModalIndexJP);
    });
}


async function abrirModalIndexJP(event) {
    let objDados2 = {};
    let strHTML2 = '';
    let commentsShowDiv2 = document.querySelector('#modalIndexJP');

    const botaoClicado = event.currentTarget;
    const cardId = botaoClicado.getAttribute('data-id');

    const apiUrlAnimalJP = `https://17888d69-e5c6-41a4-a17c-98ca11856607-00-f4ikhqwcowcp.janeway.replit.dev/animais/${cardId}`;

    objDados2 = await readAnimaisJP(apiUrlAnimalJP);

    let id = objDados2.id;
    let url = objDados2.imagem[0];
    let name = objDados2.nome;
    let sex = objDados2.sexo;
    let age = objDados2.idade;
    let race = objDados2.raca;
    let vaccination = objDados2.vacinado;
    let castrated = objDados2.castrado;
    let history = objDados2.historia;
    let tags = objDados2.etiquetas;
    let size = objDados2.porte;

    if (size == 'P') {
        size = 'Pequeno';
    } else if (size == 'M') {
        size = 'Médio';
    } else {
        size = 'Grande';
    }

    strHTML2 += `
        <div id="${id}" class="modalJP ${sex}">
            <div id="cabecalhoModalJP">
                <h1>Vamos Adotar?</h1>
                <span class="fechar-modalJP">×</span>
            </div>
            <div class="corpo_modalJP">
                <section class="flexPerfilJP">
                    <div>
                        <img id="avatarPrincipalJP" src="${url}" alt="">
                    </div>
                    <div class="informacoesJP">
                        <div id="nomeAutorJP">
                            <p>${name}</p>
                        </div>
                        <p><strong>Idade:</strong> ${age}</p>
                        <p><strong>Raça:</strong> ${race}</p>
                        <p><strong>Porte:</strong> ${size}</p>
                        <p><strong>Vacinação:</strong> ${vaccination}</p>
                        <p><strong>Castrado:</strong> ${castrated ? "Sim" : "Não"}</p>
                        <div id="descricaoJP">
                            <h4>História:</h4>
                            <p>${history}</p>
                        </div>
                        <p><strong>Tags:</strong> ${tags.join(', ')}</p>
                        <button class="button-44JP" onclick="adotouIndexJP(event)">Adotar!</button>
                        <button class="button-44JP" onclick="comentariosIndexJP(event)">Comentários!</button>
                    </div>
                </section>
            </div>
        </div>`;

    commentsShowDiv2.innerHTML = strHTML2;

    // Adiciona o event listener para fechar o modal
    document.querySelector('.fechar-modalJP').addEventListener('click', fecharModalIndexJP);
    document.querySelector('.modalJP').classList.add('visivelJP');
}

function fecharModalIndexJP() {
    document.querySelector('.modalJP').classList.remove('visivelJP');
    document.querySelector('#modaisJP').innerHTML = ''; // Limpa o conteúdo do modal
}

});