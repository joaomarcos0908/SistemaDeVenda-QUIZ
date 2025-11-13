package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {

        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade deve ser maior que zero.");
        }
        int quantidadeAtual = itens.getOrDefault(produto, 0);
        itens.put(produto, quantidadeAtual + quantidade);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Map.Entry<Produto, Integer> entrada : itens.entrySet()) {
            Produto produto = entrada.getKey();
            int quantidade = entrada.getValue();
            subtotal = subtotal.add(produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade)));
        }
        return subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        BigDecimal subtotal = getSubtotal();
        if (politica == null) {
            return UtilDinheiro.arredondar(subtotal);
        }
        BigDecimal total = politica.aplicar(subtotal);
        BigDecimal arredondado = UtilDinheiro.arredondar(total);
        return arredondado.compareTo(BigDecimal.ZERO) < 0
                ? UtilDinheiro.arredondar(BigDecimal.ZERO)
                : arredondado;
    }

    public int getTotalItens() {
        int total = 0;
        for (int quantidade : itens.values()) {
            total += quantidade;
        }
        return total;
    }
}

