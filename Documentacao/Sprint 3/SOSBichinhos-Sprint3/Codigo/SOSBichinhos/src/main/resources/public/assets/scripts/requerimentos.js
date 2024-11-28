//------------ Requerimentos - ONG ---------------------//

const apiUrl_Formulario = "/formulario";
const apiUrl_Animal = "/animal";
const apiUrl_Usuario = "/usuario";
const apiUrl_Endereco = "/endereco";

//Função para buscar todos os formulários
async function getAllForms() {
    try {
        const res = await fetch(apiUrl_Formulario);
        if (!res.ok) {
            throw new Error('Erro na resposta da rede');
        }
        const data = await res.json();
        console.log(data);
        return data; // Retorna os dados
    } catch (error) {
        alert("Formulários não encontrados ou inexistentes");
        console.error('Erro:', error);
        return []; // Retorna um array vazio em caso de erro
    }
}

// Função para buscar formulário específico
async function getForm(id) {
    try {
        const response = await fetch(`${apiUrl_Formulario}/${id}`);
        if (!response.ok) {
            throw new Error("Erro na requisição: " + response.status);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        alert("Formulário não encontrado");
        console.error('Erro:', error);
        return null; 
    }
}

// Função para obter os detalhes do animal com base no ID
async function getAnimalDetails(idAnimal) {
    try {
        const response = await fetch(`${apiUrl_Animal}/${idAnimal}`); 
        if (!response.ok) {
            throw new Error('Erro ao buscar detalhes do animal');
        }
        const animal = await response.json();
        return animal;
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para obter os detalhes do usuario com base no ID
async function getUserDetails(idPessoa) {
    try {
        const response = await fetch(`${apiUrl_Usuario}/${idPessoa}`); 
        if (!response.ok) {
            throw new Error('Erro ao buscar detalhes do usuário');
        }
        const usuario = await response.json();
        return usuario;
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para buscar os detalhes do endereço do usuario
async function getEnderecoDetails(idPessoa) {
    let enderecos = [];
    try {
        const response = await fetch(apiUrl_Endereco); // Endpoint para buscar todos os endereços
        if (!response.ok) {
            throw new Error('Erro ao buscar detalhes do endereço');
        }
        enderecos = await response.json(); 

        // Filtra o endereço pelo ID do usuário
        const enderecoUsuario = enderecos.find(endereco => endereco.id_pessoa === idPessoa);
        
        if (!enderecoUsuario) {
            throw new Error(`Endereço não encontrado para a pessoa com ID: ${idPessoa}`);
        }

        return enderecoUsuario; // Retorna o endereço do usuário específico
    } catch (error) {
        console.error('Erro:', error);
        return null; // Retorna null em caso de erro
    }
}

// Função para obter os detalhes do usuario com base no ID
async function getUserDetails(idPessoa) {
    try {
        const response = await fetch(`${apiUrl_Usuario}/${idPessoa}`); 
        if (!response.ok) {
            throw new Error('Erro ao buscar detalhes do usuário');
        }
        const usuario = await response.json();
        return usuario;
    } catch (error) {
        console.error('Erro:', error);
    }
}

//Função para deletar formulário
async function deleteForm(id) {
    try {
        const response = await fetch(`${apiUrl_Formulario}/delete/${id}`, { 
            method: 'DELETE',
        });

        if (response.ok) {
            alert('Formulário excluído com sucesso!');
        } else if (response.status === 404) {
            alert('Formulário não encontrado.');
        } else {
            alert('Erro ao excluir formulário.');
        }
    } catch (error) {
        console.error('Erro ao excluir formulário:', error);
        alert('Erro ao excluir formulário.');
    }
}

//Função para carregar requerimentos
async function readRequirements() {
    let requirements = [];
    try {
        requirements = await getAllForms(); 
        console.log("Requirements:", requirements);
        return requirements;
    } catch (error) {
        console.error("Error:", error);
    }
}

// Função para exibir os requerimentos (formulários processados)
async function imprimeRequerimentos() {
    let tela = document.getElementById('tela');
    let strHtml = '';
    let objRequerimentos = await readRequirements();
    console.log(objRequerimentos);

    strHtml += '<div class="row">';
    if (objRequerimentos.length > 0) {
        for (let i = 0; i < objRequerimentos.length; i++) {
            const id_animal = objRequerimentos[i].id_animal || objRequerimentos[i].id; // Obtém o ID do animal
            const id_formulario = objRequerimentos[i].id_formulario;
            // Buscar dados do animal, caso o formulário não tenha 
            let animalDetails = objRequerimentos[i].animal 
                ? objRequerimentos[i] 
                : await getAnimalDetails(id_animal);

            strHtml += `
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 Requerimentos" id="card-${i}">
                    <div class="card caixa">
                        <div class="card imagemreq" id="imagem-${i}" style="font-size: 3vw;">
                            <img class="img-animal" src="${animalDetails.imagem}" alt="img-animal" style="width: 100%;">
                            <div class="card-text">
                                <h5 class="req-animal">${animalDetails.nome}</h5>
                            </div>
                        </div>
                        <div class="centerB">
                            <div class="button">
                                <button type="button" class="adotou" data-id="${id_formulario}">Adotou</button>
                                <button type="button" class="nao-adotou" data-id="${id_formulario}">Não Adotou</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }

        strHtml += '</div>';
        tela.innerHTML = strHtml;

        // Configura eventos após a criação dos elementos
        configurarEventos(objRequerimentos);
    } else {
        strHtml += `
            <div class="Nreque">
                <h1>Não há requerimentos!!</h1>
            </div>
        `;
        tela.innerHTML = strHtml;
    }
}

imprimeRequerimentos();

//Função para configurar eventos dos requerimentos
function configurarEventos(requerimentos) {
    requerimentos.forEach((requerimento, i) => {
        document.getElementById(`imagem-${i}`).addEventListener('click', async function() {
            const id_pessoa = requerimento.id_pessoa; // Captura o id_pessoa do requerimento
            imprimeformulario(requerimento, id_pessoa);
        });
      
        const botaoNaoAdotou = document.querySelectorAll('.nao-adotou');
		botaoNaoAdotou.forEach(botao => {
			botao.addEventListener('click', function() {
				const formularioId = this.getAttribute('data-id');

                alert('Requerimento descartado! ');

				const deletar = deleteForm(formularioId);  // Chama a função de exclusão com o ID do formulário
                if (!deletar) {
                    throw new Error('Erro ao deletar o formulário: ' + deleteFormResponse.status);
                }

                // Se a exclusão der certo, recarrega a página
                if (deletar) {
                    location.reload();
                }
			});
		});

        const botaoAdotou = document.querySelectorAll('.adotou');
        botaoAdotou.forEach(botao => {
            botao.addEventListener('click', async function() {
                try {
                    const formId = this.getAttribute('data-id');
                    if (!formId) {
                        throw new Error('ID do formulário não encontrado.');
                    }

                    // Obtém os detalhes do formulário
                    const formResponse = await getForm(formId);
                    if (!formResponse) {
                        throw new Error('Erro ao obter formulário: formulário não encontrado.');
                    }

                    const animalId = formResponse.id_animal;

                    // Obtém detalhes do animal
                    const animaisResponse = await getAnimalDetails(animalId);
                    if (!animaisResponse) {
                        throw new Error('Erro ao obter detalhes do animal.');
                    }

                    alert('Requerimento aceito! ');
                    const adotouBtn = document.querySelector('.adotou');
                    const naoAdotouBtn = document.querySelector('.nao-adotou');
                    if (adotouBtn) adotouBtn.style.display = 'none'; // Oculta o botão "Adotou"
                    if (naoAdotouBtn) naoAdotouBtn.style.display = 'none'; // Oculta o botão "Não Adotou"

                    ////----------------Revisar ------------------//

                    // Atualiza a interface para mostrar que o animal foi adotado
                    const animalElement = document.querySelector(`#animal-${animalId}`);
                    if (animalElement) {
                        animalElement.innerHTML = `<p>Animal adotado com sucesso!</p>`;
                        animalElement.style.color = "green";
                    }

                    // Oculta os botões de ação
                    const formElement = document.querySelector(`#form-${formId}`);
                    if (formElement) {
                        const adotouBtn = formElement.querySelector('.adotou');
                        const naoAdotouBtn = formElement.querySelector('.nao-adotou');
                        if (adotouBtn) adotouBtn.style.display = 'none'; // Oculta o botão "Adotou"
                        if (naoAdotouBtn) naoAdotouBtn.style.display = 'none'; // Oculta o botão "Não Adotou"
                    }
                } catch (error) {
                    console.error('Erro:', error);
                }
            });
        });

    });
}

