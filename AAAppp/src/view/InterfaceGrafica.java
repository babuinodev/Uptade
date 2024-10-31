package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DaoFuncionario;
import dao.DaoCarro;
import entidade.Carro;
import entidade.Funcionario;

public class InterfaceGrafica extends JFrame {
    private DaoFuncionario funcionarioDAO;
    private DaoCarro carroDAO;

    Object[] nomeColunas = new Object[]  {"nome", "modelo", "ano", "preço"};
    DefaultTableModel dtm = new DefaultTableModel(nomeColunas, 0);

    Object[] nomeColunasFunc = new Object[]  {"nome", "salario"};
    DefaultTableModel dtmFun = new DefaultTableModel(nomeColunasFunc, 0);

    public InterfaceGrafica(DaoCarro daoCarro) {
        funcionarioDAO = new DaoFuncionario();
        carroDAO = daoCarro;

        setTitle("Sistema de Vendas de Carros");
        setSize(400, 400); // Aumentado para acomodar mais botões
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabPane = new JTabbedPane();
        add(tabPane);

        // Botões da interface funcionario
        JPanel painelFuncionario = new JPanel(new BorderLayout());
        JPanel botoesFuncionario = new JPanel(new FlowLayout());
        JTable tabelaFuncionario = new JTable(dtmFun);

        JScrollPane tabelaFuncionarioPane = new JScrollPane(tabelaFuncionario);
        painelFuncionario.add(botoesFuncionario, BorderLayout.PAGE_START);
        painelFuncionario.add(tabelaFuncionarioPane, BorderLayout.CENTER);

        JButton btnListarFuncionarios = new JButton("Listar Funcionários");
        JButton btnAdicionarFuncionario = new JButton("Adicionar Funcionário");
        JButton btnLimparFuncionario = new JButton("Limpar");
        botoesFuncionario.add(btnListarFuncionarios);
        botoesFuncionario.add(btnAdicionarFuncionario);
        botoesFuncionario.add(btnLimparFuncionario);

        // Botões da interface carros
        JPanel painelCarros = new JPanel(new BorderLayout());
        JPanel botoesCarrosPane = new JPanel(new FlowLayout());
        JTable tabelaCarros = new JTable(dtm);
        JScrollPane tabelaCarrosPane = new JScrollPane(tabelaCarros);

        painelCarros.add(botoesCarrosPane, BorderLayout.PAGE_START);
        painelCarros.add(tabelaCarrosPane, BorderLayout.CENTER);

        JButton btnListarCarros = new JButton("Listar Carros");
        JButton btnAdicionarCarro = new JButton("Registrar Venda de Carro");
        JButton btnLimparCarros = new JButton("Limpar");
        botoesCarrosPane.add(btnListarCarros);
        botoesCarrosPane.add(btnAdicionarCarro);
        botoesCarrosPane.add(btnLimparCarros);

        tabPane.add("Carros", painelCarros);
        tabPane.add("Funcionário", painelFuncionario);

        // Ação para listar todos os funcionários com todas as informações
        btnListarFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Funcionario> funcionarios = funcionarioDAO.loadAll();
                preencheFuncionarioTabela(funcionarios);
            }
        });

        // Ação para adicionar um novo funcionário
        btnAdicionarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = JOptionPane.showInputDialog("Digite o nome do funcionário:");
                    if (nome == null) return; // Cancelado

                    String salarioStr = JOptionPane.showInputDialog("Digite o salário do funcionário:");
                    if (salarioStr == null) return; // Cancelado

                    double salario = Double.parseDouble(salarioStr);
                    Funcionario funcionario = new Funcionario(0, nome, salario); // ID padrão como 0
                    funcionarioDAO.save(funcionario);
                    JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, insira um número válido para Salário.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        // Ação para listar todos os carros com todas as informações
        btnListarCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carro> carros = carroDAO.loadAll();
                preencheCarrosTabela(carros);
            }
        });

        // Ação para adicionar um novo carro vendido
        btnAdicionarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeCarro = JOptionPane.showInputDialog("Digite o nome do carro:");
                    if (nomeCarro == null) return; 
                    
                    String modelo = JOptionPane.showInputDialog("Digite o modelo do carro:");
                    if (modelo == null) return; 

                    String anoStr = JOptionPane.showInputDialog("Digite o ano do carro:");
                    if (anoStr == null) return; 

                    String precoStr = JOptionPane.showInputDialog("Digite o preço do carro:");
                    if (precoStr == null) return; 

                    int ano = Integer.parseInt(anoStr);
                    double preco = Double.parseDouble(precoStr);

                    Carro carro = new Carro(0, nomeCarro, modelo, ano, preco); // ID padrão como 0
                    carroDAO.save(carro); 
                    JOptionPane.showMessageDialog(null, "Carro vendido registrado com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, insira números válidos para Ano e Preço.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        // Ação para limpar a tabela de funcionários
        btnLimparFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtmFun.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Tabela de Funcionários limpa com sucesso!");
            }
        });

        // Ação para limpar a tabela de carros
        btnLimparCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Tabela de Carros limpa com sucesso!");
            }
        });
    }

    void preencheCarrosTabela(List<Carro> carros) {
        dtm.setRowCount(0);
        for (var c : carros) {
            Object[] row = new Object[] {c.getNomedocarro(), c.getModelo(), c.getAno(), c.getPreco()};
            dtm.addRow(row);
        }
    }

    void preencheFuncionarioTabela(List<Funcionario> funcionarios) {
        dtmFun.setRowCount(0);
        for (var f : funcionarios) {
            Object[] row = new Object[] {f.getnome(), f.getsalario()};
            dtmFun.addRow(row);
        }
    }
}

