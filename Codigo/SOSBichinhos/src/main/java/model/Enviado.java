package model;

public class Enviado{
    private int id_enviado;
    private int id_pessoa;
    private String imagem;
    private char sexo;
    private String especie;
    private String raca;
    private String idade;
    private String porte;
    private String temperamento;
    private String necessidades_especiais;
    private String estado_de_saude;
    private String caracteristicas_gerais;

    public Enviado() {
        this.id_enviado = -1;
        this.id_pessoa = -1;
        this.imagem = "";
        this.sexo = ' ';
        this.especie = "";
        this.raca = "";
        this.idade = "";
        this.porte = "";
        this.temperamento = "";
        this.necessidades_especiais = "";
        this.estado_de_saude = "";
        this.caracteristicas_gerais = "";
    }



    public Enviado(int id_enviado, int id_pessoa, String imagem, char sexo, String especie, String raca, String idade, String porte, String temperamento, String necessidades_especiais, String estado_de_saude,String caracteristicas_gerais) {

        this.id_enviado = id_enviado;
        this.id_pessoa = id_pessoa;
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
    }

    

}