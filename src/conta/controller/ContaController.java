package conta.controller;

import java.util.ArrayList;
import java.util.List;
import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
    private List<Conta> listaContas = new ArrayList<>();
    int numero = 0;

    @Override
    public void atualizar(Conta conta) {
        var buscaConta = buscarNaCollection(conta.getNumero());

        if (buscaConta != null) {
            listaContas.set(listaContas.indexOf(buscaConta), conta);
            System.out.println(
                    "\nA Conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
            return;
        }
        System.out.println("\nA Conta número: " + conta.getNumero() + " não foi encontrada!");
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nConta número " + conta.getNumero() + " foi cadastrada com sucesso!");
    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null) {
            if (listaContas.remove(conta))
                System.out.println("\nA Conta número: " + numero + " foi deletada com sucesso!");
            return;
        }
        System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
    }

    @Override
    public void depositar(int numero, float valor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas)
            conta.visualizar();
    }

    @Override
    public void procurarPorNumero(int numero) {
        var contaProcurada = buscarNaCollection(numero);

        if (contaProcurada != null) {
            contaProcurada.visualizar();
            return;
        }
        System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
    }

    @Override
    public void sacar(int numero, float valor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferir(int numeroOrigin, int numeroDestino, float valor) {
        // TODO Auto-generated method stub

    }

    public int gerarNumero() {
        return ++numero;
    }

    public Conta buscarNaCollection(int numero) {
        var contaProcurada = listaContas.stream().filter(conta -> conta.getNumero() == numero)
                .findAny().orElse(null);
        return contaProcurada;
    }
}
