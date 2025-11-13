package br.icev.vendas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class UtilDinheiro {
    private UtilDinheiro() { }  // impede instância

    public static BigDecimal arredondar(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo.");
        }
        return valor.setScale(2, RoundingMode.HALF_UP);
    }


    }

