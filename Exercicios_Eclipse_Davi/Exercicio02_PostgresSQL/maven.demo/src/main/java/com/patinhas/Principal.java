package com.patinhas;

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
	}

}
