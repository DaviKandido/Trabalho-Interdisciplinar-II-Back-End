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
    async function deleteData(id) {
      try {
          const response = await fetch(`${apiUrl_CRUD_Animal}/delete/${id}`, { // URL base do servidor
              method: 'POST',
          });

          const data = await response.json();
          console.log(data);
          alert("Animal " + data + " removido com sucesso");
          return data; // Retorna o id do animal removido
      } catch (error) {
          console.error('Erro:', error);
          alert("Erro ao remover o animal ou animal inexistente");
          throw error; // Re-throw o erro para que possa ser capturado pelo chamador
      }
  }

   async function getAnimal(FunctionCallBack, id) {
        fetch(`${apiUrl_CRUD_Animal}/${id}`)
          .then((res) => res.json())
          .then(data => {
            FunctionCallBack(data);
            return data;
          })
          .catch(error => {
            alert("Animal não encontrado ou inexistente");
            console.error('Erro:', error);
          });
      }

    async function getTodosAnimais(FunctionCallBack) {
        fetch(`${apiUrl_CRUD_Animal}`)
          .then((res) => res.json())
          .then(data => {
            FunctionCallBack(data);
            return data;
          })
          .catch(error => {
            alert("Animal não encontrado ou inexistente");
            console.error('Erro:', error);
          });
      }

    //-------------------------------- END - FUNÇÕES SaveData -----------------------------------//

    function GraverAnimal() {
      // Selecionando os elementos diretamente
      let inputNomeAnimal = document.querySelector("#nome-input-animal");
      let inputSexoAnimal = document.querySelector('#sexo-input-animal');
      let inputIdadeAnimal = document.querySelector("#idade-input-animal");
      let inputRacaAnimal = document.querySelector("#raca-input-animal");
      let inputVacinasAnimal = document.querySelector("#vacinas-input-animal");
      let inputCastradoAnimal = document.querySelector("#castrado-input-animal");
      let inputHistoriaAnimal = document.querySelector("#historia-input-animal");
      let inputPorteAnimal = document.querySelector('#porte-input-animal');
      let inputEspecieAnimal = document.querySelector('#especie-input-animal');

      let inputTag1Animal = document.querySelector('#tag1-input-animal');
      let inputTag2Animal = document.querySelector('#tag2-input-animal');
      let inputTag3Animal = document.querySelector('#tag3-input-animal');

      
      // Agora, acessamos os valores dos inputs
      let castradoBoolean = inputCastradoAnimal.value === "true";
  
      let Animal = {
          imagem: "inputImagemAnimal", // Concertar input imagem
          nome: inputNomeAnimal.value,
          sexo: inputSexoAnimal.value,
          idade: inputIdadeAnimal.value,
          raca: inputRacaAnimal.value,
          vacinas: inputVacinasAnimal.value,
          castrado: castradoBoolean,
          historia: inputHistoriaAnimal.value,
          porte: inputPorteAnimal.value,
          especie: inputEspecieAnimal.value
      };
      
      console.log(Animal);
      
      // Limpar os campos após o salvamento
      inputNomeAnimal.value = "";
      inputSexoAnimal.value = "";
      inputIdadeAnimal.value = "";
      inputRacaAnimal.value = "";
      inputVacinasAnimal.value = "";
      inputCastradoAnimal.value = "";
      inputHistoriaAnimal.value = "";
      inputPorteAnimal.value = "";
      inputEspecieAnimal.value = "";

      inputTag1Animal.value = "";
      inputTag2Animal.value = "";
      inputTag3Animal.value = "";
      
  
      // Chama a função para salvar os dados
      saveDataAnimal(Animal);
  }

    //--------------------------------EventListener - Botões-----------------------------------//

    const CadastrarAnimalBtn = document.querySelector(".botao-cadastra-animal");
    const RemoveAnimalBtn = document.querySelector(".botao-remove-animal");
    const inputIdRemoveAnimal = document.querySelector("#id-remove-animal");

    CadastrarAnimalBtn.addEventListener("click", GraverAnimal);

    RemoveAnimalBtn.addEventListener("click", async function() {
      const idRemoveAnimal = inputIdRemoveAnimal.value;
  
      try {
          // Aguarde a conclusão da exclusão do animal
          await deleteData(idRemoveAnimal);
          
          getTodosAnimais(ListaTodosAnimais);
      } catch (error) {
          console.error("Erro ao remover animal:", error);
      }
  });


