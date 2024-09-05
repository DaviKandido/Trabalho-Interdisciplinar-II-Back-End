package model;

public class Animal {
	
	private int id;
	private String url;
	private String  nome;
	private char sexo;
	private String  idade;	
	private String  raca;
	private String  vacinas;
	private String  cidade;
	private boolean cadastrado;
	private String historia;
	private String  tags;
	private char porte;
	private String especie;

	
	//---------Construtor Padrão----------//
	public Animal(){
		id = -1;
		url = "";
		nome = "";
		sexo = ' ';
		idade = "";
		raca = "";
		vacinas = "";
		cidade = "";
		cadastrado = false;
		historia = "";
		tags = "";
		porte = ' ';
		especie = "";
	}

	//---------Construtor----------//
	public Animal(int id, String url, String nome, char sexo, String idade, String raca, String vacinas, String cidade, boolean cadastrado, String historia,String tags, char porte,String especie){
		this.id = id;
		this.url = url;
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.raca = raca;
		this.vacinas = vacinas;
		this.cidade = cidade;
		this.cadastrado = cadastrado;
		this.historia = historia;
		this.tags = tags;
		this.porte = porte;
		this.especie = especie;
	}

	//---------ID----------//
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	//---------URL----------//
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//---------NOME----------//
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//---------SEXO----------//
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	//---------IDADE----------//
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	//---------RAÇA----------//
	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	//---------VACINAS----------//
	public String getVacinas() {
		return vacinas;
	}

	public void setVacinas(String vacinas) {
		this.vacinas = vacinas;
	}


	//---------Cidade----------//
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	//---------CADASTRADO----------//
	public boolean getCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	//---------HISTÓRIA----------//
	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	//---------TAGS----------//
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	//---------PORTE----------//
	public char getPorte() {
		return porte;
	}

	public void setPorte(char porte) {
		this.porte = porte;
	}

	//---------ESPÉCIE----------//
	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "\nANIMAL [ID: " + id +
			   "\nURL: " + url +
			   "\nNome: " + nome +
			   "\nSexo: " + sexo +
			   "\nIdade: " + idade +
			   "\nRaça: " + raca +
			   "\nVacinas: " + vacinas +
			   "\nCidade: " + cidade +
			   "\nCadastrado: " + (cadastrado ? "Sim" : "Não") +
			   "\nHistória: " + historia +
			   "\nTags: " + tags +
			   "\nPorte: " + porte +
			   "\nEspécie: " + especie + "]\n";
	}


	@Override
	public boolean equals(Object obj){
		return (this.getId() == ((Animal) obj).getId());
	}

}
