package conta;

import java.util.Scanner;

import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
    public static void main(String[] args) {

        // Teste da Classe Conta Corrente
        ContaCorrente cc1 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f, 1000.0f);
        cc1.visualizar();
        cc1.sacar(12000.0f);
        cc1.visualizar();
        cc1.depositar(5000.0f);
        cc1.visualizar();

        // Teste da Classe Conta Poupança
        ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
        cp1.visualizar();
        cp1.sacar(1000.0f);
        cp1.visualizar();
        cp1.depositar(5000.0f);
        cp1.visualizar();

        Scanner leia = new Scanner(System.in);
        int opcao;
        String mensagem = """
                \n*****************************************************************


                                BANCO DO BRAZIL COM Z


                *****************************************************************

                                1 - Criar Conta
                                2 - Listar todas as Contas
                                3 - Buscar Conta por Numero
                                4 - Atualizar Dados da Conta
                                5 - Apagar Conta
                                6 - Sacar
                                7 - Depositar
                                8 - Transferir valores entre Contas
                                9 - Sair

                *****************************************************************
                Entre com a opção desejada:""";

        while (true) {

            System.out.print(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + mensagem + " ");
            System.out.print(Cores.TEXT_RESET);
            opcao = leia.nextInt();
            leia.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta \n\n");
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
                    break;
                case 3:
                    System.out.println(
                            Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
                    break;

                default:
                    if (opcao < 1 || opcao > 9) {
                        System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n");
                        break;
                    }
                    System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                    sobre();
                    leia.close();
                    System.exit(0);
            }
        }
    }

    public static void sobre() {
        String mensagemSobre = """
                \n**************************************************************************
                    Projeto Desenvolvido por: Leonardo de Paula
                    leofernandes9@gmail.com
                    https://github.com/leodipaula
                **************************************************************************
                """;
        System.out.println(mensagemSobre);
    }
}
