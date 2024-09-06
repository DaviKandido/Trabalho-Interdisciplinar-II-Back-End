document.addEventListener("DOMContentLoaded", () => {

    const apiUrl_CRUD_Animal = "/animal";

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
            alert("Animal adicionado com sucesso");
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
    function deleteData(id) {
        fetch(`${apiUrl_CRUD_Animal}/delete/${id}`, {
            method: "POST",
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
        const inputsexoAnimal= document.querySelector('input[name="sexo"]:checked').value;
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
            url: inputUrlAnimal,
            nome: inputNomeAnimal,
            sexo: inputsexoAnimal,
            idade: inputIdadeAnimal,
            raca: inputRacaAnimal,
            vacinas: inputVacinasAnimal,
            cidade: inputCidadeAnimal,
            tags: tagsSelecionadas[0], // Adiciona as tags selecionadas [0] temporária
            historia: inputHistoriaAnimal,
            porte: inputPorteAnimal,
            especie: inputEspecieAnimal
        };
        
        saveDataAnimal(Animal);
        console.log(Animal);
    }

    //--------------------------------EventListener - Botões-----------------------------------//

    const CadastrarAnimalBtn = document.querySelector(".botao-cadastra-animal");
    const RemoveAnimalBtn = document.querySelector(".botao-remove-animal");
    const inputIdRemoveAnimal = document.querySelector("#id-remove-animal");

    CadastrarAnimalBtn.addEventListener("click", GraverAnimal);

    RemoveAnimalBtn.addEventListener("click", function() {
        const idRemoveAnimal = inputIdRemoveAnimal.value;
        deleteData(idRemoveAnimal);
    });

    //--------------------------------End - EventListener Botões-----------------------------------//
});
