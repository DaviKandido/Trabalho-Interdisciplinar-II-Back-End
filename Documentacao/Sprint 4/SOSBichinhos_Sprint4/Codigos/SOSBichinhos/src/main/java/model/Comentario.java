//[bibliotecas]
package model;

//[cabecalho]
/**
 * @author: matheusEduardoCamposSoares - 837435
 * @data: 19.10.2024 - modelComentario
**/

public class Comentario {
	
	private int id;
	private String conteudo;
	private int  id_animal;
	private int id_pessoa;

	
	//---------Construtor Padrão----------//
	public Comentario(){
		id = -1;
		conteudo = "";
        id_animal = -1;
        id_pessoa = -1;
	}

	//---------Construtor----------//
	public Comentario(int id, String conteudo, int id_animal, int id_pessoa)
    {
		this.id = id;
		this.conteudo = conteudo;
        this.id_animal = id_animal;
        this.id_pessoa = id_pessoa;
	}

	//---------ID----------//
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	//---------CONTEUDO----------//
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	//---------ID_ANIMAL----------//
	public int getIdAnimal() {
		return id_animal;
	}

	public void setIdAnimal(int id_animal) {
		this.id_animal = id_animal;
	}

	//---------ID_PESSOA----------//
	public int getIdPessoa() {
		return id_pessoa;
	}

	public void setIdPessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	@Override
	public String toString() 
    {
		return "\nCOMENTARIO [ID: " + id +
			   "\nConteudo: " + conteudo +
			   "\nId_animal: " + id_animal +
			   "\nId_pessoa: " + id_pessoa + "]\n";
	}


	@Override
	public boolean equals(Object obj){
		return (this.getId() == ((Comentario) obj).getId());
	}

}
