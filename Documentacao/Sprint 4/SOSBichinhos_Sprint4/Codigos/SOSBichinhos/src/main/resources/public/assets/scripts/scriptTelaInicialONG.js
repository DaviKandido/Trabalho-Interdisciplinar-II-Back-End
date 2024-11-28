const apiUrlJP = "/animal";


async function getTodosAnimais() {
    let users = [];
    try {
        const response = await fetch(apiUrlJP); 
        users = await response.json();
        console.log("Success:", users);
    } catch (error) {
        console.error("Error:", error);
        alert("Erro ao ler animais.");
    }
    return users;
}

async function mostrarDados() {
    let divContemCardsAnimais = document.querySelector('#ContemCardsAnimais');

    // Pega todos os animais
    const animais = await getTodosAnimais(); // Declaração adequada

    // Verifica se há animais para exibir
    if (animais.length > 0) {
        animais.forEach(animal => {
            let altSexo = animal.sexo === 'M' ? 'Masculino' : 'Feminino';

                divContemCardsAnimais.innerHTML += `
                    <div class="cardJP ${altSexo}" id="card${altSexo}">
                        <img src="${animal.imagem}" alt="imagem do ${animal.nome}">
                        <div>
                            <h1>${animal.nome}</h1>
                            <img class="sexoJP" id="${altSexo}" src="../../imgs/icone${altSexo}.png" alt="${altSexo}">
                            <h2>${animal.historia}</h2>
                            <button class="botaoModalJP" onclick="AbrirModal(${animal.id_animal})">Saiba Mais</button>
                        </div>
                    </div>
                `;
        });
    } else {
        divContemCardsAnimais.innerHTML = '<p>Nenhum animal encontrado.</p>'; // Mensagem se não houver animais
    }
}




pesquisarAnimalBtn = document.querySelector('#botaoPesquisar');


pesquisarAnimalBtn.addEventListener("click", async () => {

    console.log("Pesquisou");

    let divContemCardsAnimais = document.querySelector('#ContemCardsAnimais');

    const animais = await getTodosAnimais();

    let valorGenero = document.querySelector('#generoJP').value;
    let valorPorte = document.querySelector('#tamanhoJP').value;
    let valorEspecie = document.querySelector('#especieJP').value;
    let valorAdotado = document.querySelector('#adotadoJP').value;



    divContemCardsAnimais.innerHTML = '';

    let encontrado = false;

    if (animais.length > 0) {
        animais.forEach(animal => {
            // Condição de pesquisa
            let generoMatch = valorGenero === 'Todos' || animal.sexo === valorGenero;
            let porteMatch = valorPorte === 'Todos' || animal.porte === valorPorte;
            let especieMatch = valorEspecie === 'Todos' || animal.especie === valorEspecie;
            let adotadoMatch = valorAdotado === 'Todos' || animal.adotado === (valorAdotado === 'Adotado') ? true : false;

            console.log(valorAdotado);


            // Se todas as condições forem verdadeiras (ou 'Todos' ou correspondência exata)
            if (generoMatch && porteMatch && especieMatch && adotadoMatch) {
                let altSexo = animal.sexo === 'M' ? 'Masculino' : 'Feminino';

                divContemCardsAnimais.innerHTML += `
                    <div class="cardJP ${altSexo}" id="card${altSexo}">
                        <img src="${animal.imagem}" alt="imagem do ${animal.nome}">
                        <div>
                            <h1>${animal.nome}</h1>
                            <img class="sexoJP" id="${altSexo}" src="../../imgs/icone${altSexo}.png" alt="${altSexo}">
                            <h2>${animal.historia}</h2>
                            <button class="botaoModalJP" onclick="AbrirModal(${animal.id_animal})">Saiba Mais</button>
                        </div>
                    </div>
                `;

                encontrado = true;
            }
        });
    }

    console.log(encontrado);

    if(!encontrado){
        divContemCardsAnimais.innerHTML += '<h2>Nenhum animal encontrado.</h2>'
    }
});



const apiUrlAnimal = "/animal";

