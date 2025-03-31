package conta.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import conta.util.Cores;

public abstract class Conta {
    private int numero;
    private int agencia;
    private TipoDaConta tipo;
    private String titular;
    private float saldo;

    public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.tipo = TipoDaConta.pegarTipo(tipo);
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public TipoDaConta getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = TipoDaConta.pegarTipo(tipo);
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean sacar(float valor) {
        if (this.getSaldo() < valor) {
            System.out.println("\nSaldo Insuficiente!");
            return false;
        }
        this.setSaldo(this.getSaldo() - valor);
        return true;
    }

    public void depositar(float saldo) {
        this.setSaldo(this.getSaldo() + saldo);
    }

    public void visualizar() {
        System.out.println(
                Cores.TEXT_RED + "\n\n***********************************************************"
                        + Cores.TEXT_RESET);
        System.out.println("Dados da Conta:");
        System.out.println(Cores.TEXT_RED
                + "***********************************************************" + Cores.TEXT_RESET);
        System.out.println("Numero da Conta: " + this.numero);
        System.out.println("Agência: " + this.agencia);
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Titular: " + this.titular);
        System.out.println("Saldo: " + this.saldo);
    }

    private enum TipoDaConta {
        CONTACORRENTE(1, "Conta Corrente"), CONTAPOUPANCA(2, "Conta Poupança");

        private final String descricao;
        private final int codigo;
        private static final Map<Integer, TipoDaConta> MAP = new HashMap<>();

        static {
            for (var tipo : values())
                MAP.put(tipo.codigo, tipo);
        }

        TipoDaConta(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public static TipoDaConta pegarTipo(int codigo) {
            return Optional.ofNullable(MAP.get(codigo))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Código inválido para o tipo da conta " + codigo));
        }

        @Override
        public String toString() {
            return descricao;
        }


    }
}
