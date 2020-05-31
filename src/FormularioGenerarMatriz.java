import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioGenerarMatriz extends JDialog {

    private JLabel lblFilas;
    private JTextField txfFilas;
    private JButton btnCrear;

    public FormularioGenerarMatriz(Formulario f) {
        super(f, true);
        setTitle("Cantidad de Filas");
        setLayout(new FlowLayout());

        lblFilas = new JLabel("Cantidad de Filas:");
        add(lblFilas);

        txfFilas = new JTextField(5);
        txfFilas.setToolTipText("Cantidad de filas que generará aleatoriamente en el archivo");
        add(txfFilas);

        btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }
            
        });
        add(btnCrear);
    }

    public int cantidadFilas(){
        int n=0;
        try {
            n = Integer.parseInt(txfFilas.getText());
        } catch (NumberFormatException e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "Solo puede ingresar números","Error",JOptionPane.ERROR_MESSAGE);
        }
        return n;
    }
}