async function getAnimal(id) {
try {
    const res = await fetch(`${apiUrlAnimal}/${id}`);
    if (!res.ok) {
        throw new Error("Animal não encontrado ou inexistente");
    }
    const data = await res.json();
    return data; // Retorna o objeto do animal
} catch (error) {
    alert("Animal não encontrado ou inexistente");
    console.error('Erro:', error);
}
}


async function updateAnimalAdotado(id_animal){

try{
    const resp = await fetch(`${apiUrlAnimal}/adotado/${id_animal}`, {
        method: 'PUT'
    })

    if(resp.ok){
        alert("Animal foi adotado com sucesso!");
    }
}catch(error){
    console.error('Erro ao atualizar status do animal:', error);
}
}

async function updateAnimalVoltou(id_animal){
try{
    const resp = await fetch(`${apiUrlAnimal}/voltou/${id_animal}`, {
        method: 'PUT'
    })

    if(resp.ok){
       alert("Animal voltou a adoção");
    }
}catch(error){
    console.error('Erro ao atualizar status do animal:', error);
}
}



async function MudarStatusAdocao(id){

const animal = await getAnimal(id);

const status = animal.adotado

if(status){
    await updateAnimalVoltou(animal.id_animal);
    fecharModalJP();
}else{
    await updateAnimalAdotado(animal.id_animal);
    fecharModalJP();
}
}



// Essa função abre o modal
async function AbrirModal(id){

console.log(id);


let divContemModalAnimais = document.querySelector('#ContemMoldaisAnimais');

const animal = await getAnimal(id);

console.log(animal);

let altSexo = animal.sexo === 'M' ? 'Masculino' : 'Feminino';
let altPorte = animal.porte === 'P' ? 'Pequeno' : animal.porte === 'M' ? 'Médio' : 'Grande';

divContemModalAnimais.innerHTML = `
            <div id = "${animal.id_animal}" class="modalJP ${animal.sexo}">
    <div id="cabecalhoModalJP">
        <h1>Vamos Adotar?</h1>
        <span class="fechar-modalJP"> × </span>
    </div>
    <div class="corpo_modalJP">
        <section class="flexPerfilJP">
            <div>
                <img id="avatarPrincipalJP" src="${animal.imagem}" alt="">
            </div>
            <div class="informacoesJP">
                <div id="nomeAutorJP">
                    <p>${animal.nome}</p>
                </div>
                <p><strong>Idade:</strong> ${animal.idade}</p>
                <p><strong>Especie:</strong> ${animal.especie}</p>
                <p><strong>Raça:</strong> ${animal.raca}</p>
                <p><strong>Porte:</strong> ${altPorte}</p>
                <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
                <p><strong>Castrado:</strong> ${animal.castrado ? "Sim" : "Não"}</p>
                <p><strong>Adotado:</strong> <span class="adotado ${animal.adotado ? 'true' : 'false'}">${animal.adotado ? "Sim" : "Não"}</span> </p>
                <div id="descricaoJP">
                    <h4>História:</h4>
                    <p>${animal.historia}</p>
                </div>
                <button class="button-44JP" onclick="window.location.href='../../modules/TelaCadastroAnimalONG/teladecadastrodeanimalONG.html?id_atualiza=${animal.id_animal}'">Atualizar</button>
                <button class="button-44JP" onclick="MudarStatusAdocao(${animal.id_animal})">Mudar status de adoção</button>
            </div>
        </section>
    </div>
    </div>`;

    const botoesFecharModalJP = document.querySelector('.fechar-modalJP');
    botoesFecharModalJP.addEventListener('click', fecharModalJP);
    document.querySelector('.modalJP').classList.add('visivelJP');
}


function fecharModalJP() {

let divContemModalAnimais = document.querySelector('#ContemMoldaisAnimais');

divContemModalAnimais.innerHTML = '';

document.querySelector('.modalJP').classList.remove('visivelJP');
}





// Chamar a função para imprimir os animais ao carregar a página
document.addEventListener('DOMContentLoaded', mostrarDados);