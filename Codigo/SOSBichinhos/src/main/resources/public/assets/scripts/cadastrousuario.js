//Henrique 
const Usuario = "/usuario";
const Endereco = "/endereco";
// Exibe mensagem em um elemento de ID msg

function LCdisplayMessage(mensagem) {
    msg = document.getElementById('LCmsg');
    msg.innerHTML = '<div class="alert alert-warning">' + mensagem + '</div>';
} // end displayMessage ( )

function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
 /**
     * @param {object} imagem imagem a ser salvado pelo backend
     */

 async function saveDataImage(file) {
    const apiKey = 'a4dcb923f4523e3bd8f0b6a7cdf23f0f'; // My_API_KEY do ImgBB
    const formData = new FormData();
    formData.append('image', file);

    try {
        const response = await fetch(`https://api.imgbb.com/1/upload?key=${apiKey}`, {
            method: 'POST',
            body: formData
        });

        const data = await response.json();

        if (data.success) {
            console.log('URL da imagem:', data.data.url);
            return data.data.url; // Retorna a URL da imagem
        } else {
            console.error('Erro ao enviar a imagem:', data.error.message);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
};

async function HG_createUserAndEndereco(user, endereco) {
    try {
        // Criação do usuário
        console.log(user);
        const userResponse = await fetch('/usuario', { 
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
        const enderecoResponse = await fetch('/endereco', { 
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
        LCdisplayMessage("Sucesso ao criar o Usuario!");

        await delay(3000); 

        window.location.href = "../../modules/login/login.html";

    } catch (error) {
        console.error('Erro:', error);
        LCdisplayMessage(error.message);
    }
}

async function HG_alterUser(user, endereco) {
    console.log(user);
    console.log(endereco);
    try {
        await updateUsuario(user);
        await updateEndereco(endereco); // Aguarda a atualização do endereço
        LCdisplayMessage("Alterado!");
        await delay(2000);
        window.location.reload();
    } catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao editar usuário ou endereço: " + error.message);
    }
}

async function updateEndereco(endereco) {
    try {
        const response = await fetch(`${Endereco}/update/${endereco.id_endereco}`, { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(endereco),
        });

        if (!response.ok) {
            throw new Error("Erro ao editar endereço");
        }

        const dataEndereco = await response.json();  
        console.log("Endereço atualizado:", dataEndereco);
    } catch (error) {
        console.error("Erro ao processar resposta:", error);
        throw error;  // Opcional: relançar o erro se necessário
    }
}

async function updateUsuario(user) {
    const responseUsuario = await fetch(`${Usuario}/update/${user.id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    });

    if (!responseUsuario.ok) {
        throw new Error("Erro ao editar usuário");
    }

    const dataUsuario = await responseUsuario.json();
    console.log("Usuário atualizado:", dataUsuario); 
}

async function readEnderecos() {
    //  definir dados locais
    let users = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(Endereco);
        users = await response.json();
        //  mostrar resultado
        console.log("Success:", users);
        //  displayMessage("Sucesso ao ler usuarios!");
    }
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler usuarios (JSON Server indisponível).");
    }
    //  retornar
    return (users);
} 

async function HG_deleteUser(id, id_endereco) {
    try {
        // Remover o endereço
        const responseEndereco = await fetch(`${Endereco}/delete/${id_endereco}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
        });

        if (!responseEndereco.ok) {
            const errorText = await responseEndereco.text();
            throw new Error(`Erro ao remover o endereço: ${responseEndereco.status} - ${errorText}`);
        }
        console.log(`Endereço removido: Status ${responseEndereco.status}`);

        const resultEndereco = await responseEndereco.json();
        console.log('Endereço resultado:', resultEndereco);

        // Agora, remover o usuário
        const responseUsuario = await fetch(`${Usuario}/delete/${id}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
        });

        if (!responseUsuario.ok) {
            const errorText = await responseUsuario.text();
            throw new Error(`Erro ao remover o usuário: ${responseUsuario.status} - ${errorText}`);
        }

        const resultUsuario = await responseUsuario.json();
        console.log('Usuário resultado:', resultUsuario);

        LCdisplayMessage(`Usuário removido com sucesso`);

        await delay(3000); 

        LCpaginaInicial();
    } catch (error) {
        console.error('Erro:', error);
        LCdisplayMessage(`Erro ao remover o usuário ou usuário inexistente: ${error.message}`);
    }
}


function LCpaginaInicial() {
    window.location.href = "../../index.html";
} // end LCpaginaInicial ( )

function LCexcluirUsuarioLS() {
    localStorage.removeItem('db');
} 

async function checkUser() {
    //  definir dados locais
    let inputEmailValue = document.querySelector('#inputEmail').value;
    let inputSenhaValue = document.querySelector('#inputSenha').value;
    //  testar se há algo digitado
    if (inputEmailValue != '' || inputSenhaValue != '') {
        //  ler dados do JSON Server
        let users = await readUsers();
        let enderecos = await readEnderecos();
        //  ler usuario da ong
        
        //  testar se e' ong
        if (inputEmailValue === "SOSBichinhosdiw@gmail.com" && inputSenhaValue === "Patinhas02") {
            window.location.href = "../../modules/telaInicialONG/telainicialONG.html";
        }
        else {
            //  filtrar os usuarios de acordo com os valores digitados
            let userFilter = users.find((e) => e.email === inputEmailValue && e.senha === inputSenhaValue);

           
            
            

            //  testar o resultado
            if (!userFilter) {
                clearLogin();
                LCdisplayMessage('ERRO: Email ou senha incorretos.');
            }
            else {
                let endereco = enderecos.find((e) => e.id_pessoa === userFilter.id);
                LCcriarUsuarioLS(userFilter);

                if(endereco)
                LCcriarEndereco(endereco);

                LCdisplayMessage('Usuário válido.');
                window.location.href = "../../modules/exibicaoAnimais/exibiranimal.html";
            }
        }
    }
} // end checkUser ()

function clearLogin() {
    document.querySelector('#inputEmail').value = '';
    document.querySelector('#inputSenha').value = '';
}

function startLogin() {
    let btnInsert = document.querySelector('#btnInsert');
    btnInsert.addEventListener('click', () => checkUser());

    let btnClear = document.querySelector('#btnClear');
    btnClear.addEventListener('click', () => clearLogin());
}

async function readUsers() {
    //  definir dados locais
    let users = {};
    //  tentar fazer a chamada
    try {
        //  definir a chamade HTTP do JSON Server
        const response = await fetch(Usuario);
        users = await response.json();
        //  mostrar resultado
        console.log("Success:", users);
        //  displayMessage("Sucesso ao ler usuarios!");
    }
    catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler usuarios (JSON Server indisponível).");
    }
    //  retornar
    return (users);
} // end readUsers ( )

function LCcriarUsuarioLS(user) {
    localStorage.setItem('db', JSON.stringify(user));
} // end LCcriarUsuarioLS ( )

function LCcriarEndereco(user) {

    localStorage.setItem('endereco', JSON.stringify(user));
} 

async function HG_readUser(id) {
    let perfil = {};
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${Usuario}/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        perfil = await response.json();
        // Mostrar resultado
        //console.log("Success:", perfil);
    } catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler perfil");
    }
    return perfil;
} // end HG_readUser()

async function readEndereco(id) {
    let perfil = {};
    try {
        // Definir a chamada HTTP do JSON Server
        const response = await fetch(`${Endereco}/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        perfil = await response.json();
        // Mostrar resultado
        //console.log("Success:", perfil);
    } catch (error) {
        console.error("Error:", error);
        LCdisplayMessage("Erro ao ler perfil");
    }
    return perfil;
}

function LCverificarLogin() {
    // definir dados locais
    let user = LCgetUser();
    let controle = false;
    // testar se há usuário logado
    if (!(Object.keys(user).length > 0))
        window.location.href = "../../modules/login/login.html";
    else
        controle = true;
    // retornar
    return (controle);
} // end LCverificarLogin( )

function LCgetUser() {
    //  definir dados locais
    let strUser = localStorage.getItem('db');
    let objUser = {};
    //  testar leitura
    if (strUser) {
        objUser = JSON.parse(strUser);
    }
    //  retornar
    return (objUser);
} 
function getEndereco() {
    //  definir dados locais
    let strUser = localStorage.getItem('endereco');
    let objUser = {};
    //  testar leitura
    if (strUser) {
        objUser = JSON.parse(strUser);
    }
    //  retornar
    return (objUser);
} 

function LCverificarPerfil() {
    //  testar login
    if (LCverificarLogin()) {
        console.log('logado');
    }
} 
