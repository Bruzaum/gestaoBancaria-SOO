package gestaoBancaria.SOO;


public class Conta extends Cliente {
    private int numero;
    private String senha;
    private double[] saldo;


    public Conta(String cpf, String nome, String telefone, String email, int idade, int numeroConta, String senha) {
        super(cpf, nome, telefone, email, idade, numeroConta);
    
    this.saldo = new double[2];    
    
    this.senha = senha;
    this.saldo[0] = 0; //Corrente
    this.saldo[1] = 0; //Poupança
    }

    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public double getCorrente() {
        return this.saldo[0];
    }
    
    public void setCorrente(double saldo) {
        this.saldo[0] = saldo;
    }    
    
    public double getPoupanca() {
        return this.saldo[1];
    }
    
    public void setPoupanca(double saldo) {
        this.saldo[1] = saldo;
    }  
}
