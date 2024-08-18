package com.patinhas;


import java.util.*;
public class Principal {

	public static void main(String[] args) {

		DAO dao = new DAO();

		boolean parar = true;
		Scanner sc = new Scanner(System.in);
		Animal animal = new Animal();

		dao.Conectar();

		while (parar) {
			
			System.out.println("\nEscolha uma opção: ");
			System.out.println("1 - Listar Patinhas");
			System.out.println("2 - Cadastrar Patinha");
			System.out.println("3 - Excluir Patinha");
			System.out.println("4 - Atualizar Patinha");
			System.out.println("5 - Sair");
			System.out.print("Opção: ");
			int opcao = sc.nextInt();


			Animal[] animais = dao.getAnimais();
			//Lista todos os animais
			if(opcao == 1){


				//Mostar todos os animais
				animais = dao.getAnimais();
				System.out.println("\n =====  Todos os Animais =====");
				for (int i = 0; i < animais.length; i++) {
					System.out.println(animais[i].toString());
				}				

			//cadastra animal
			}else if(opcao == 2){

				System.out.println("Insira um id:");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Insira uma url de sua foto:");
				String url = sc.nextLine();
				System.out.println("Insira o seu nome:");
				String nome = sc.nextLine();
				System.out.println("Insira o seu sexo: M(macho) - F(Femea)");
				char sexo = sc.next().charAt(0);
				System.out.println("Insira a sua idade:");
				String idade = sc.nextLine();
				System.out.println("Insira a sua raça:");
				String raca = sc.nextLine();
				System.out.println("Insira as suas vacinas:");
				String vacinas = sc.nextLine();
				System.out.println("Insira a sua historia:");
				String historia = sc.nextLine();
				System.out.println("Insira tags para o animal:");
				String tags = sc.nextLine();
				System.out.println("Insira seu porte: G(grande) - M(medio) P(pequeno)");
				char porte = sc.next().charAt(0);
				System.out.println("Insira sua especie:");
				String especie = sc.nextLine();
				
				//Inserir um animal na tabela
				animal = new Animal(id, 
									url,
									nome, 
									sexo, 
									idade, 
									raca, 
									vacinas, 
									false, 
									historia, 
									tags, 
								porte, 
								especie );
				if(dao.inserirAnimal(animal) == true){
					System.out.println("Animal inserido com sucesso! ->" + animal.toString());
				}				

			//Exclui animal
			}else if(opcao == 3){

				//Excluir animal
				System.out.print("Digite o id do animal que deseja excluir: ");
				int id = sc.nextInt();
				dao.excluirAnimal(id);

			//Atualiza animal
			}else if(opcao == 4){

				System.out.println("\nO que você deseja atualizar no animal?");
				System.out.println("1 - Nome");
				System.out.println("2 - Url da foto");
				System.out.println("3 - Idade");
				System.out.println("4 - Raça");
				System.out.println("5 - Vacinas");
				System.out.println("6 - Porte");
				System.out.println("7 - Especie");
				System.out.println("8 - Sexo");
				System.out.println("9 - Tags");
				System.out.println("10 - Historia");
				int opcao2 = sc.nextInt();
				
				switch (opcao2) {
					case 1:
						System.out.print("Digite o novo nome do animal: ");
						String nome = sc.next();
						animal.setNome(nome);
						dao.atualizarAnimal(animal);
						break;
				
					case 2:
						System.out.print("Digite a nova Url da foto: ");
						String url = sc.next();
						animal.setUrl(url);
						dao.atualizarAnimal(animal);
						break;
				
					case 3:
						System.out.print("Digite a nova idade do animal: ");
						String idade = sc.next();
						animal.setIdade(idade);
						dao.atualizarAnimal(animal);
						break;
				
					case 4:
						System.out.print("Digite a nova raça do animal: ");
						String raca = sc.next();
						animal.setRaca(raca);
						dao.atualizarAnimal(animal);
						break;
				
					case 5:
						System.out.print("Digite as novas vacinas do animal: ");
						String vacinas = sc.next();
						animal.setVacinas(vacinas);
						dao.atualizarAnimal(animal);
						break;
				
					case 6:
						System.out.print("Digite o novo porte do animal (P/M/G): ");
						char porte = sc.next().charAt(0);
						animal.setPorte(porte);
						dao.atualizarAnimal(animal);
						break;
				
					case 7:
						System.out.print("Digite a nova espécie do animal: ");
						String especie = sc.next();
						animal.setEspecie(especie);
						dao.atualizarAnimal(animal);
						break;
				
					case 8:
						System.out.print("Digite o novo sexo do animal (M/F): ");
						char sexo = sc.next().charAt(0);
						animal.setSexo(sexo);
						dao.atualizarAnimal(animal);
						break;
				
					case 9:
						System.out.print("Digite as novas tags do animal: ");
						String tags = sc.next();
						animal.setTags(tags);
						dao.atualizarAnimal(animal);
						break;
				
					case 10:
						System.out.print("Digite a nova história do animal: ");
						sc.nextLine(); // Limpa o buffer
						String historia = sc.nextLine();
						animal.setHistoria(historia);
						dao.atualizarAnimal(animal);
						break;
				
					default:
						System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
						break;
				}
				

			}else{

				parar = false;

			}
		
	}

		dao.close();
	}

}

//======================  Códigos Padrões de INSERTE DELETE E UPDATE  =========================//

// //Inserir um animal na tabela
// Animal animal = new Animal(2, 
// "http://example.com/animal.jpg",
// "Rex", 
// 	'M', 
//  "5 anos", 
// "Labrador", 
// "Vacina Anti-Raiva, Vacina Polivalente", 
// true, 
// "Foi encontrado abandonado e agora está em um abrigo.", 
//    "ativo, amigável", 
//  'G', 
// "Cão" );
// if(dao.inserirAnimal(animal) == true){
// System.out.println("Animal inserido com sucesso! ->" + animal.toString());
// }

// //Mostrar animais machos
// System.out.println("\n =====  Animais Machos =====");
// Animal[] animais = dao.getAnimaisMachos();
// for (int i = 0; i < animais.length; i++) {
// System.out.println(animais[i].toString());
// }

// //Atualiza animal
// animal.setNome("Rex2");
// dao.atualizarAnimal(animal);

// //Mostar todos os animais
// System.out.println("\n =====  Todos os Animais =====");
// Animal[] animais2 = dao.getAnimais();
// for (int i = 0; i < animais2.length; i++) {
// System.out.println(animais2[i].toString());
// }

// //Excluir animal
// dao.excluirAnimal(animal.getId());

// //Mostar todos os animais
// animais = dao.getAnimais();
// System.out.println("\n =====  Todos os Animais =====");
// for (int i = 0; i < animais.length; i++) {
// System.out.println(animais2[i].toString());
// }

		// //Mostrar animais machos
		// System.out.println("\n =====  Animais Machos =====");
		// Animal[] animais = dao.getAnimaisMachos();
		// for (int i = 0; i < animais.length; i++) {
		// 	System.out.println(animais[i].toString());
		// }

		// //Atualiza animal
		// animal.setNome("Rex2");
		// dao.atualizarAnimal(animal);

//======================  END - Códigos Padrões de INSERTE DELETE E UPDATE  =========================//