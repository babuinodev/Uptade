package view;

import javax.swing.SwingUtilities;

import dao.DaoCarroBD;

public class TestInterfaceGrafica {
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica ui = new InterfaceGrafica(new DaoCarroBD());
            ui.setVisible(true);
        });
    }

}
