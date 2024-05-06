package entities;

import java.math.BigDecimal;

public class ContaBancaria {
    protected int contaID;
    protected BigDecimal saldo;
    protected String titular;

    public ContaBancaria(int contaID, String titular) {
        this.contaID = contaID;
        this.titular = titular;
        this.saldo = BigDecimal.ZERO;
    }

    public ContaBancaria(int contaID, BigDecimal saldo, String titular) {
        this.contaID = contaID;
        this.saldo = saldo;
        this.titular = titular;
    }

    public void deposito(BigDecimal montante) {
        if (montante.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(montante);
            System.out.println("Deposito de R$ " + montante + " feito. Seu saldo agora é " + saldo);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void saque(BigDecimal montante) {
        if (montante.compareTo(BigDecimal.ZERO) > 0 && saldo.compareTo(montante) >= 0) {
            saldo = saldo.subtract(montante);
            System.out.println("Saque de R$ " + montante + " feito. Seu saldo agora é " + saldo);
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

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }
}

class ContaCorrente extends ContaBancaria {
    public ContaCorrente(int contaID, String titular) {
        super(contaID, titular);
    }

    public ContaCorrente(int contaID, BigDecimal saldo, String titular) {
        super(contaID, saldo, titular);
    }

    public void calcularRendimento() {
        BigDecimal juros = new BigDecimal("10.00");
        saldo = saldo.add(juros);
        System.out.println("Juros adicionados ao saldo da conta corrente.");
    }
}

class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(int contaID, String titular) {
        super(contaID, titular);
    }

    public ContaPoupanca(int contaID, BigDecimal saldo, String titular) {
        super(contaID, saldo, titular);
    }

    public void calcularRendimento() {
        BigDecimal taxaDeJurosAnual = new BigDecimal("0.03");
        BigDecimal juros = getSaldo().multiply(taxaDeJurosAnual);
        saldo = saldo.add(juros);
        System.out.println("Juros calculados e adicionados ao saldo da poupança.");
    }
}