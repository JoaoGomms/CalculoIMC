package example.model;

public class Pessoas {

    private String uid;
    private String nome;
    private int idade;
    private double peso;
    private String sexo;


    public Pessoas(){

    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Pessoas(String uid, String sexo , String nome, int idade, double peso, double altura) {
        this.uid = uid;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.sexo = sexo;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Pessoas{" +
                "uid='" + uid + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                ", sexo='" + sexo + '\'' +
                ", altura=" + altura +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    private double altura;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }


}
