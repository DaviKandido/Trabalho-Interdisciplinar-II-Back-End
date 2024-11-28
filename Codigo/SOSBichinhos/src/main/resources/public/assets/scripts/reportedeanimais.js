
//------------ Requerimentos - ONG ---------------------//

const apiUrl_Formulario = "/enviado";
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

//Função para deletar formulário
async function deleteForm(id) {
    try {
        const response = await fetch(`${apiUrl_Formulario}/delete/${id}`, { 
            method: 'DELETE',
        });

        if (response.ok) {
            alert('Formulário excluído com sucesso!');
            return true; 
        } else if (response.status === 404) {
            alert('Formulário não encontrado.');
            return false;
        } else {
            alert('Erro ao excluir formulário.');
            return false; 
        }
    } catch (error) {
        console.error('Erro ao excluir formulário:', error);
        alert('Erro ao excluir formulário.');
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
    let objRequerimentos = await getAllForms();
    console.log(objRequerimentos);

    strHtml += '<div class="row">';
    if (objRequerimentos != null) {
        for (let i = 0; i < objRequerimentos.length; i++) {
            

            strHtml += `
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 Requerimentos" id="card-${i}">
                    <div class="card caixa">
                        <div class="card imagemreq" id="imagem-${i}" style="font-size: 3vw;">
                            <img class="img-animal" src="${objRequerimentos[i].imagem}" alt="img-animal" style="width: 100%;">
                            <div class="card-text">
                                <h5 class="req-animal">${objRequerimentos[i].especie}</h5>
                            </div>
                        </div>
                        <div class="centerB">
                            <div class="button">
                                <button type="button" class="adotou" data-id="${objRequerimentos[i].id_enviado}">Cadastrar</button>
                                <button type="button" class="nao-adotou" data-id="${objRequerimentos[i].id_enviado}">Descartar</button>
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
                <h1>Não há reportes no momento!!</h1>
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
            
            imprimeformulario(requerimento);
        });
      
        const botaoNaoAdotou = document.querySelectorAll('.nao-adotou');
		botaoNaoAdotou.forEach(botao => {
			botao.addEventListener('click', async function() {
				const formularioId = this.getAttribute('data-id');

                alert('Requerimento descartado! ');

				const deletar = await deleteForm(formularioId); // Chama a função de exclusão com o ID do formulário
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
            botao.addEventListener('click', async function() 
            {
                const formularioId = this.getAttribute('data-id');
                
                // PreencherCampos(formularioId);
                window.location.href = `../TelaCadastroAnimalONG/teladecadastrodeanimalONG.html?id_reporte=${formularioId}`;
            
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
async function imprimeformulario(formulario) 
{

    let usuarioInfo = await getUserDetails(formulario.id_pessoa); 
    document.getElementById('requerimentosDisplay').style.display = 'block';
    let req = document.getElementById('requerimentosDisplay');
    let formHtml = '';

    // Adiciona botão de fechar
    formHtml += '<button id="closeButton">Fechar</button>';
    formHtml += '<div>';


    // Exibe o formulário completo
    formHtml += `
        <div id="formulario-detalhado">
            <div class="requerimento2">
                <div class="requerimento1-text">
                    <h4>Formulário completo:</h4>
                    <ul>
 
                        <li>Sexo: <p>${formulario.sexo}</p></li>
                        <li>Idade: <p>${formulario.idade}</p></li>
                        <li>Especie: <p>${formulario.especie}</p></li>
                        <li>Raça: <p>${formulario.raca}</p></li>
                        <li>Porte: <p>${formulario.porte}</p></li>
                        <li>Temperamento: <p>${formulario.temperamento}</p></li>
                        <li>Necessidades Especiais: <p>${formulario.necessidades_especiais}</p></li>
                        <li>Estado de saúde: <p>${formulario.estado_de_saude}</p></li>
                        <li>Características gerais: <p>${formulario.caracteristicas_gerais}</p></li>
                        <li>Localizacao: <p>${formulario.localizacao}</p></li>
                         <li>Indivíduo que achou o animal : <p>${usuarioInfo.nome}</p></li>
                          <li>email: <p>${usuarioInfo.email}</p></li>
                        
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
