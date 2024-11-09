package model;
import java.util.*;

public class TagsAnimal {
    
    private int id_tagAnimal;
    private ArrayList<String> conteudo_tag;
    private int id_animal;

    //---------Construtor Padrão----------//
    public TagsAnimal(){
        this.id_tagAnimal = -1;
        this.conteudo_tag = new ArrayList<>();
        this.id_animal = -1;
    }

    public TagsAnimal(int id_tagAnimal, ArrayList<String> conteudo_tag, int id_animal){
        this.id_tagAnimal = id_tagAnimal;
        this.conteudo_tag = conteudo_tag;
        this.id_animal = id_animal;
    }

    //---------ID----------//
    public int getId_tagAnimal(){
        return id_tagAnimal;
    }

    public void setId_tagAnimal(int id_tagAnimal){
        this.id_tagAnimal = id_tagAnimal;
    }

    //---------Conteudo TagAnimal----------//
    public ArrayList<String> getConteudo_tag(){
        return conteudo_tag;
    }

    public void setConteudo_tag(ArrayList<String> conteudo_tag){
        this.conteudo_tag = conteudo_tag;
    }

    //---------Conteudo TagAnimal----------//
    public int getId_animal(){
        return id_animal;
    }

    public void setId_animal(int id_animal){
        this.id_animal = id_animal;
    }


    @Override
	public String toString() {
		return "\nTagsAnimal [id_tagAnimal: " + id_tagAnimal +
			   "\nconteudo_tag: " + conteudo_tag +
			   "\nid_animal: " + id_animal +"]\n";
	}


	@Override
	public boolean equals(Object obj){
		return (this.getId_tagAnimal() == ((TagsAnimal) obj).getId_tagAnimal());
	}

}
