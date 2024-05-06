package entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    ArrayList<ContaBancaria> contaBancaria;
    Scanner sc;

    public Banco() {
        contaBancaria = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public void menu() {
        int escolhaMenu;

        do {
            System.out.println("Bem vindo ao Banco do Urubu");
            System.out.println();
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Criar uma conta");
            System.out.println("2. Gerenciar sua conta");
            System.out.println("3. Mostrar informações de todas as contas");
            System.out.println("4. Mostrar informações detalhadas de uma conta");
            System.out.println("5. Calcular rendimento das contas");
            System.out.println("6. Excluir sua conta");
            System.out.println("0. Finalizar");

            escolhaMenu = sc.nextInt();
            sc.nextLine();

            switch (escolhaMenu) {
                case 1:
                    System.out.println("--------------------------------------------------------------------------------");
                    adicionarConta();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("--------------------------------------------------------------------------------");
                    gerenciarConta();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("--------------------------------------------------------------------------------");
                    mostrarInformacoesDeTodasAsContas();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("--------------------------------------------------------------------------------");
                    mostrarInformacoesDeUmaConta();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 5:
                    System.out.println("--------------------------------------------------------------------------------");
                    calcularRendimentoParaTodasAsContas();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 6:
                    System.out.println("--------------------------------------------------------------------------------");
                    excluirConta();
                    System.out.println("--------------------------------------------------------------------------------");
                    break;
                case 0: 
                    System.out.println("Obrigado por utilizar o Banco do Urubu. Volte Sempre!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida");
                    break;
            }
            
        } while (escolhaMenu != 0);
    }

    public void adicionarConta() {
        System.out.println("Insira o seu nome");
        String nome = sc.nextLine();
        System.out.println("Deseja inserir algum valor na conta? (1 para sim, 2 para não)");
        int escolhaSaldo = sc.nextInt();
        sc.nextLine();

        BigDecimal saldoInicial = BigDecimal.ZERO;

        if (escolhaSaldo == 1) {
            System.out.println("Insira o valor inicial");
            saldoInicial = sc.nextBigDecimal();
            sc.nextLine();
        }

        System.out.println("Escolha o tipo da conta (1 para conta corrente, 2 para conta poupança)");
        int escolhaConta = sc.nextInt();
        sc.nextLine();

        if (escolhaConta == 1) {
            ContaCorrente novaContaCorrente = new ContaCorrente(contaBancaria.size() + 1, saldoInicial, nome);
            contaBancaria.add(novaContaCorrente);
            System.out.println("Conta corrente criada com sucesso!");
        } else if (escolhaConta == 2){
            ContaPoupanca novaContaPoupanca =  new ContaPoupanca(contaBancaria.size() + 1, saldoInicial, nome);
            contaBancaria.add(novaContaPoupanca);
            System.out.println("Conta poupança criada com sucesso!");
        } else {
            System.out.println("Opção inválida. Não foi possível criar a conta.");
        }
    }

    public void excluirConta() {
        System.out.println("Digite o número da conta que deseja excluir:");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        boolean contaEncontrada = false;

        for (ContaBancaria conta : contaBancaria) {
            if (conta.getContaID() == numeroConta) {
                contaBancaria.remove(conta);
                System.out.println("Conta removida com sucesso!");
                contaEncontrada = true;
                break;
            }
        }

        if (!contaEncontrada) {
            System.out.println("Conta não encontrada. Nenhuma conta foi removida.");
        }
    }

    public void gerenciarConta() {
        System.out.println("O que deseja fazer?");
        System.out.println("1. Realizar um depósito");
        System.out.println("2. Realizar um saque");
        int escolhaGerenciar = sc.nextInt();
        sc.nextLine();

        switch (escolhaGerenciar) {
            case 1:
                depositoDaConta();
                break;
            case 2:
                saqueDaConta();
                break;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida");
                break;
        }
    }

    public void mostrarInformacoesDeUmaConta() {
        System.out.println("Insira o número da conta que deseja ver:");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        boolean contaEncontrada = false;

        for (ContaBancaria conta : contaBancaria) {
            if (conta.getContaID() == numeroConta) {
                contaEncontrada = true;
                System.out.println("=== Informações da Conta ===");
                System.out.println("Numero da Conta: " + conta.getContaID());
                System.out.println("Titular da Conta: " + conta.getTitular());
                System.out.println("Saldo da Conta: " + conta.getSaldo());
                if (conta instanceof ContaCorrente) {
                    System.out.println("Tipo da Conta: Conta Corrente");
                } else if (conta instanceof ContaPoupanca) {
                    System.out.println("Tipo da Conta: Conta Poupança");
                }
                break;
            }
        }
        
        if (!contaEncontrada) {
            System.out.println("Conta não encontrada.");
        }
    }

    public void mostrarInformacoesDeTodasAsContas() {
        for (ContaBancaria conta : contaBancaria) {
            System.out.println("Número da conta " + conta.getContaID());
            System.out.println("Titular da conta: " + conta.getTitular());
            System.out.println();
        }
    }

    public void saqueDaConta() {
        System.out.println("Insira o número da conta que deseja realizar o saque:");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        boolean contaEncontrada = false;

        for (ContaBancaria conta : contaBancaria) {
            if (conta.getContaID() == numeroConta) {
                contaEncontrada = true;
                System.out.println("Insira o valor que deseja sacar:");
                BigDecimal valorSaque = sc.nextBigDecimal();
                sc.nextLine();
                
                if (valorSaque.compareTo(conta.getSaldo()) <= 0) {
                    conta.saque(valorSaque);
                } else {
                    System.out.println("Saldo insuficiente para realizar o saque.");
                }
                break;
            }
        }
        
        if (!contaEncontrada) {
            System.out.println("Conta não encontrada.");
        }
    }

    public void depositoDaConta() {
        System.out.println("Insira o número da conta que deseja realizar o deposito:");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        boolean contaEncontrada = false;

        for (ContaBancaria conta : contaBancaria) {
            if (conta.getContaID() == numeroConta) {
                contaEncontrada = true;
                System.out.println("Insira o valor que deseja depositar:");
                BigDecimal valorDeposito = sc.nextBigDecimal();
                sc.nextLine();
                conta.deposito(valorDeposito);
                break;
            }
        }
        
        if (!contaEncontrada) {
            System.out.println("Conta não encontrada.");
        }
    }

    public void calcularRendimentoParaTodasAsContas() {
        for (ContaBancaria conta : contaBancaria) {
            if (conta instanceof ContaCorrente) {
                ((ContaCorrente) conta).calcularRendimento();
            } else if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).calcularRendimento();
            }
        }
        System.out.println("Rendimentos calculados com sucesso para todas as contas");
    }
}
