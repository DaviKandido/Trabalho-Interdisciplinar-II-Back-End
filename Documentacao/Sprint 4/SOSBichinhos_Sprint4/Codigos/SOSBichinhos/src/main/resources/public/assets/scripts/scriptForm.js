document.addEventListener("DOMContentLoaded", () => {
	
	//Mapeando URL das APIs//
	const apiUrl_Formulario = "/formulario";
	const apiUrl_Animal = "/animal";
	const apiUrl_Endereco = "/endereco";

	// Função para salvar Formulário
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
            alert("Formulário adicionado com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao submeter formulário.");
          });
    }

	//Função para deletar Formulario
	async function deleteForm(id) {
		const confirmacao = confirm('Você tem certeza que deseja excluir este formulário?');
		if (confirmacao) {
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
	}

	//--- Buscar dados da pessoa logada e do animal selecionado ---//
	const idPessoa = JSON.parse(localStorage.getItem('db'));
	const params = new URLSearchParams(location.search);
	const idAnimal = params.get('id');

	if (!idPessoa || !idAnimal) {
		console.log("ID não encontrado");
		return;
	}

	// Funcao para buscar os detalhes do endereço da pessoa
	async function getEnderecoDetails() {
		let enderecos = [];
		try {
			const response = await fetch(apiUrl_Endereco); // Endpoint para buscar todos os enderecos
			if (!response.ok) {
				throw new Error('Erro ao buscar detalhes do endereço');
			}
			enderecos = await response.json(); 
			return enderecos;
		} catch (error) {
			console.error('Erro:', error);
		}
	}
	
	//Se idPessoa existe
	if (idPessoa) {
		// Preenche os campos do formulario com os dados do usuário
		document.querySelector("#nome").value = idPessoa.nome;
		document.querySelector("#nome").readOnly = true;

		document.querySelector("#inputIdade").value = idPessoa.idade;
		document.querySelector("#inputIdade").readOnly = true;

		document.querySelector("#email").value = idPessoa.email;
		document.querySelector("#email").readOnly = true;

		document.querySelector("#moradia").value = idPessoa.moradia;
		document.querySelector("#moradia").readOnly = true;

		// Preenche o campo de sexo
		if (idPessoa.sexo === 'F') {
			document.querySelector("#sexo-feminino").checked = true;
		} else if (idPessoa.sexo === 'M') {
			document.querySelector("#sexo-masculino").checked = true;
		}

		document.querySelector("#sexo-feminino").disabled = true;
		document.querySelector("#sexo-masculino").disabled = true;

		getEnderecoDetails().then(enderecos => {
			if (enderecos && Array.isArray(enderecos)) {
				// Filtrar o endereço associado ao idPessoa
				const endereco = enderecos.find(endereco => endereco.id_pessoa == idPessoa.id);
				
				if (endereco) {
					// Preencher os campos do formulário com os dados do endereço
					document.querySelector("#rua").value = endereco.rua || "";
					document.querySelector("#rua").readOnly = true;
		
					document.querySelector("#numero").value = endereco.numero || "";
					document.querySelector("#numero").readOnly = true;
		
					document.querySelector("#bairro").value = endereco.bairro || "";
					document.querySelector("#bairro").readOnly = true;
		
					document.querySelector("#cidade").value = endereco.cidade || "";
					document.querySelector("#cidade").readOnly = true;
		
					document.querySelector("#estado").value = endereco.estado || "";
					document.querySelector("#estado").readOnly = true;
				} else {
					console.log('Endereço não encontrado para essa pessoa.');
				}
			}
		});
	}

	//Se idAnimal existe
	if (idAnimal) {
		//buscar os detalhes do animal referenciado pelo ID
		getAnimalDetails(idAnimal).then(animal => {
			if (animal) {
				const titulo = document.querySelector('.titulo');
				titulo.textContent = `Formulário para Adoção do Animal: ${animal.nome}`;
			}
		});
	}

	// Funcao para obter os detalhes do animal com base no ID
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

	//--- Eventos de Botoes---//

	// Captura o botão de enviar
	const SubmeterFormBtn = document.querySelector(".botao_adocao");

	SubmeterFormBtn.addEventListener("click", async (event) => {
		event.preventDefault(); 

		// Logica de envio do formulario
		const inputAnimalSozinho = document.querySelector("#animalSozinho").value;
		const inputCiente = document.querySelector("#ciente").value;
		const inputPermissao = document.querySelector("#permissao").value; 
		const inputTeveAnimal = document.querySelector("#teveAnimal").value; 
		const inputTelefone = document.querySelector("#telefone").value;
		const inputApLiberado = document.querySelector("#apLiberado").value;

		//Converter as string par boolean
		function convertToBoolean(value) {
			return value.toLowerCase() === "sim";
		}

		let formulario = {
			animal_sozinho: inputAnimalSozinho,
			familia_ciente: convertToBoolean(inputCiente),
			permissao: convertToBoolean(inputPermissao),
			teve_animal: convertToBoolean(inputTeveAnimal), 
			id_animal: idAnimal,
			id_pessoa: idPessoa.id,
			telefone: inputTelefone,
			ap_liberado: convertToBoolean(inputApLiberado)
		};
		
		try {
			saveForm(formulario); //enviar formulario
			console.log("Formulário enviado com sucesso:", formulario);
			document.getElementById("formularioAdocao").reset();
		} catch (error) {
			console.error("Erro ao enviar o formulário:", error);
		}
	});

	//Botao de Listar Formularios do Usuario
	const botaoListar = document.querySelector(".botaoListar");
	botaoListar.addEventListener("click", function(event) {
	    event.preventDefault(); 
	    getAll(formularios => {
	        Listar(formularios);
	        document.getElementById("myModal").style.display = "block"; 
	    });
	});

	//Bucar todos os Formulaios
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

	// Função para abrir o modal
	const openModalAnimaisBtn = document.getElementById("openModalAnimais");
	const openModalFormulariosBtn = document.getElementById("openModalFormularios");
	const modal = document.getElementById("myModal");
	const modalBody = document.getElementById("modal-body");

	// modal de animais adotados
	openModalAnimaisBtn.onclick = async function() {
		modal.style.display = "block"; 
		modalBody.innerHTML = ''; 
		try {
			const response = await fetch(apiUrl_Formulario); 
			const formularios = await response.json();
			ListarAnimais(formularios); 
		} catch (error) {
			console.error('Erro ao carregar formulários:', error);
		}
	};

	// modal de formularios submetidos
	openModalFormulariosBtn.onclick = async function() {
		modal.style.display = "block";
		modalBody.innerHTML = '';
		try {
			const response = await fetch(apiUrl_Formulario); 
			const formularios = await response.json();
			Listar(formularios); 
		} catch (error) {
			console.error('Erro ao carregar formulários:', error);
		}
	};

	//Funcao para Listar Animais adotados do Usuario Logado
	async function ListarAnimais(formularios) {
		modalBody.innerHTML = `<h1 class="titulo">Animais Adotados</h1>`; 
	
		const formulariosDoUsuario = formularios.filter(formulario => formulario.id_pessoa === idPessoa.id);
		if (formulariosDoUsuario.length === 0) {
			modalBody.innerHTML += `<p>Nenhum formulário encontrado.</p>`;
			return;
		}
	
		const promessasAnimais = formulariosDoUsuario.map(async (formulario) => {
			try {
				const animal = await getAnimalDetails(formulario.id_animal);
				if (animal) {
					const imagem = animal.imagem || 'default-image.jpg';
	
					return `
					<div class="flexPerfilJP">
						<div>
							<img id="avatarPrincipalJP" src="${imagem}" alt="animal">
						</div>
						<div class="informacoesJP">
							<p><strong>Nome do Animal:</strong> ${animal.nome || 'N/A'}</p>
							<p><strong>Idade:</strong> ${animal.idade || 'N/A'}</p>
							<p><strong>Sexo:</strong> ${animal.sexo || 'N/A'}</p>
							<p><strong>Raça:</strong> ${animal.raca || 'N/A'}</p>
							<p><strong>Porte:</strong> ${animal.porte || 'N/A'}</p>
							<p><strong>Vacinas:</strong> ${animal.vacinas || 'N/A'}</p>
							<p><strong>Castrado:</strong> ${animal.castrado ? 'Sim' : 'Não'}</p>
						</div>
					</div>`;
				}
			} catch (error) {
				console.error(`Erro ao buscar detalhes do animal com ID ${formulario.id_animal}:`, error);
			}
		});
	
		const animaisHTML = await Promise.all(promessasAnimais);
		modalBody.innerHTML += animaisHTML.join('');
	}

	//Funcao para Listar formularios submetidos pelo usuario logado
	function Listar(formularios) {
		const modalBody = document.getElementById("modal-body");
		modalBody.innerHTML = `<h1 class="titulo">Formulário Submetidos</h1>`; 

		// Filtra os formulários para incluir apenas os do usuário logado
		const formulariosDoUsuario = formularios.filter(formulario => formulario.id_pessoa === idPessoa.id);

		if (formulariosDoUsuario.length === 0) {
			modalBody.innerHTML += `<p>Nenhum formulário encontrado.</p>`;
			return;
		}

		//De boolean para string
		function booleanToString(value) {
			return value ? "Sim" : "Não";
		}
		
		formulariosDoUsuario.forEach((formulario) => {
			// Exibe detalhes de cada formulario no modal
			modalBody.innerHTML += `
			<div class="flexPerfilJP">
				<div class="informacoesJP">
					<p><strong>Animal Sozinho:</strong> ${formulario.animal_sozinho}</p>
					<p><strong>Permissão:</strong> ${booleanToString(formulario.permissao)}</p>
					<p><strong>Família Ciente:</strong> ${booleanToString(formulario.familia_ciente)}</p>
					<p><strong>Telefone:</strong> ${formulario.telefone}</p>
					<p><strong>Apartamento Liberado:</strong> ${booleanToString(formulario.ap_liberado)}</p>
	
					<button class="botao-excluir" data-id="${formulario.id_formulario}">Excluir</button>
				</div>
			</div>`;
		});
		const botoesExcluir = document.querySelectorAll('.botao-excluir');

		botoesExcluir.forEach(botao => {
			botao.addEventListener('click', function() {
				const formularioId = this.getAttribute('data-id');
				deleteForm(formularioId);  // Chama a função de exclusão com o ID do formulário
			});
		});
	}
});
