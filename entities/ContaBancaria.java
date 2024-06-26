package entities;

import java.math.BigDecimal;

public class ContaBancaria {
    protected int contaID;
    protected BigDecimal saldo;
    protected String titular;

    public ContaBancaria(int contaID, BigDecimal saldo, String titular) {
        this.contaID = contaID;
        this.saldo = saldo;
        this.titular = titular;
    }

    public void deposito(BigDecimal montante) {
        if (montante.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(montante);
            System.out.println("Deposito de R$ " + montante + " feito. Seu saldo agora é R$" + saldo);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void saque(BigDecimal montante) {
        if (montante.compareTo(BigDecimal.ZERO) > 0 && saldo.compareTo(montante) >= 0) {
            saldo = saldo.subtract(montante);
            System.out.println("Saque de R$ " + montante + " feito. Seu saldo agora é R$" + saldo);
        } else {
            System.out.println("Valor de saque inválido ou fundos insuficientes.");
        }
    }

    public int getContaID() {
        return contaID;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}

class ContaCorrente extends ContaBancaria {
    public ContaCorrente(int contaID, BigDecimal saldo, String titular) {
        super(contaID, saldo, titular);
    }

    public void calcularRendimento() {
        BigDecimal juros = new BigDecimal("10.00");
        saldo = saldo.add(juros);
    }
}

class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(int contaID, BigDecimal saldo, String titular) {
        super(contaID, saldo, titular);
    }

    public void calcularRendimento() {
        final BigDecimal TAXA_DE_JUROS_ANUAL = new BigDecimal("0.03");
        BigDecimal juros = getSaldo().multiply(TAXA_DE_JUROS_ANUAL);
        saldo = saldo.add(juros);
    }
}