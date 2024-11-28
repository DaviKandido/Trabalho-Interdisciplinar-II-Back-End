
document.addEventListener("DOMContentLoaded", () => {
        
    function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    async function preencherFormularioComAnaliseIA() {
        const imageUrl = getQueryParam("image");
        if (!imageUrl) {
            console.error("URL da imagem não encontrada.");
            return;
        }
    
        const imageElement = document.getElementById("avatarPrincipalJP");
        if (imageElement) {
            imageElement.src = imageUrl;
            imageElement.style.display = "block";
        } else {
            console.error("Elemento não encontrado.");
            return;
        }
    
        try {
            alert("Analisando imagem, Favor aguardar...");
            const response = await analisarImagem(imageUrl);
            
            console.log(response);
            if (!response.candidates || response.candidates.length === 0) {
                console.error("Nenhum candidato encontrado na resposta.");
                return;
            }
    
            // Extraindo o texto JSON da resposta e limpando a formatação do bloco de código
            
            const analiseTexto = response.candidates[0].content.parts[0].text
            .replace(/```json|```/g, '') // Remove todas as ocorrências de ```json e ```
            .trim(); // Remove espaços em branco ao redor

            
            console.log(analiseTexto);
            
            let analise;
            try {
                analise = JSON.parse(analiseTexto);
            } catch (e) {
                console.error("Erro ao analisar o texto JSON:", e);
                return;
            }
            
            console.log(analise);
            await preencherCamposFormulario(analise);
        } catch (error) {
            console.error("Erro ao analisar a imagem:", error);
            alert("Erro ao analisar a imagem" + error);
        }
    }
    
    async function analisarImagem(imageUrl) {
        try {
            const response = await fetch('/enviado_gemini', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ url: imageUrl })
            });
    
            if (!response.ok) {
                throw new Error("Erro ao analisar a imagem");
            }
    
            return await response.json();
        } catch (error) {
            console.error("Erro ao chamar o serviço de análise de imagem:", error);
            throw error;
        }
    }

    async function preencherCamposFormulario(analise) {
        if (analise) {
            if(analise.especie === "Desconhecido"){
                alert("Animal desconhecido, favor fornecer uma imagem valida");
            }else{
                document.querySelector("#especie").value = analise.especie || "";
                document.querySelector("#raca").value = analise.raca || "";
                document.querySelector("#idade").value = analise.idade || "";
                document.querySelector("#sexo").value; 
                //Logica para selecionar Porte do Animal
                let porteValue;
                if (analise.porte.includes("Pequeno")) {
                    porteValue = 'P';
                } else if (analise.porte.includes("Médio")) {
                    porteValue = 'M';
                } else if (analise.porte.includes("Grande")) {
                    porteValue = 'G';
                } else {
                    porteValue = '';
                }

                const porteSelect = document.querySelector("#porte");
                if (porteSelect) {
                    porteSelect.value = porteValue;
                    if (porteSelect.value !== porteValue) {
                        porteSelect.selectedIndex = [...porteSelect.options].findIndex(option => option.value === porteValue);
                    }
                } 

                document.querySelector("#estado_de_saude").value = analise.estado_de_saude || "";
                document.querySelector("#temperamento").value = analise.temperamento || "";
                document.querySelector("#necessidades_especiais").value = analise.necessidades_especiais || "";
                document.querySelector("#caracteristicas_gerais").value = analise.caracteristicas_gerais || "";
                document.querySelector("#localizacao").value;
            }
        } else {
            console.error("Análise não fornecida ou inválida.");
        }
    }

    const idPessoa = JSON.parse(localStorage.getItem('db'));

    if (!idPessoa) {
        console.log("ID não encontrado");
    }

    const SubmeterFormBtn = document.querySelector(".botao_submit");

    SubmeterFormBtn.addEventListener("click", async (event) => {
        event.preventDefault();
        const imagemUrl = getQueryParam("image");
        const inputSexo = document.querySelector("#sexo").value; 
        const inputEspecie = document.querySelector("#especie").value;
        const inputRaca = document.querySelector("#raca").value;
        const inputIdade = document.querySelector("#idade").value; 
        const inputPorte = document.querySelector("#porte").value;
        const inputEstadoSaude = document.querySelector("#estado_de_saude").value;
        const inputTemperamento = document.querySelector("#temperamento").value;
        const inputNecessidadesEspeciais = document.querySelector("#necessidades_especiais").value;
        const inputCaracteristicasGerais = document.querySelector("#caracteristicas_gerais").value;
        const inputLocalizacao = document.querySelector("#localizacao").value;

        let formulario = {
            imagem: imagemUrl,
            sexo: inputSexo,
            especie: inputEspecie,
            raca: inputRaca,
            idade: inputIdade,
            porte: inputPorte,
            temperamento: inputTemperamento,
            necessidades_especiais: inputNecessidadesEspeciais,
            estado_de_saude: inputEstadoSaude,
            caracteristicas_gerais: inputCaracteristicasGerais,
            localizacao: inputLocalizacao,
            id_pessoa: idPessoa.id,
        };
        
        try {
            saveForm(formulario);
            console.log("Formulário enviado com sucesso:", formulario);
            document.getElementById("formularioAdocao").reset();
        } catch (error) {
            console.error("Erro ao enviar o formulário:", error);
        }
    });

    const apiUrl_Enviado = "/enviado";

    // Função para salvar Formulario do animal enviado
	function saveForm(dado) {
        fetch(apiUrl_Enviado, { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dado)
        }).then(response => response.text()) 
          .then(dado => {
            console.log(dado);
            alert("Formulário adicionado com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao submeter formulário.");
          });
    }

    preencherFormularioComAnaliseIA();
});
