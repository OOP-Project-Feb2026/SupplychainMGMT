package app;

import gui.LoginJF;

public class Main {
    public static void main(String[] args) {
        // Use the built-in Java Swing thread for thread safety
        java.awt.EventQueue.invokeLater(() -> {
            new LoginJF().setVisible(true);
        });
    }
}
