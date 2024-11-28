
package model;

public class Enviado{
    private int id_enviado;
    private String imagem;
    private char sexo;
    private String especie;
    private String raca;
    private String idade;
    private char porte;
    private String temperamento;
    private String necessidades_especiais;
    private String estado_de_saude;
    private String caracteristicas_gerais;
    private String localizacao;
    private int id_pessoa;

    public Enviado() {

        this.id_enviado = -1;
        this.imagem = "";
        this.sexo = ' ';
        this.especie = "";
        this.raca = "";
        this.idade = "";
        this.porte = ' ';
        this.temperamento = "";
        this.necessidades_especiais = "";
        this.estado_de_saude = "";
        this.caracteristicas_gerais = "";
        this.localizacao = "";
        this.id_pessoa = -1;
    }



    public Enviado(int id_enviado, String imagem, char sexo, String especie, String raca, String idade, char porte, String temperamento, String necessidades_especiais, String estado_de_saude,String caracteristicas_gerais,String localizacao, int id_pessoa) {

        this.id_enviado = id_enviado;
        this.imagem = imagem;
        this.sexo = sexo;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.temperamento = temperamento;
        this.necessidades_especiais = necessidades_especiais;
        this.estado_de_saude = estado_de_saude;
        this.caracteristicas_gerais = caracteristicas_gerais;
        this.localizacao = localizacao;
        this.id_pessoa = id_pessoa;
    }

    public int getId_enviado() {
        return id_enviado;
    }

    public void setId_enviado(int id_enviado) {
        this.id_enviado = id_enviado;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public char getPorte() {
        return porte;
    }

    public void setPorte(char porte) {
        this.porte = porte;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getNecessidades_especiais() {
        return necessidades_especiais;
    }

    public void setNecessidades_especiais(String necessidades_especiais) {
        this.necessidades_especiais = necessidades_especiais;
    }

    public String getEstado_de_saude() {
        return estado_de_saude;
    }

    public void setEstado_de_saude(String estado_de_saude) {
        this.estado_de_saude = estado_de_saude;
    }

    public String getCaracteristicas_gerais() {
        return caracteristicas_gerais;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setCaracteristicas_gerais(String caracteristicas_gerais) {
        this.caracteristicas_gerais = caracteristicas_gerais;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "\nEnviado [id_enviado=" + id_enviado +
                "\nid_pessoa=" + id_pessoa +
                "\nimagem=" + imagem +
                "\nsexo=" + sexo +
                "\nespecie=" + especie +
                "\nraca=" + raca + 
                "\nidade=" + idade + 
                "\nporte=" + porte+  
                "\ntemperamento=" + temperamento +  
                "\nnecessidades_especiais=" + necessidades_especiais+  
                "\nestado_de_saude=" + estado_de_saude +  
                "\ncaracteristicas_gerais=" + caracteristicas_gerais + 
                 "\nlocalizacao=" + localizacao + "]";
    }

    @Override
	public boolean equals(Object obj){
		return (this.getId_enviado() == ((Enviado) obj).getId_enviado());
	}

}
