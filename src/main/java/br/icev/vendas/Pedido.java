package br.icev.vendas;

import br.icev.vendas.excecoes.ErroPagamentoException;

import java.math.BigDecimal;
import java.util.Map;

public class Pedido {
    public enum Status { PAGO }

    private final Map<String, Integer> itensPorCodigo;
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) throws ErroPagamentoException {
        if (itensPorCodigo == null || itensPorCodigo.isEmpty()) {
            throw new ErroPagamentoException("Itens do pedido não podem ser nulos ou vazios.");
        }
        if (totalPago == null || totalPago.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ErroPagamentoException("Total pago deve ser maior que zero.");
        }
        if (codigoAutorizacao == null || codigoAutorizacao.trim().isEmpty()) {
            throw new ErroPagamentoException("Código de autorização é obrigatório.");
        }
        if (status == null) {
            throw new ErroPagamentoException("Status não pode ser nulo.");
        }

        this.itensPorCodigo = Map.copyOf(itensPorCodigo); // imutável
        this.totalPago = totalPago;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public Status getStatus() {
        return status;
    }

    public int getQuantidadeItem(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("Código do produto não pode ser nulo.");
        }
        return itensPorCodigo.getOrDefault(codigo, 0);
    }


}

