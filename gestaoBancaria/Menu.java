package gestaoBancaria.SOO;

import java.util.Scanner;

public class Menu {
    
    public static int numeroConta = 1;

    public static Scanner SC = new Scanner(System.in);

    public static void MENU_PRINCIPAL(String nomeBanco) {
        String MENU = "";
        MENU += "Bem-Vindo(a) ao Banco " + nomeBanco + "!\n\nComo posso te ajudar?\n\n";
        MENU += "[1] - Criar conta\n";
        MENU += "[2] - Acessar Conta\n";
        MENU += "[3] - Deposito\n";
        MENU += "[4] - Saque\n";
        MENU += "[5] - Atualizar dados da minha conta\n";
        MENU += "[6] - Finalizar uma conta\n\n";
        MENU += "[0] - Sair do sistema\n:";
        System.out.print(MENU);
    }

    public static String[] CRIAR_CONTA() {

        String[] values = new String[7];
        String tmp = null;
        boolean check = true;
        

        System.out.print("Para criarmos sua conta precisamos das seguintes informacoes;\n");
        System.out.print("Seu nome: ");
        values[0] = Menu.SC.next();
        
        System.out.print("Idade: ");
        values[5] = Menu.SC.next();

        System.out.print("CPF: ");
        values[1] = Menu.SC.next();

        System.out.print("Telefone: ");
        values[2] = Menu.SC.next();

        System.out.print("Email: ");
        values[3] = Menu.SC.next();

        while (check) {
            System.out.print("Senha: ");
            values[4] = Menu.SC.next();

            System.out.print("Por favor, confirme a senha: ");
            tmp = Menu.SC.next();

            if (!values[4].equals(tmp)) {
                System.out.print("As senhas nao sao iguais! Por favor, reescreva as senhas.\n");
            } else {
                check = false;
            }
        }
        values[6] = String.valueOf(numeroConta);
        System.out.println();
        System.out.println("Conta criada com sucesso!\nO numero da sua conta eh: " + numeroConta);
        System.out.println();
        
        numeroConta++;
        
        return values;
    }

        public static String[] ALTERAR_CONTA() {

        String[] values = new String[7];

        System.out.print("As seguintes informaçoes ser serao alteradas:;\n");
        System.out.print("Seu nome: ");
        values[0] = Menu.SC.next();
        
        System.out.print("Idade: ");
        values[5] = Menu.SC.next();

        System.out.print("CPF: ");
        values[1] = Menu.SC.next();

        System.out.print("Telefone: ");
        values[2] = Menu.SC.next();

        System.out.print("Email: ");
        values[3] = Menu.SC.next();
        
        System.out.print("Senha: ");
        values[4] = Menu.SC.next();
        
        return values;
    }
    
    public static String[] ACESSAR_CONTA() {
        String[] values = new String[2];

        System.out.print("Informe os seguintes dados para acessar sua conta.\n\n");
        System.out.print("Numero da conta: ");
        values[0] = Menu.SC.next();
        System.out.print("Senha de acesso: ");
        values[1] = Menu.SC.next();

        return values;
    }

    public static String[] DEPOSITAR() {
        boolean check = true;
        
        System.out.print("Informe os seguintes dados para o deposito.\n\n");
        String[] values = new String[3];
        
        System.out.print("Numero da conta: ");
        values[1] = Menu.SC.next();

        System.out.print("[1] - Corrente\n");
        System.out.print("[2] - Poupanca\n");
        System.out.print("Tipo da conta: ");
        values[0] = Menu.SC.next();
        
        while (check) {
            System.out.print("Informe o valor do deposito: ");
            values[2] = Menu.SC.next();
            var valor = Double.parseDouble(values[2]);

            if (valor < 0.00) {
                System.out.print("Inserir somente valores positivos\n");
            }else {
                check = false;
            }
        }
        return values;
    }
    
    public static String[] SACAR() {
        boolean check = true;
        
        System.out.print("Informe os seguintes dados para o saque.\n\n");
        String[] values = new String[3];
        
        System.out.print("Numero da conta: ");
        values[1] = Menu.SC.next();

        System.out.print("[1] - Corrente\n");
        System.out.print("[2] - Poupanca\n");
        System.out.print("Tipo da conta: ");
        values[0] = Menu.SC.next();
        
        while (check) {
            System.out.print("Informe o valor do saque: ");
            values[2] = Menu.SC.next();
            var valor = Double.parseDouble(values[2]);

            if (valor < 0.00) {
                System.out.print("Inserir somente valores positivos\n");
            }else {
                check = false;
            }
        }
        return values;
    }    

    public static void BYE() {
        System.out.println();
        System.out.print("Obrigado por utilizar nosso Banco!\n");
        System.out.println();
    }
}
