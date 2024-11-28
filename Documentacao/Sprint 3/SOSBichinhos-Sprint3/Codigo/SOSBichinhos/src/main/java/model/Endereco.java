package model;

public class Endereco {
    private int id_endereco;
    private String bairro;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private int id_pessoa;

    // Construtor
    public Endereco(int id_endereco, String bairro, String rua, String numero, String cidade, String estado, int id_pessoa) {
        this.id_endereco = id_endereco;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.id_pessoa = id_pessoa;
    }

    // Getters e Setters
    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    // Método sobreposto da classe Object. Exibe o objeto como String.
    @Override
    public String toString() {
        return "Endereco: " + rua + ", " + numero + " - " + bairro + ", " + cidade + ", " + estado + 
               "\nID Endereco: " + id_endereco + "\nID Pessoa: " + id_pessoa;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getId_endereco() == ((Endereco) obj).getId_endereco());
    }
}
