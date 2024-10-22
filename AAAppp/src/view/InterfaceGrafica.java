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
import javax.swing.SwingUtilities;
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
        JPanel painelFuncionario = new JPanel(new FlowLayout());
        JButton btnListarFuncionarios = new JButton("Listar Funcionários");
        JButton btnAdicionarFuncionario = new JButton("Adicionar Funcionário");
        painelFuncionario.add(btnListarFuncionarios);
        painelFuncionario.add(btnAdicionarFuncionario);
        
        
        
        // Botões da interface carros
        JPanel painelCarros = new JPanel(new BorderLayout());
        
        JPanel botoesCarrosPane = new JPanel(new FlowLayout());
        
        JTable tabelaCarros = new JTable(dtm);
        JScrollPane tabelaCarrosPane = new JScrollPane(tabelaCarros);
        
        painelCarros.add(botoesCarrosPane, BorderLayout.PAGE_START);
        painelCarros.add(tabelaCarrosPane, BorderLayout.CENTER);
        
        JButton btnListarCarros = new JButton("Listar Carros");
        JButton btnAdicionarCarro = new JButton("Registrar Venda de Carro");
        botoesCarrosPane.add(btnListarCarros);
        botoesCarrosPane.add(btnAdicionarCarro);
        
        tabPane.add("Carros", painelCarros);
        tabPane.add("Funcionario", painelFuncionario);

        // Ação para listar todos os funcionários com todas as informações
        btnListarFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Funcionario> funcionarios = funcionarioDAO.loadAll();
                StringBuilder sb = new StringBuilder();
                for (Funcionario f : funcionarios) {
                    sb.append("ID: ").append(f.getid())
                      .append(", Nome: ").append(f.getnome())
                      .append(", Salário: ").append(f.getsalario())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Funcionários", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação para adicionar um novo funcionário
        btnAdicionarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do funcionário:");
                String idStr = JOptionPane.showInputDialog("Digite o ID do funcionário:");
                String salarioStr = JOptionPane.showInputDialog("Digite o salário do funcionário:");
                int id = Integer.parseInt(idStr);
                double salario = Double.parseDouble(salarioStr);
                Funcionario funcionario = new Funcionario(id, nome, salario);
                funcionarioDAO.save(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
            }
        });

        // Ação para listar todos os carros com todas as informações
        btnListarCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carro> carros = carroDAO.loadAll();
                preencheCarrosTabela(carros);

//                StringBuilder sb = new StringBuilder();
//                for (Carro c : carros) {
//                    sb.append("ID: ").append(c.getid())
//                      .append(", Nome: ").append(c.getNomedocarro())
//                      .append(", Modelo: ").append(c.getModelo())
//                      .append(", Ano: ").append(c.getAno())
//                      .append(", Preço: ").append(c.getPreco())
//                      .append("\n");
//                }
//                JOptionPane.showMessageDialog(null, sb.toString(), "Carros", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação para adicionar um novo carro vendido
        btnAdicionarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCarro = JOptionPane.showInputDialog("Digite o nome do carro:");
                String modelo = JOptionPane.showInputDialog("Digite o modelo do carro:");
                String idStr = JOptionPane.showInputDialog("Digite o ID do carro:");
                String anoStr = JOptionPane.showInputDialog("Digite o ano do carro:");
                String precoStr = JOptionPane.showInputDialog("Digite o preço do carro:");
                
                int id = Integer.parseInt(idStr);
                int ano = Integer.parseInt(anoStr);
                double preco = Double.parseDouble(precoStr);
                
                Carro carro = new Carro(id, nomeCarro, modelo, ano, preco);
                carroDAO.save(carro); 
                JOptionPane.showMessageDialog(null, "Carro vendido registrado com sucesso!");
            }
        });
    }
    
    void preencheCarrosTabela(List<Carro> carros) {
    	dtm.setRowCount(0);
    	for(var c: carros) {
    		Object[] row = new Object[] {c.getNomedocarro(), c.getModelo(), c.getAno(), c.getPreco()};
    		dtm.addRow(row);
    	}
    }

}