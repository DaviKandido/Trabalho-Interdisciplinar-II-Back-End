document.addEventListener("DOMContentLoaded", () => {

    const apiUrl_CRUD_Animal = "http://localhost:6789/animal";

    //--------------------------------FUNÇÕES saveData-----------------------------------//

    /**
     * Manda para o JSON qualquer objeto
     * @param {object} dado objeto a ser salvado no JSON server
     */
    function saveDataAnimal(dado) {
        fetch(apiUrl_CRUD_Animal, { // URL base do servidor
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dado)
        }).then(response => response.json())
          .then(dado => {
            console.log(dado);
            alert("Animal Adicionado com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao adicionar o animal");
          });
    }

    /**
     * Apaga do server os objetos
     * @param {string} id ID do objeto a ser excluído
     */
    function deleteDataArquivo(id) {
        fetch(`${apiUrl_CRUD_Animal}/${id}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Erro na resposta da API');
        })
          .then(data => {
            alert(`Animal ${id} deletado com sucesso`);
            console.log(data);
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao remover o animal");
          });
    }

    //-------------------------------- END - FUNÇÕES SaveData -----------------------------------//

    function GraverAnimal() {
        const inputUrlAnimal = document.querySelector("#url-input-animal").value;
        const inputNomeAnimal = document.querySelector("#nome-input-animal").value;
        const sexoSelecionado = document.querySelector('input[name="sexo"]:checked').value;
        const inputIdadeAnimal = document.querySelector("#idade-input-animal").value;
        const inputRacaAnimal = document.querySelector("#raca-input-animal").value;
        const inputVacinasAnimal = document.querySelector("#vacinas-input-animal").value;
        const inputCidadeAnimal = document.querySelector("#cidade-input-animal").value;
        const inputHistoriaAnimal = document.querySelector("#historia-input-animal").value;
        const inputPorteAnimal = document.querySelector('input[name="porte"]:checked').value;
        const inputEspecieAnimal = document.querySelector('#especie-input-animal').value;
        
        // Selecionando as tags marcadas
        const tagsSelecionadas = Array.from(document.querySelectorAll('input[name="tags"]:checked')).map(checkbox => checkbox.value);
        
        let Animal = {
            url_animal: inputUrlAnimal,
            nome_animal: inputNomeAnimal,
            sexo_animal: sexoSelecionado,
            idade_animal: inputIdadeAnimal,
            raca_animal: inputRacaAnimal,
            vacinas_animal: inputVacinasAnimal,
            cidade_animal: inputCidadeAnimal,
            tags_animal: tagsSelecionadas[0], // Adiciona as tags selecionadas [0] temporária
            historia_animal: inputHistoriaAnimal,
            porte_animal: inputPorteAnimal,
            especie_animal: inputEspecieAnimal
        };
        
        saveDataAnimal(Animal);
        console.log(Animal);
    }

    //--------------------------------EventListener - Botões-----------------------------------//

    const CadastrarAnimalBtn = document.querySelector(".botao-cadastra-animal");
    const RemoveAnimalBtn = document.querySelector(".botao-remove-animal");
    const inputIdRemoveAnimal = document.querySelector("#id-input-animal");

    CadastrarAnimalBtn.addEventListener("click", GraverAnimal);

    RemoveAnimalBtn.addEventListener("click", function() {
        const idRemoveAnimal = inputIdRemoveAnimal.value;
        deleteDataArquivo(idRemoveAnimal);
    });

    //--------------------------------End - EventListener Botões-----------------------------------//
});