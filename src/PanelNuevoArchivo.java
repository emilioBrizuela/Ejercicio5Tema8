import java.awt.*;

import javax.swing.*;

public class PanelNuevoArchivo extends JPanel {
    private JLabel cantFilas;
    private JTextField eCantFilas;
    private JLabel tNombreArchivo;
    private JTextField nombreArchivo;
    private JLabel error;

    public PanelNuevoArchivo() {
        setLayout(new FlowLayout());
        setSize(10,250);
        cantFilas = new JLabel("Cantidad de Filas");
        eCantFilas = new JTextField(10);
        eCantFilas.setToolTipText("Cantidad de filas que generará aleatoriamente en el archivo");
        
        tNombreArchivo = new JLabel("Nombre del Archivo");
        nombreArchivo = new JTextField(20);
        
        error = new JLabel();
        add(tNombreArchivo);
        add(nombreArchivo);

        add(cantFilas);
        add(eCantFilas);
        add(error);
    }

    /**
     * Devuelve el contenido de eCantFilas
     * @return cantidad de filas
     */
    public int getCantFilas() {
        return Integer.parseInt(eCantFilas.getText());
    }

    /**
     * Instancia el error
     */
    public void mostrarError(String texto){
        error.setForeground(Color.RED);
        error.setText(texto);
    }

    /**
     * Devuelve contenido de nombreArchivo
     * @return nombre del archivo
     */
    public String getNombreArchivo(){
        return nombreArchivo.getText();
    }
}