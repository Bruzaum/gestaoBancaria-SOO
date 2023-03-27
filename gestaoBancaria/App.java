package gestaoBancaria.SOO;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String... args) {

        Scanner SC = new Scanner(System.in);
        ArrayList<Conta> clientesCriados = new ArrayList<>();

        String opt, age, cpf, senha;
        int numeroConta, idade;
        boolean validador = true;


        String [] dados;
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
                        System.out.println("Nenhum cliente cadastrado!");
                        System.out.println();
                    } else {
                        dados = new String[2];
                        dados = Menu.ACESSAR_CONTA();
                        numeroConta = Integer.parseInt(dados[0]);

                        for (Conta conta : clientesCriados) {
                            if(numeroConta == conta.getNumeroConta()){
                                if (dados[1].equals(conta.getSenha())){
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
                            } else{
                                System.out.println("Cliente nao encontrado");
                            }
                        }            
                    }
                    
 
                    break;

                case "3":
                    dados = new String[3];
                    dados = Menu.DEPOSITAR();
                    break;
                
                case "4":
                    dados = new String[3];
                    dados = Menu.SACAR();
                    break;
                    
                case "5":
                    //Atualizar dados da minha conta;
                    
                    //Teste para ver membros criados no objeto
                    System.out.print(clientesCriados.size());
                    System.out.println();
                    for (Conta cliente : clientesCriados) {
                        System.out.println(cliente.getNumeroConta());
                        System.out.println(cliente.getNome());
                        System.out.println();
                    }
                    break;

                case "6":
                    //Finalizar uma conta; 
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


