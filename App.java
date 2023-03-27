package gestaoBancaria.SOO;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String... args) {

        Scanner SC = new Scanner(System.in);
        ArrayList<Conta> clientesCriados = new ArrayList<>();
        

        String opt, age, cpf, senha;
        int numeroConta, idade;
        double saldoTemp;
        boolean validador = true;


        String [] dados;
        String [] novosDados;
        Banco BS = new Banco("Itau");

        while(validador){
          Menu.MENU_PRINCIPAL(BS.nome);
            opt = SC.next();
            switch (opt) {
                case "1":
                    dados = new String[7];
                    dados = Menu.CRIAR_CONTA();
                    idade = Integer.parseInt(dados[5]);
                    numeroConta = Integer.parseInt(dados[6]);
                    clientesCriados.add(new Conta(dados[1], dados[0], dados[2], dados[3], idade, numeroConta, dados[4]));
                    break;

                case "2":
                    if( clientesCriados.size() < 1 ){
                        System.out.println("Nenhum cliente cadastrado ate o momento!");
                        System.out.println();
                    } else {
                        dados = new String[2];
                        dados = Menu.ACESSAR_CONTA();
                        numeroConta = Integer.parseInt(dados[0]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta != conta.getNumeroConta()){
                                System.out.println("Cliente nao encontrado");
                            } else{
                                if (dados[1].equals(conta.getSenha())){
                                    System.out.println();
                                    System.out.println("Nome: " + conta.getNome());
                                    System.out.println("CPF: " + conta.getCpf());
                                    System.out.println("Idade: " + conta.getIdade());
                                    System.out.println("Telefone: " + conta.getTelefone());
                                    System.out.println("Email: " + conta.getEmail());
                                    System.out.println("Saldo Conta Corrente: " + conta.getCorrente());
                                    System.out.println("Saldo Conta Poupanca: " + conta.getPoupanca());
                                    System.out.println();                                 
                                }else {
                                    System.out.println("Senha Incorreta");
                                }
                            }
                        }            
                    }
                    
 
                    break;

                case "3":
                    if( clientesCriados.size() < 1 ){
                        System.out.println("Nenhum cliente cadastrado ate o momento!");
                        System.out.println();
                    } else {
                        dados = new String[3];
                        dados = Menu.DEPOSITAR();
                        numeroConta = Integer.parseInt(dados[1]);
                        int tipoConta = Integer.parseInt(dados[0]);
                        var valor = Double.parseDouble(dados[2]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta != conta.getNumeroConta()){
                                System.out.println();
                                System.out.println("Cliente nao encontrado");
                                System.out.println();
                            } else{
                                if (tipoConta == 1){
                                    saldoTemp = conta.getCorrente() + valor;
                                    conta.setCorrente(saldoTemp);
                                } else {
                                    saldoTemp = conta.getPoupanca()+ valor;
                                    conta.setPoupanca(saldoTemp);
                                }                             
                            }
                        }            
                    }
                    break;
                    
                case "4":
                    if( clientesCriados.size() < 1 ){
                        System.out.println("Nenhum cliente cadastrado ate o momento!");
                        System.out.println();
                    } else {
                        dados = new String[3];
                        dados = Menu.SACAR();
                        numeroConta = Integer.parseInt(dados[1]);
                        int tipoConta = Integer.parseInt(dados[0]);
                        var valor = Double.parseDouble(dados[2]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta != conta.getNumeroConta()){
                                System.out.println();
                                System.out.println("Cliente nao encontrado");
                                System.out.println();
                            } else{
                                if (tipoConta == 1){
                                    if (valor > conta.getCorrente()){
                                        System.out.println();
                                        System.out.println("Saldo Indisponivel");
                                        System.out.println();
                                        break;
                                    } else{
                                        saldoTemp = conta.getCorrente() - valor;
                                        conta.setCorrente(saldoTemp);
                                        break;
                                    }

                                } else {
                                    System.out.println();
                                    System.out.println("Nao eh possivel sacar dinheiro da Poupanca.");
                                    System.out.println();
                                }
                            }                             
                        }
                    }
                    break;
                    
                case "5":
                    //Atualizar dados da minha conta;
                    if( clientesCriados.size() < 1 ){
                        System.out.println("Nenhum cliente cadastrado ate o momento!");
                        System.out.println();
                    } else {
                        dados = new String[2];
                        dados = Menu.ACESSAR_CONTA();
                        numeroConta = Integer.parseInt(dados[0]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta != conta.getNumeroConta()){
                                System.out.println();
                                System.out.println("Cliente nao encontrado");
                                System.out.println();
                            } else{
                                if (dados[1].equals(conta.getSenha())){
                                    novosDados = new String[7];
                                    novosDados = Menu.ALTERAR_CONTA();
                                    conta.setNome(novosDados[0]);
                                    conta.setIdade(Integer.parseInt(novosDados[5]));
                                    conta.setCpf(novosDados[1]);
                                    conta.setTelefone(novosDados[2]);
                                    conta.setEmail(novosDados[3]);
                                    conta.setSenha(novosDados[4]);
                                    System.out.println();                                 
                                }else {
                                    System.out.println("Senha Incorreta");
                                    System.out.println();
                                }
                            }
                        }            
                    }
                    break;

                case "6":
                    //Finalizar uma conta;
                    if( clientesCriados.size() < 1 ){
                        System.out.println("Nenhum cliente cadastrado ate o momento!");
                        System.out.println();
                    } else {
                        dados = new String[2];
                        dados = Menu.ACESSAR_CONTA();
                        numeroConta = Integer.parseInt(dados[0]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta == conta.getNumeroConta()){
                                if (dados[1].equals(conta.getSenha())){
                                    clientesCriados.remove(conta);
                                    System.out.println("Conta removida com sucesso!");                                 
                                }else {
                                    System.out.println();
                                    System.out.println("Senha Incorreta");
                                    System.out.println();
                                }
                            } else{
                                System.out.println();
                                System.out.println("Cliente nao encontrado");
                                System.out.println();
                            }
                        }            
                    }
                    
                    break; 
                    
                case "11":
                    //Teste para ver membros criados no objeto
                    System.out.print(clientesCriados.size());
                    System.out.println();
                    for (Conta cliente : clientesCriados) {
                        System.out.println(cliente.getNumeroConta());
                        System.out.println(cliente.getNome());
                        System.out.println();
                    }
                    break;
                    
                case "0":
                    Menu.BYE();
                    validador = false;
                    break;

                default:
                    break;
            }
        }
    }
}