//----------------Mostra animal solicitado em JSON -----------------//

    const DetalharAnimalJSONBtn = document.querySelector(".botao-detalha-animal-JSON"); 

    DetalharAnimalJSONBtn.addEventListener("click", function() {

        const inputIdAnimal= document.querySelector("#id-detalha-animal").value;

        getAnimal(MostraAnimalVisualizavel_EmJSON, inputIdAnimal)
        
    });

    function MostraAnimalVisualizavel_EmJSON(animal) {

        window.location.href = "http://localhost:8081/animal/" + animal.id_animal;
    }



    //----------------Mostra animal solicitado no html -----------------//


    const DetalharAnimalBtn = document.querySelector(".botao-detalha-animal");



    DetalharAnimalBtn.addEventListener("click", function(){

        const inputIdAnimal= document.querySelector("#id-detalha-animal").value;

        getAnimal(MostraAnimalVisualizavel, inputIdAnimal)
    });






    function MostraAnimalVisualizavel(animal){

        const divAnimalVisualizavel = document.querySelector(".pagina-container-Animal-Visualizavel");

        divAnimalVisualizavel.style.display = "flex"

        divAnimalVisualizavel.innerHTML = `<h1 class="titulo">Animal visualizavel</h1>

                <div class="Card-Animal-Visualizavel">

          <div>
            <img id="avatarPrincipalJP" src="../../imgs/imagsInputs/${animal.imagem}" alt="animal do animal ${animal.nome}">
          </div>
          <div class="informacoesJP">
            <div id="nomeAutorJP">
              <p>Nome: ${animal.nome}</p>
            </div>
            <p><strong>id do animal:</strong> ${animal.id_animal}</p>
            <p><strong>Idade:</strong>${animal.idade}</p>
            <p><strong>Sexo:</strong> ${animal.sexo}</p>
            <p><strong>Raça:</strong> ${animal.raca}</p>
            <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
            <p><strong>Castrado:</strong> ${animal.castrado}</p>
            <p><strong>Porte:</strong> ${animal.porte}</p>
            <p><strong>Espécie:</strong> ${animal.especie}</p>
            <p><strong>Tags:</strong> ${animal.tags}</p>
            <div id="descricaoJP">
              <h4><strong>História:</strong></h4>
              <p>
                ${animal.historia}
              </p>
            </div>
          </div>
        </div>`;

        divAnimalVisualizavel.innerHTML += `<button type="submit" class="botao-fechaAba-Animal-Visualizavel">Fechar</button>`


        const FecharAbaAnimalVisualizavelBtn = document.querySelector(".botao-fechaAba-Animal-Visualizavel");

        FecharAbaAnimalVisualizavelBtn.addEventListener("click", function(){


        divAnimalVisualizavel.style.display = "none"
       });

    }



    //----------------Mostra TODOS os animal solicitado no html -----------------//


    const botaolistaAnimaisBtn = document.querySelector(".botao-lista-animais");

    

    botaolistaAnimaisBtn.addEventListener("click", function(){

        getTodosAnimais(ListaTodosAnimais)
    });

    function ListaTodosAnimais(animais){

        const divAnimalVisualizavel = document.querySelector(".pagina-container-Animal-Visualizavel");

        divAnimalVisualizavel.style.display = "flex"

         divAnimalVisualizavel.innerHTML = `<h1 class="titulo">Animal Visualizavel</h1>`

         animais.forEach(animal => {
             
             divAnimalVisualizavel.innerHTML += `
     
                     <div class="Card-Animal-Visualizavel">
     
               <div>
                 <img id="avatarPrincipalJP" src="../../imgs/imagsInputs/${animal.imagem}" alt="animal">
               </div>
               <div class="informacoesJP">
                 <div id="nomeAutorJP">
                   <p>Nome: ${animal.nome}</p>
                 </div>
                 <p><strong>id do animal:</strong> ${animal.id_animal}</p>
                 <p><strong>Idade:</strong>${animal.idade}</p>
                 <p><strong>Sexo:</strong> ${animal.sexo}</p>
                 <p><strong>Raça:</strong> ${animal.raca}</p>
                 <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
                 <p><strong>Castrado:</strong> ${animal.castrado}</p>
                 <p><strong>Porte:</strong> ${animal.porte}</p>
                 <p><strong>Espécie:</strong> ${animal.especie}</p>
                 <p><strong>Tags:</strong> ${animal.tags}</p>
                 <div id="descricaoJP">
                   <h4><strong>História:</strong></h4>
                   <p>
                     ${animal.historia}
                   </p>
                 </div>
               </div>
             </div>`;


            });
            
        divAnimalVisualizavel.innerHTML += `<button type="submit" class="botao-fechaAba-Animal-Visualizavel">Fechar</button>`

        const FecharAbaAnimalVisualizavelBtn = document.querySelector(".botao-fechaAba-Animal-Visualizavel");

        FecharAbaAnimalVisualizavelBtn.addEventListener("click", function(){


        divAnimalVisualizavel.style.display = "none"
       });

    }
    

        //--------------------------------End - EventListener Botões-----------------------------------//


}); // <-- Aqui está a chave de fechamento do primeiro bloco DOMContentLoaded

