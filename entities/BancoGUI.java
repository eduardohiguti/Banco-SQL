package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BancoGUI {
    private ArrayList<ContaBancaria> contaBancaria;
    public JFrame frame;

    public BancoGUI() {
        contaBancaria = new ArrayList<>();
        frame = new JFrame("Banco do Urubu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        showMainMenu();
    }

    public void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(7, 1));

        JLabel label = new JLabel("Bem vindo ao Banco do Urubu", JLabel.CENTER);
        frame.add(label);

        JButton criarContaButton = new JButton("Criar uma conta");
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdicionarConta();
            }
        });
        frame.add(criarContaButton);

        JButton gerenciarContaButton = new JButton("Gerenciar sua conta");
        gerenciarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGerenciarConta();
            }
        });
        frame.add(gerenciarContaButton);

        JButton mostrarInfoTodasContasButton = new JButton("Mostrar informações de todas as contas");
        mostrarInfoTodasContasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacoesDeTodasAsContas();
            }
        });
        frame.add(mostrarInfoTodasContasButton);

        JButton mostrarInfoUmaContaButton = new JButton("Mostrar informações detalhadas de uma conta");
        mostrarInfoUmaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInformacoesDeUmaConta();
            }
        });
        frame.add(mostrarInfoUmaContaButton);

        JButton calcularRendimentoButton = new JButton("Calcular rendimento das contas");
        calcularRendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularRendimentoParaTodasAsContas();
            }
        });
        frame.add(calcularRendimentoButton);

        JButton excluirContaButton = new JButton("Excluir sua conta");
        excluirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExcluirConta();
            }
        });
        frame.add(excluirContaButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showAdicionarConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(5, 1));

        JLabel nomeLabel = new JLabel("Insira o seu nome:");
        JTextField nomeField = new JTextField();
        frame.add(nomeLabel);
        frame.add(nomeField);

        JLabel saldoLabel = new JLabel("Deseja inserir algum valor na conta? (Sim/Não)");
        JTextField saldoField = new JTextField();
        frame.add(saldoLabel);
        frame.add(saldoField);

        JLabel tipoLabel = new JLabel("Escolha o tipo da conta (1 para conta corrente, 2 para conta poupança):");
        JTextField tipoField = new JTextField();
        frame.add(tipoLabel);
        frame.add(tipoField);

        JButton criarButton = new JButton("Criar conta");
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String saldoInput = saldoField.getText().toLowerCase();
                BigDecimal saldoInicial = BigDecimal.ZERO;

                if (saldoInput.equals("sim")) {
                    String valorInicialStr = JOptionPane.showInputDialog("Insira o valor inicial:");
                    saldoInicial = new BigDecimal(valorInicialStr);
                }

                int escolhaConta = Integer.parseInt(tipoField.getText());

                if (escolhaConta == 1) {
                    ContaCorrente novaContaCorrente = new ContaCorrente(contaBancaria.size() + 1, saldoInicial, nome);
                    contaBancaria.add(novaContaCorrente);
                    JOptionPane.showMessageDialog(frame, "Conta corrente criada com sucesso!");
                } else if (escolhaConta == 2){
                    ContaPoupanca novaContaPoupanca =  new ContaPoupanca(contaBancaria.size() + 1, saldoInicial, nome);
                    contaBancaria.add(novaContaPoupanca);
                    JOptionPane.showMessageDialog(frame, "Conta poupança criada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Opção inválida. Não foi possível criar a conta.");
                }

                showMainMenu();
            }
        });
        frame.add(criarButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showExcluirConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));

        JLabel numeroContaLabel = new JLabel("Digite o número da conta que deseja excluir:");
        JTextField numeroContaField = new JTextField();
        frame.add(numeroContaLabel);
        frame.add(numeroContaField);

        JButton excluirButton = new JButton("Excluir conta");
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(numeroContaField.getText());

                boolean contaEncontrada = false;

                for (ContaBancaria conta : contaBancaria) {
                    if (conta.getContaID() == numeroConta) {
                        contaBancaria.remove(conta);
                        JOptionPane.showMessageDialog(frame, "Conta removida com sucesso!");
                        contaEncontrada = true;
                        break;
                    }
                }

                if (!contaEncontrada) {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada. Nenhuma conta foi removida.");
                }

                showMainMenu();
            }
        });
        frame.add(excluirButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showGerenciarConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("O que deseja fazer?", JLabel.CENTER);
        frame.add(label);

        JButton depositoButton = new JButton("Realizar um depósito");
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepositoDaConta();
            }
        });
        frame.add(depositoButton);

        JButton saqueButton = new JButton("Realizar um saque");
        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSaqueDaConta();
            }
        });
        frame.add(saqueButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showDepositoDaConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));

        JLabel numeroContaLabel = new JLabel("Insira o número da conta que deseja realizar o depósito:");
        JTextField numeroContaField = new JTextField();
        frame.add(numeroContaLabel);
        frame.add(numeroContaField);

        JButton depositoButton = new JButton("Depositar");
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(numeroContaField.getText());

                boolean contaEncontrada = false;

                for (ContaBancaria conta : contaBancaria) {
                    if (conta.getContaID() == numeroConta) {
                        contaEncontrada = true;
                        String valorDepositoStr = JOptionPane.showInputDialog("Insira o valor que deseja depositar:");
                        BigDecimal valorDeposito = new BigDecimal(valorDepositoStr);
                        conta.deposito(valorDeposito);
                        break;
                    }
                }

                if (!contaEncontrada) {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                }

                showMainMenu();
            }
        });
        frame.add(depositoButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showSaqueDaConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));

        JLabel numeroContaLabel = new JLabel("Insira o número da conta que deseja realizar o saque:");
        JTextField numeroContaField = new JTextField();
        frame.add(numeroContaLabel);
        frame.add(numeroContaField);

        JButton saqueButton = new JButton("Sacar");
        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(numeroContaField.getText());

                boolean contaEncontrada = false;

                for (ContaBancaria conta : contaBancaria) {
                    if (conta.getContaID() == numeroConta) {
                        contaEncontrada = true;
                        String valorSaqueStr = JOptionPane.showInputDialog("Insira o valor que deseja sacar:");
                        BigDecimal valorSaque = new BigDecimal(valorSaqueStr);

                        if (valorSaque.compareTo(conta.getSaldo()) <= 0) {
                            conta.saque(valorSaque);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Saldo insuficiente para realizar o saque.");
                        }
                        break;
                    }
                }

                if (!contaEncontrada) {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                }

                showMainMenu();
            }
        });
        frame.add(saqueButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showInformacoesDeUmaConta() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));

        JLabel numeroContaLabel = new JLabel("Insira o número da conta que deseja ver:");
        JTextField numeroContaField = new JTextField();
        frame.add(numeroContaLabel);
        frame.add(numeroContaField);

        JButton mostrarButton = new JButton("Mostrar informações");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(numeroContaField.getText());

                boolean contaEncontrada = false;

                for (ContaBancaria conta : contaBancaria) {
                    if (conta.getContaID() == numeroConta) {
                        contaEncontrada = true;
                        String info = "=== Informações da Conta ===\n" +
                                      "Numero da Conta: " + conta.getContaID() + "\n" +
                                      "Titular da Conta: " + conta.getTitular() + "\n" +
                                      "Saldo da Conta: " + conta.getSaldo() + "\n" +
                                      "Tipo da Conta: " + (conta instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança");
                        JOptionPane.showMessageDialog(frame, info);
                        break;
                    }
                }

                if (!contaEncontrada) {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                }

                showMainMenu();
            }
        });
        frame.add(mostrarButton);

        frame.revalidate();
        frame.repaint();
    }

    public void mostrarInformacoesDeTodasAsContas() {
        StringBuilder info = new StringBuilder("=== Informações de Todas as Contas ===\n");

        for (ContaBancaria conta : contaBancaria) {
            info.append("Número da Conta: ").append(conta.getContaID()).append("\n");
            info.append("Titular da Conta: ").append(conta.getTitular()).append("\n\n");
        }

        JOptionPane.showMessageDialog(frame, info.toString());
        showMainMenu();
    }

    public void calcularRendimentoParaTodasAsContas() {
        for (ContaBancaria conta : contaBancaria) {
            if (conta instanceof ContaCorrente) {
                ((ContaCorrente) conta).calcularRendimento();
            } else if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).calcularRendimento();
            }
        }
        JOptionPane.showMessageDialog(frame, "Rendimentos calculados com sucesso para todas as contas");
        showMainMenu();
    }
}
