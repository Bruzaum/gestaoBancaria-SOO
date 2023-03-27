package gestaoBancaria.SOO;


public class Conta extends Cliente {
    private int numero;
    private String senha;
    private double[] saldo;
    private boolean status;
    //private Cliente cliente;

    private String log = "";

    public Conta(String cpf, String nome, String telefone, String email, int idade, int numeroConta, String senha) {
        super(cpf, nome, telefone, email, idade, numeroConta);
    
    this.saldo = new double[2];    
    
    this.senha = senha;
    this.saldo[0] = 0; //Corrente
    this.saldo[1] = 0; //Poupança
    this.status = true;
    }

    //public Conta(String cpf, String nome, String telefone, String email, int idade, int numeroConta) {
    //    this.numero = numeroConta;
    //    //this.cliente = cliente;
    //    this.senha = senha;
    //    this.saldo = 0;
    //    this.status = true;
    //}
    

    public int numero() {
        return this.numero;
    }

    public double sacar(double valor) {
        //CONTA INATIVA
        if (!this.status) {
            this.log += " SAQUE NEGADO - CONTA DESATIVADA\n";
            return -1;
        }

        //CONTA ATIVA
        if (this.saldo[0] >= valor) {
            this.saldo[0] -= valor;
            this.log += " SAQUE REALIZADO - " + valor + "\n";
            return valor;
        }
        return 0;
    }

    public double depositar(double valor) {
        //CONTA INATIVA
        if (!this.status) {
            this.log += " DEPOSITO NEGADO - CONTA DESATIVADA\n";
            return -1;
        }

        //CONTA ATIVA
        this.saldo[0] += valor;
        this.log += " DEPOSITO REALIZADO - " + valor + "\n";
        return this.saldo[0];
    }

    public double saldo() {
        return this.saldo[0];
    }

    public void changeStatus() {
        if (this.status == true) {
            this.log += " CONTA DESATIVADA\n";
            this.status = false;
        } else {
            this.log += " CONTA ATIVADA\n";
            this.status = true;
        }
    }

    // RETORNAR UM VALOR BOOLEAN PARA VERIFICAR NO MAIN
    public String statusConta() {
        if (this.status == true) {
            return "Desativar Conta";
        } else {
            return "Ativar Conta";
        }
    }

    public void setSenha(String senha) {
        this.log += " NOVA SENHA INSERIDA\n";
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public String log() {
        return this.log;
    }
    
    public double getCorrente() {
        return this.saldo[0];
    }
    
    public double getPoupanca() {
        return this.saldo[1];
    }
}

