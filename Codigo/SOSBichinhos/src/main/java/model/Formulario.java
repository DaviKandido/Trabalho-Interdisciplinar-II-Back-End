package model;

public class Formulario {
	
    private int id;
    private String nome;
    private int idade;
    private char sexo;
    private String cidade;
    private String ap_liberado;
    private String ciente;
    private String teve_animal;
    private String permissao;
    private String animal_sozinho;
    private String aonde_fica;
    private String telefone;
    private String email;
    private String nome_animal;
    private String imagem_animal;
    private String moradia;

    public Formulario() {
        this.id = -1;
        this.nome = "";
        this.idade = 0;
        this.sexo = '*';
        this.cidade = "";
        this.ap_liberado = "";
        this.ciente = "";
        this.teve_animal = "";
        this.permissao = "";
        this.animal_sozinho = "";
        this.aonde_fica = "";
        this.telefone = "";
        this.email = "";
        this.nome_animal = "";
        this.imagem_animal = "";
        this.moradia = "";
    }

    public Formulario(int id, String nome, int idade, char sexo, String cidade, String ap_liberado, 
            		  String ciente, String teve_animal, String permissao, String animal_sozinho, 
            		  String aonde_fica, String telefone, String email, String nome_animal, 
            		  String imagem_animal, String moradia) {
		setId(id);
		setNome(nome);
		setIdade(idade);
		setSexo(sexo);
		setCidade(cidade);
		setApLiberado(ap_liberado);
		setCiente(ciente);
		setTeveAnimal(teve_animal);
		setPermissao(permissao); 
		setAnimalSozinho(animal_sozinho);
		setAondeFica(aonde_fica);
		setTelefone(telefone);
		setEmail(email);
		setNomeAnimal(nome_animal);
		setUrlImagem(imagem_animal); 
		setMoradia(moradia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoradia() {
        return moradia;
    }

    public void setMoradia(String moradia) {
        this.moradia = moradia;
    }

    public String getCiente() {
        return ciente;
    }

    public void setCiente(String ciente) {
        this.ciente = ciente;
    }

    public String getApLiberado() {
        return ap_liberado;
    }

    public void setApLiberado(String ap_liberado) {
        this.ap_liberado = ap_liberado;
    }

    public String getTeveAnimal() {
        return teve_animal;
    }

    public void setTeveAnimal(String teve_animal) {
        this.teve_animal = teve_animal;
    }

    public String getAondeFica() {
        return aonde_fica;
    }

    public void setAondeFica(String aonde_fica) {
        this.aonde_fica = aonde_fica;
    }

    public String getAnimalSozinho() {
        return animal_sozinho;
    }

    public void setAnimalSozinho(String animal_sozinho) {
        this.animal_sozinho = animal_sozinho;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getNomeAnimal() {
        return nome_animal;
    }

    public void setNomeAnimal(String nome_animal) {
        this.nome_animal = nome_animal;
    }

    public String getUrlImagem() {
    	return imagem_animal;
    }
    
    public void setUrlImagem(String imagem_animal) {
        this.imagem_animal = imagem_animal;
    }
    
    @Override
    public String toString() {
    	 return "Formulario [id=" + id + 
    	           ", nome=" + nome + 
    	           ", idade=" + idade + 
    	           ", sexo=" + sexo + 
    	           ", cidade=" + cidade + 
    	           ", ciente=" + ciente + 
    	           ", teve_animal=" + teve_animal + 
    	           ", permissao=" + permissao + 
                   ", animal_sozinho=" + animal_sozinho +
    	           ", ap_liberado=" + ap_liberado + 
    	           ", aonde_fica=" + aonde_fica + 
    	           ", telefone=" + telefone + 
    	           ", email=" + email + 
    	           ", nome_animal=" + nome_animal + 
    	           ", imagem_animal=" + imagem_animal + 
    	           ", moradia=" + moradia + "]";
    }

    @Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Formulario) obj).getId());
	}
}