document.addEventListener("DOMContentLoaded", () => {

    const apiUrlAnimal = "/animal";

    //--------------------------------FUNÇÕES Data-----------------------------------//
    async function getTodosAnimais() {
        try {
            const res = await fetch(apiUrlAnimal);
            if (!res.ok) {
                throw new Error('Erro na resposta da rede');
            }
            const data = await res.json();
            console.log(data);
            return data; // Retorna os dados
        } catch (error) {
            alert("Animais não encontrados ou inexistentes");
            console.error('Erro:', error);
            return []; // Retorna um array vazio em caso de erro
        }
    }


    //--------------------------------FUNÇÕES Data-----------------------------------//

    // Mostra todos os animais quando acessar a pagina
    async function mostrarDados() {
        let divContemCardsAnimais = document.querySelector('#ContemCardsAnimais');

        // Pega todos os animais
        const animais = await getTodosAnimais(); // Declaração adequada

        // Verifica se há animais para exibir
        if (animais.length > 0) {
            animais.forEach(animal => {
                let altSexo = animal.sexo === 'M' ? 'Masculino' : 'Feminino';

                if(animal.adotado === false){
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
                }
            });
        } else {
            divContemCardsAnimais.innerHTML = '<p>Nenhum animal encontrado.</p>'; // Mensagem se não houver animais
        }
    }

    mostrarDados();


    pesquisarAnimalBtn = document.querySelector('#botaoPesquisar');


    pesquisarAnimalBtn.addEventListener("click", async () => {

        console.log("Pesquisou");
    
        let divContemCardsAnimais = document.querySelector('#ContemCardsAnimais');
    
        const animais = await getTodosAnimais();
    
        let valorGenero = document.querySelector('#generoJP').value;
        let valorPorte = document.querySelector('#tamanhoJP').value;
        let valorEspecie = document.querySelector('#especieJP').value;
    
    
        divContemCardsAnimais.innerHTML = '';

        let encontrado = false;
    
        if (animais.length > 0) {
            animais.forEach(animal => {
                // Condição de pesquisa
                let generoMatch = valorGenero === 'Todos' || animal.sexo === valorGenero;
                let porteMatch = valorPorte === 'Todos' || animal.porte === valorPorte;
                let especieMatch = valorEspecie === 'Todos' || animal.especie === valorEspecie;
    
                // Se todas as condições forem verdadeiras (ou 'Todos' ou correspondência exata)
                if (generoMatch && porteMatch && especieMatch && animal.adotado === false) {
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





});

//ATENÇÃO ESSAS FUNÇÕES DEVEM ESTAR FORA DO EVENTO DOMContentLoaded pois dependem de uma chamada no html
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
                        <p><strong>Raça:</strong> ${animal.raca}</p>
                        <p><strong>Porte:</strong> ${altPorte}</p>
                        <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
                        <p><strong>Castrado:</strong> ${animal.castrado ? "Sim" : "Não"}</p>
                        <div id="descricaoJP">
                            <h4>História:</h4>
                            <p>${animal.historia}</p>
                        </div>
                        <button class="button-44JP" onclick="window.location.href='../../modules/formulario/formulario.html?id=${animal.id_animal}'">Adotar!</button>
                        <button class="button-44JP" onclick="window.location.href='../../modules/comentarios/comentarios.html?id=${animal.id_animal}'">Comentários!</button>
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