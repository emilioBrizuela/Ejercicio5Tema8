import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        Formulario f = new Formulario();
        // SelectorDeArchivos f = new SelectorDeArchivos();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 250);
        f.setVisible(true);
    }
}