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
        // TODO Auto-generated method stub

    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nConta número " + conta.getNumero() + " foi cadastrada com sucesso!");
    }

    @Override
    public void deletar(int numero) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

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
}
