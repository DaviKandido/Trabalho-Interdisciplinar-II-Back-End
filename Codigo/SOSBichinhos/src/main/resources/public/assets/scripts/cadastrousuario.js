//Henrique 
const Usuario = "/Usuario";

// Exibe mensagem em um elemento de ID msg

function LCdisplayMessage(mensagem) {
    msg = document.getElementById('LCmsg');
    msg.innerHTML = '<div class="alert alert-warning">' + mensagem + '</div>';
} // end displayMessage ( )

async function HG_createUserAndEndereco(user, endereco) {
    try {
        // Criação do usuário
        console.log(user);
        const userResponse = await fetch('/Usuario', { // URL base do servidor
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        if (!userResponse.ok) {
            throw new Error('Erro ao criar o usuário');
        }

        const createdUser = await userResponse.json();
        console.log(createdUser);
        LCdisplayMessage("Sucesso ao criar usuário");

        // Atribuir o ID do usuário recém-criado ao endereço
        endereco.id_pessoa = createdUser.id;

        // Criação do endereço
        console.log(endereco);
        const enderecoResponse = await fetch('/Endereco', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(endereco)
        });

        if (!enderecoResponse.ok) {
            throw new Error('Erro ao criar o endereço');
        }

        const createdEndereco = await enderecoResponse.json();
        console.log(createdEndereco);
        LCdisplayMessage("Sucesso ao criar o usuário!");

    } catch (error) {
        console.error('Erro:', error);
        LCdisplayMessage(error.message);
    }
}

function HG_alterUser(user) {
    try {
        // Definir a chamada HTTP do JSON Server
          fetch(`${Usuario}/put/` + user.id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        });
        // Mostrar resultado
       LCdisplayMessage("Sucesso ao editar usuário!");
    }
    // Chamada de erro
    catch (error) {
        console.error("Error:", error);
       LCdisplayMessage("Erro ao editar usuário.");
    }
} 


 function HG_deleteUser(id) {
    fetch(`${Usuario}/delete/` + id, { // URL base do servidor
        method: 'POST',
    }).then(response => response.json())
      .then(id => {
        console.log(id);
        LCdisplayMessage("usuario " + id + " removido com sucesso");
      })
      .catch(error => {
          console.error('Erro:', error);
          LCdisplayMessage("Erro ao remover o usuario ou usuario inexistente");
      });
}


