package model;

public class TagsAnimal {
    
    private int id_tagAnimal;
    private String conteudo_tag;
    private int id_animal;

    //---------Construtor Padrão----------//
    public TagsAnimal(){
        this.id_animal = -1;
        this.conteudo_tag = "";
        this.id_animal = -1;
    }

    public TagsAnimal(int id_tagAnimal, String conteudo_tag, int id_animal){
        this.id_animal = id_tagAnimal;
        this.conteudo_tag = conteudo_tag;
        this.id_animal = id_animal;
    }

    //---------ID----------//
    public int getId_tagAnimal(){
        return id_tagAnimal;
    }

    public void setId_tagAnimal(int id_animal){
        this.id_animal = id_tagAnimal;
    }

    //---------Conteudo TagAnimal----------//
    public String getConteudo_tag(){
        return conteudo_tag;
    }

    public void setConteudo_tag(String conteudo_tag){
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
