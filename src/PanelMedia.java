import javax.swing.*;
import java.awt.*;

public class PanelMedia extends JPanel {

    private String[] tMedia = { "MEDIA 1", "MEDIA 2", "MEDIA 3" };
    private JTextField mediaText1;
    private JTextField mediaText2;
    private JTextField mediaText3;

    private JLabel error;
    private JLabel medias;

    public PanelMedia() {

        setLayout(null);
        int y = 0;

        for (String titulo : tMedia) {
            medias = new JLabel();
            medias.setBounds(100, y, 200, 20);
            medias.setText(titulo);
            add(medias);
            y += 30;
        }

        mediaText1 = new JTextField();
        mediaText2 = new JTextField();
        mediaText3 = new JTextField();

        mediaText1.setBounds(200, 0, 200, 20);
        mediaText2.setBounds(200, 30, 200, 20);
        mediaText3.setBounds(200, 60, 200, 20);

        mediaText1.setToolTipText("Media primera fila, si no contiene números enteros redondeará el resultado");
        mediaText2.setToolTipText("Media segunda fila, muestra valor en reales ");
        mediaText3.setToolTipText("Media tercera fila, muestra valor en reales");

        add(mediaText1);
        add(mediaText2);
        add(mediaText3);

        mediaText1.setEditable(false);
        mediaText2.setEditable(false);
        mediaText3.setEditable(false);

        error = new JLabel("Los archivos deben contener números, el formato es: Entero, Reales, Reales");
        error.setBounds(50, 100, 500, 20);
        add(error);
    }

    /**
     * Instancia el valor de error
     * 
     * @param t Texto del error
     */
    public void setError(String t) {
        error.setForeground(Color.RED);
        this.error.setText(t);
    }

    /**
     * Instancia la media de la primera fila
     * 
     * @param t media
     */
    public void setMediaText1(String t) {
        this.mediaText1.setText(t);
    }

    /**
     * Instancia la media de la segunda fila
     * 
     * @param t media
     */

    public void setMediaText2(String t) {
        this.mediaText2.setText(t);
    }

    /**
     * Instancia la media de la tercera fila
     * 
     * @param t media
     */
    public void setMediaText3(String t) {
        this.mediaText3.setText(t);
    }
}