package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
    public static void main(String[] args) {
        ContaController contas = new ContaController();
        Scanner leia = new Scanner(System.in);

        int opcao = 0, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        System.out.println("\nCriar Contas\n");

        ContaCorrente cc1 =
                new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
        contas.cadastrar(cc1);
        ContaCorrente cc2 =
                new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
        contas.cadastrar(cc2);
        ContaPoupanca cp1 =
                new ContaPoupanca(contas.gerarNumero(), 125, 2, "Maria dos Santos", 4000f, 12);
        contas.cadastrar(cp1);
        ContaPoupanca cp2 =
                new ContaPoupanca(contas.gerarNumero(), 125, 2, "João da Silva", 1000f, 15);
        contas.cadastrar(cp2);

        contas.listarTodas();
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
            try {
                opcao = 0;
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nDigite Valores inteiros!");
            } finally {
                leia.nextLine();
            }


            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta \n\n");

                    System.out.println("Digite o Numero da Agência: ");
                    agencia = leia.nextInt();
                    System.out.println("Digite o Nome do Titular: ");
                    leia.skip("\\R?");
                    titular = leia.nextLine();

                    do {
                        System.out.println("Digite o Tipo da Conta (1: CC ou 2: CP): ");
                        tipo = leia.nextInt();
                        leia.nextLine();
                    } while (tipo < 1 && tipo > 2);

                    System.out.print("\nDigite o Saldo da Conta (R$): ");
                    saldo = leia.nextFloat();

                    switch (tipo) {
                        case 1 -> {
                            System.out.println("Digite o Limite de Crédito (R$): ");
                            limite = leia.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo,
                                    titular, saldo, limite));
                        }
                        case 2 -> {
                            System.out.println("Digite o dia do Aniversário da Conta: ");
                            aniversario = leia.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo,
                                    titular, saldo, aniversario));
                        }
                    }
                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(
                            Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();
                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    var buscaConta = contas.buscarNaCollection(numero);

                    if (buscaConta != null) {

                        System.out.println("Digite o Numero da Agência: ");
                        agencia = leia.nextInt();
                        System.out.println("Digite o Nome do Titular: ");
                        leia.skip("\\R?");
                        titular = leia.nextLine();

                        System.out.println("Digite o Saldo da Conta (R$): ");
                        saldo = leia.nextFloat();

                        tipo = buscaConta.getTipo();

                        switch (tipo) {
                            case 1 -> {
                                System.out.println("Digite o Limite de Crédito (R$): ");
                                limite = leia.nextFloat();
                                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular,
                                        saldo, limite));
                            }
                            case 2 -> {
                                System.out.println("Digite o dia do Aniversario da Conta: ");
                                aniversario = leia.nextInt();
                                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular,
                                        saldo, aniversario));
                            }
                            default -> System.out.println("Tipo de conta inválido!");
                        }

                    } else
                        System.out.println("\nConta não encontrada!");

                    keyPress();
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
                    System.out.println("Digite o número da Conta: ");
                    numero = leia.nextInt();
                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");

                    System.out.println("Digite o Numero da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o Valor do Saque (R$): ");
                        valor = leia.nextFloat();
                        if (valor <= 0) {
                            System.out.println("O valor do Saque precisa ser maior que R$ 0,00");
                            continue;
                        }
                    } while (valor <= 0);

                    contas.sacar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o Valor do Depósito (R$): ");
                        valor = leia.nextFloat();
                        if (valor <= 0) {
                            System.out.println("O valor do depósito precisa ser maior que R$ 0,00");
                            continue;
                        }
                    } while (valor <= 0);

                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");

                    System.out.println("Digite o Numero da Conta de Origem:");
                    numero = leia.nextInt();
                    System.out.println("Digite o Número da Conta de Destino:");
                    numeroDestino = leia.nextInt();

                    do {
                        System.out.println("Digite o Valor da Transferência (R$): ");
                        valor = leia.nextFloat();
                        if (valor <= 0) {
                            System.out.println(
                                    "O valor da transferência precisa ser maior que R$ 0,00");
                            return;
                        }
                    } while (valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);

                    keyPress();
                    break;

                default:
                    if (opcao < 1 || opcao > 9) {
                        System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n");
                        keyPress();
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

    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter: " + e.getCause());
        }
    }
}
