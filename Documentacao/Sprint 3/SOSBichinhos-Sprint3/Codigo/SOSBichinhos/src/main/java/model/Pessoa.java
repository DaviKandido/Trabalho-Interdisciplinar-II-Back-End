package model;


public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String moradia;
    private String imagem;
    private int idade;
    private String sexo;
    
    // Construtor
    public Pessoa(int id, String nome, String email, String senha, String moradia, String imagem, int idade, String sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.moradia = moradia;
        this.imagem = imagem;
        this.idade = idade;
        this.sexo = sexo;
    }

    // Getters e Setters
    public String getMoradia() { return moradia; }
    public void setMoradia(String moradia) { this.moradia = moradia; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

	
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
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "\nUsuario: " + nome + 
				"\nE-mail: " + email + 
				"\nSenha: " + senha;
				
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Pessoa) obj).getId());
	}	
}