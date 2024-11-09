package model;

public class Animal {
	
	private int id_animal;
	private String imagem;
	private String  nome;
	private char sexo;
	private String idade;	
	private String raca;
	private String vacinas;
	private boolean castrado;
	private String historia;
	private char porte;
	private String especie;

	
	//---------Construtor Padrão----------//
	public Animal(){
		id_animal = -1;
		imagem = "";
		nome = "";
		sexo = ' ';
		idade = "";
		raca = "";
		vacinas = "";
		castrado = false;
		historia = "";
		porte = ' ';
		especie = "";
	}

	//---------Construtor----------//
	public Animal(int id_animal, String imagem, String nome, char sexo, String idade, String raca, String vacinas, boolean castrado, String historia, char porte,String especie){
		this.id_animal = id_animal;
		this.imagem = imagem;
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.raca = raca;
		this.vacinas = vacinas;
		this.castrado = castrado;
		this.historia = historia;
		this.porte = porte;
		this.especie = especie;
	}

	//---------ID----------//
	public int getId(){
		return id_animal;
	}

	public void setId(int id_animal){
		this.id_animal = id_animal;
	}

	//---------imagem----------//
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {

		this.imagem = imagem;
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


	//---------castrado----------//
	public boolean getCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	//---------HISTÓRIA----------//
	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
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
		return "\nANIMAL [ID: " + id_animal +
			   "\nimagem: " + imagem +
			   "\nNome: " + nome +
			   "\nSexo: " + sexo +
			   "\nIdade: " + idade +
			   "\nRaça: " + raca +
			   "\nVacinas: " + vacinas +
			   "\ncastrado: " + (castrado ? "Sim" : "Não") +
			   "\nHistória: " + historia +
			   "\nPorte: " + porte +
			   "\nEspécie: " + especie + "]\n";
	}


	@Override
	public boolean equals(Object obj){
		return (this.getId() == ((Animal) obj).getId());
	}

}
