package model;

public class Formulario {
	
    //---- Atributods Formulario ----//
    private int id_formulario;
    private String animal_sozinho;
    private boolean familia_ciente;
    private boolean permissao;
    private boolean teve_animal;
    private int id_pessoa; // fk Usuario
    private int id_animal; // fk animal
    private String telefone;
    private boolean ap_liberado;

    //---- Construtores ----//
    public Formulario() {
        this.id_formulario = -1;
        this.id_pessoa = -1; // Inicializa como null
        this.id_animal = -1; // Inicializa como null
        this.animal_sozinho = "";
        this.familia_ciente = false; 
        this.permissao = false; 
        this.teve_animal = false;
        this.telefone = ""; 
        this.ap_liberado = false;
    }

    public Formulario(int id, String animal_sozinho, boolean familia_ciente, boolean permissao, 
        boolean teve_animal, int id_animal, int id_pessoa, String telefone, boolean ap_liberado) {
        this.id_formulario = id;
        this.animal_sozinho = animal_sozinho;
        this.familia_ciente = familia_ciente;
        this.permissao = permissao;
        this.teve_animal = teve_animal;
        this.id_animal = id_animal;  // ID Animal
        this.id_pessoa = id_pessoa;  // ID Pessoa
        this.telefone = telefone;
        this.ap_liberado = ap_liberado;
    }

    //---- Metodos get e set ----//
    public int getIdFormulario() {
        return id_formulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.id_formulario = idFormulario;
    }

    public int getIdPessoa() {
        return id_pessoa; 
    }

    public void setIdPessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getIdAnimal() {
        return id_animal;
    }

    public void setIdAnimal(int id_animal) {
        this.id_animal = id_animal;
    }

    public boolean isFamiliaCiente() { 
        return familia_ciente;
    }

    public void setFamiliaCiente(boolean familia_ciente) { 
        this.familia_ciente = familia_ciente;
    }

    public boolean isTeveAnimal() { 
        return teve_animal;
    }

    public void setTeveAnimal(boolean teve_animal) { 
        this.teve_animal = teve_animal;
    }

    public String getAnimalSozinho() {
        return animal_sozinho;
    }

    public void setAnimalSozinho(String animal_sozinho) {
        this.animal_sozinho = animal_sozinho;
    }

    public boolean isPermissao() { 
        return permissao;
    }

    public void setPermissao(boolean permissao) { 
        this.permissao = permissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isApLiberado() { 
        return ap_liberado;
    }

    public void setApLiberado(boolean ap_liberado) { 
        this.ap_liberado = ap_liberado;
    }


    @Override
    public String toString() {
        return "Formulario [id=" + id_formulario + 
               ", animal_sozinho=" + animal_sozinho + 
               ", familia_ciente=" + familia_ciente + 
               ", permissao=" + permissao + 
               ", teve_animal=" + teve_animal + 
               ", id_animal=" + id_animal + 
               ", id_pessoa=" + id_pessoa +
               ", telefone=" + telefone +
               ", ap_liberado=" + ap_liberado + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;
        
        Formulario other = (Formulario) obj;
        return this.getIdFormulario() == other.getIdFormulario();
    }
}