import javax.swing.*;
import java.awt.*;

public class PanelMedia extends JPanel {

    private String[] tMedia = { "MEDIA 1", "MEDIA 2", "MEDIA 3" };
    private JTextField txfMedia1;
    private JTextField txfMedia2;
    private JTextField txfMedia3;

    private JLabel lblError;
    private JLabel lblMedia;

    public PanelMedia() {

        setLayout(null);
        int y = 0;

        for (String titulo : tMedia) {
            lblMedia = new JLabel();
            lblMedia.setBounds(100, y, 200, 20);
            lblMedia.setText(titulo);
            add(lblMedia);
            y += 30;
        }

        txfMedia1 = new JTextField();
        txfMedia2 = new JTextField();
        txfMedia3 = new JTextField();

        txfMedia1.setBounds(200, 0, 200, 20);
        txfMedia2.setBounds(200, 30, 200, 20);
        txfMedia3.setBounds(200, 60, 200, 20);

        txfMedia1.setToolTipText("Media primera fila, si no contiene números enteros redondeará el resultado");
        txfMedia2.setToolTipText("Media segunda fila, muestra valor en reales ");
        txfMedia3.setToolTipText("Media tercera fila, muestra valor en reales");

        add(txfMedia1);
        add(txfMedia2);
        add(txfMedia3);

        txfMedia1.setEditable(false);
        txfMedia2.setEditable(false);
        txfMedia3.setEditable(false);

        lblError = new JLabel("Los archivos deben contener números, el formato es: Entero, Reales, Reales");
        lblError.setBounds(50, 100, 500, 20);
        add(lblError);
    }

    /**
     * Instancia el valor de error
     * 
     * @param t Texto del error
     */
    public void setError(String t) {
        lblError.setForeground(Color.RED);
        this.lblError.setText(t);
    }

    public JLabel getLblError(){
        return this.lblError;
    }

    /**
     * Instancia la media de la primera fila
     * 
     * @param t media
     */
    public void setTxfMedia1(String t) {
        this.txfMedia1.setText(t);
    }

    /**
     * Instancia la media de la segunda fila
     * 
     * @param t media
     */

    public void setTxfMedia2(String t) {
        this.txfMedia2.setText(t);
    }

    /**
     * Instancia la media de la tercera fila
     * 
     * @param t media
     */
    public void setTxfMedia3(String t) {
        this.txfMedia3.setText(t);
    }
}