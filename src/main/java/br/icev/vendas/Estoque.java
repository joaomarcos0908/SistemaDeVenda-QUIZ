package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;

import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private final Map<String, Integer> produtos = new HashMap<>();

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade deve ser maior que zero.");
        }
        int atual = produtos.getOrDefault(codigo, 0);
        produtos.put(codigo, atual + quantidade);
    }

    public int getDisponivel(String codigo) {
        return produtos.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade) throws SemEstoqueException, QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade deve ser maior que zero.");
        }
        int disponivel = produtos.getOrDefault(codigo, 0);
        if (disponivel < quantidade) {
            throw new SemEstoqueException("Estoque insuficiente para o produto " + codigo);
        }
        produtos.put(codigo, disponivel - quantidade);
    }
    }

