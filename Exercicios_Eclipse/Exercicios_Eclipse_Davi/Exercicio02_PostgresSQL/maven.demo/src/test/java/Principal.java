
/*

package com.patinhas;


import java.util.*;
public class Principal {

	public static void main(String[] args) {


		DAO dao = new DAO();

		dao.Conectar();
		

		//Inserir um animal na tabela
		Animal animal = new Animal(2, 
							"http://example.com/animal.jpg",
							"Rex", 
           				 	'M', 
            				 "5 anos", 
            				"Labrador", 
           			     "Vacina Anti-Raiva, Vacina Polivalente", 
            		  true, 
            			"Foi encontrado abandonado e agora está em um abrigo.", 
           				    "ativo, amigável", 
          				   'G', 
                         "Cão" );
		if(dao.inserirAnimal(animal) == true){
			System.out.println("Animal inserido com sucesso! ->" + animal.toString());
		}

		//Mostrar animais machos
		System.out.println("\n =====  Animais Machos =====");
		Animal[] animais = dao.getAnimaisMachos();
		for (int i = 0; i < animais.length; i++) {
			System.out.println(animais[i].toString());
		}

		//Atualiza animal
		animal.setNome("Rex2");
		dao.atualizarAnimal(animal);

		//Mostar todos os animais
		System.out.println("\n =====  Todos os Animais =====");
		Animal[] animais2 = dao.getAnimais();
		for (int i = 0; i < animais2.length; i++) {
			System.out.println(animais2[i].toString());
		}

		//Excluir animal
		dao.excluirAnimal(animal.getId());

		//Mostar todos os animais
		animais = dao.getAnimais();
		System.out.println("\n =====  Todos os Animais =====");
		for (int i = 0; i < animais.length; i++) {
			System.out.println(animais2[i].toString());
		}

		dao.close();
	}

}


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

 */