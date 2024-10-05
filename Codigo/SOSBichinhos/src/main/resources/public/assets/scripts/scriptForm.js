document.addEventListener("DOMContentLoaded", () => {

    const apiUrl_Formulario = "/formulario";

    // Função para salvar formulário submetido
    function saveForm(dado) {
        fetch(apiUrl_Formulario, { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dado)
        }).then(response => response.json())
          .then(dado => {
            console.log(dado);
            alert("Formulário submetido com sucesso!");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao submeter formulário.");
          });
    }

    // Função para deletar formulários
	function deleteForm(id) {
	    fetch(`${apiUrl_Formulario}/${id}`, {
	        method: 'DELETE',
	    })
	    .then(response => {
	        if (response.ok) {
	            alert(`Formulário ${id} removido com sucesso.`);
	        } else {
	            alert(`Erro ao deletar: ${response.statusText}`);
	        }
	    })
	    .catch(error => {
	        console.error('Erro:', error);
	        alert("Erro ao remover o formulário");
	    });
	}

	function getForm(FunctionCallBack, id) {
	       fetch(`${apiUrl_Formulario}/${id}`)
	         .then((res) => res.json())
	         .then(data => {
	           FunctionCallBack(data);
	           return data;
	         })
	         .catch(error => {
	           alert("Formulario não encontrado");
	           console.error('Erro:', error);
	         });
	     }

	     function getAll(FunctionCallBack) {
	       fetch(`${apiUrl_Formulario}`)
	         .then((res) => res.json())
	         .then(data => {
	           FunctionCallBack(data);
	           return data;
	         })
	         .catch(error => {
	           alert("Formulario não encontrado");
	           console.error('Erro:', error);
	         });
	     }

    // Função para inserir novo formulário
    function insertForm() {
        const inputNome = document.querySelector("#nome").value;
        const inputIdade = document.querySelector("#idade").value;
        const inputSexo = document.querySelector('input[name="sexo"]:checked').value;
        const inputCidade = document.querySelector("#cidade").value;
        const inputCiente = document.querySelector("#ciente").value;
        const inputTeveAnimal = document.querySelector("#teveAnimal").value;
        const inputPermissao = document.querySelector("#permissao").value;
        const inputAnimalSozinho = document.querySelector("#animalSozinho").value;
        const inputAondeFica = document.querySelector("#aondeFica").value;
        const inputMoradia = document.querySelector("#moradia").value;
        const inputTelefone = document.querySelector("#telefone").value;
        const inputEmail = document.querySelector("#email").value;
        const inputNomeAnimal = document.querySelector("#nomeAnimal").value;
        const inputImagemAnimal= document.querySelector("#url_imagem").value;

        let formulario = {
            nome: inputNome,
            idade: inputIdade,
            sexo: inputSexo,
            cidade: inputCidade,
            ciente: inputCiente,
            teveAnimal: inputTeveAnimal,
            permissao: inputPermissao,
            animalSozinho: inputAnimalSozinho,
            aondeFica: inputAondeFica,
            moradia: inputMoradia,
            telefone: inputTelefone,
            email: inputEmail,
            nomeAnimal: inputNomeAnimal,
            imagemAnimal: inputImagemAnimal
        };

		saveForm(formulario).then(() => {
		    // Limpa os campos após a submissão
		 	document.getElementById("formularioAdocao").reset();
		 });
        console.log(formulario);
		
    }
	
    // Eventos - Botões
    const SubmeterFormBtn = document.querySelector(".botao_adocao");

    SubmeterFormBtn.addEventListener("click", function(event) {
        event.preventDefault();
        insertForm();
    });
	
	const botaoListar = document.querySelector(".botaoListar");
	botaoListar.addEventListener("click", function(event) {
	    event.preventDefault(); 
	    getAll(formularios => {
	        Listar(formularios);
	        document.getElementById("myModal").style.display = "block"; 
	    });
	});

	// Função para renderizar os formulários
	function Listar(formularios) {
	    const modalBody = document.getElementById("modal-body");
	    modalBody.innerHTML = `<h1 class="titulo">Formulários</h1>`; 

	    if (formularios.length === 0) {
	        modalBody.innerHTML += `<p>Nenhum formulário encontrado.</p>`;
	        return;
	    }

	    formularios.forEach(formulario => {
	        // Verifica se as propriedades existem
	        const imagem = formulario.imagem_animal || 'default-image.jpg'; 
	        modalBody.innerHTML += `
	        <section class="flexPerfilJP">
	            <div>
	                <img id="avatarPrincipalJP" src="${imagem}" alt="formulario">
	            </div>
	            <div class="informacoesJP">
	                <p>Nome: ${formulario.nome || 'N/A'}</p>
	                <p><strong>ID do formulário:</strong> ${formulario.id || 'N/A'}</p>
	                <p><strong>Cidade:</strong> ${formulario.cidade || 'N/A'}</p>
	                <p><strong>Idade:</strong> ${formulario.idade || 'N/A'}</p>
	                <p><strong>Sexo:</strong> ${formulario.sexo || 'N/A'}</p>
					<p><strong>Nome do Animal:</strong> ${formulario.nome_animal || 'N/A'}</p>
					<button class="botao-detalhar" data-id="${formulario.id}">Detalhar</button>
					<button class="botao-deletar" data-id="${formulario.id}">Deletar</button>
	            </div>
	        </section>`;
	    });
	}
	
	const modalBody = document.getElementById("modal-body");
	    modalBody.addEventListener("click", function(event) {
			if (event.target.classList.contains("botao-detalhar")) {
			    const id = event.target.dataset.id;
			    window.location.href = `detail.html?id=${id}`; // Redireciona para a página de detalhes
			}
			
			if (event.target.classList.contains("botao-deletar")) {
			    const id = event.target.dataset.id;
			    deleteForm(id); // Chama a função de deletar
			}

	});

});