imprimeRequerimentos();

//--- Funcao para converter boolean em string ---//
function booleanToString(value) {
    return value ? "Sim" : "Não";
}

//--- Funcao para imprimir formulário ---//
async function imprimeformulario(formulario, idUser) {
    document.getElementById('requerimentosDisplay').style.display = 'block';
    let req = document.getElementById('requerimentosDisplay');
    let formHtml = '';

    // Adiciona botão de fechar
    formHtml += '<button id="closeButton">Fechar</button>';
    formHtml += '<div>';

    let usuarioInfo = await getUserDetails(idUser); 
    let endereco = await getEnderecoDetails(idUser);
    console.log(endereco);

    // Exibe o formulário completo
    formHtml += `
        <div id="formulario-detalhado">
            <div class="requerimento2">
                <div class="requerimento1-text">
                    <h4>Formulário completo:</h4>
                    <ul>
                        <li>Nome: <p>${usuarioInfo.nome}</p></li>
                        <li>Sexo: <p>${usuarioInfo.sexo}</p></li>
                        <li>Idade: <p>${usuarioInfo.idade}</p></li>
                        <li>Rua: <p>${endereco.rua}</p></li>
                        <li>Bairro: <p>${endereco.bairro}</p></li>
                        <li>Número: <p>${endereco.numero}</p></li>
                        <li>Cidade: <p>${endereco.cidade}</p></li>
                        <li>Estado: <p>${endereco.estado}</p></li>
                        <li>Telefone: <p>${formulario.telefone}</p></li>
                        <li>E-mail: <p>${usuarioInfo.email}</p></li>
                        <li>Mora em casa ou apartamento? <p>${formulario.moradia}</p></li>
                        <li>Todos da sua casa estão cientes da adoção? <p>${booleanToString(formulario.familia_ciente)}</p></li>
                        <li>Apartamento Liberado para Animais?? <p>${booleanToString(formulario.ap_liberado)}</p></li>
                        <li>Já teve algum animal? <p>${booleanToString(formulario.teve_animal)}</p></li>
                        <li>Onde o animal ficará quando você viajar? <p>${formulario.aondefica}</p></li>
                        <li>O animal ficará muito tempo sozinho? <p>${booleanToString(formulario.animal_sozinho)}</p></li>
                        <li>Permite que a ONG visite sua casa? <p>${booleanToString(formulario.permissao)}</p></li>
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