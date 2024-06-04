package application;

import javax.swing.SwingUtilities;
import entities.BancoGUI;

public class Program {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BancoGUI().frame.setVisible(true);
            }
        });
    }
}
