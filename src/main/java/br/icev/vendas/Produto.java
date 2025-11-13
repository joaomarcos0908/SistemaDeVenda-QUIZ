package br.icev.vendas;

import java.math.BigDecimal;

public class Produto {
    private final String codigo;
    private final String nome;
    private final BigDecimal precoUnitario;

    public Produto(String codigo, String nome, BigDecimal precoUnitario) {
        if (precoUnitario == null) {
            throw new NullPointerException("O preço não pode ser nulo.");
        }
        if (codigo == null || codigo.trim().isEmpty() ||
                nome == null || nome.trim().isEmpty() ||
                precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Dados inválidos para o produto");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}


