import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Formulario extends JFrame implements ActionListener {

    private JButton btnBuscarArchivo;
    private JButton btnCrearArchivo;
    private File ruta;
    private PanelMedia panel;

    private Double[][] matriz;

    public Formulario() {

        setTitle("Ejercicio 5");
        setLayout(new BorderLayout(20, 20));

        panel = new PanelMedia();
        panel.setVisible(false);
        add(panel, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout());
        add(botones, BorderLayout.SOUTH);

        // botones.add(error,BorderLayout.NORTH);

        btnBuscarArchivo = new JButton("Abrir Archivo");
        // btnBuscarArchivo.setBounds(150, 120, 200, 20);
        btnBuscarArchivo.addActionListener(this);
        botones.add(btnBuscarArchivo);

        btnCrearArchivo = new JButton("Crear Archivo");
        // btnBuscarArchivo.setBounds(150, 120, 200, 20);
        btnCrearArchivo.addActionListener(this);
        botones.add(btnCrearArchivo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int respuesta;
        JFileChooser fc;
        if (e.getSource() == btnBuscarArchivo) {
            // panel1.setVisible(false);
            panel.setVisible(true);
            if (panel.isVisible()) {

                FileNameExtensionFilter texto = new FileNameExtensionFilter("texto", "txt");

                fc = new JFileChooser();
                fc.addChoosableFileFilter(texto);
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                respuesta = fc.showOpenDialog(this);

                if (respuesta == JFileChooser.APPROVE_OPTION) {

                    ruta = fc.getSelectedFile();
                    if (extraerMatrizDeNum()) {

                        if (fc.getFileFilter() == texto) {

                            try {
                                comprobarPrimeraFila(matriz);
                            } catch (ExcepcionEntero er) {
                                // TODO Auto-generated catch block
                                panel.setError(er.getMessage());

                                for (int i = 0; i < matriz.length; i++) {
                                    matriz[i][0] = (double) 0;
                                }
                            }
                            panel.setMediaText1(media(matriz, 0) + "");
                            panel.setMediaText2(media(matriz, 1) + "");
                            panel.setMediaText3(media(matriz, 2) + "");

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "El archivo que desea abrir no coincide con el formato permitido");
                        }
                    }
                }
            }
        }

        if (e.getSource() == btnCrearArchivo) {

            int n;
            fc = new JFileChooser();

            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

                ruta = fc.getSelectedFile();
                System.err.println(ruta);
                FormularioGenerarMatriz f = new FormularioGenerarMatriz(this);
                f.pack();
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                n = f.cantidadFilas();
                if (n != 0) {
                    generarMatriz(n);
                    escribirArchivo();
                }
            }

        }

    }

    /**
     * Escrbe los valores de la matriz en el archivo creado.
     */
    public void escribirArchivo() {
        try {
            PrintWriter escritura = new PrintWriter(new FileWriter(ruta + ".txt", true));
            for (Double[] valores : matriz) {
                String texto = "";
                for (int i = 0; i < valores.length; i++) {
                    if ((i + 1) % 3 == 0) {
                        texto += valores[i];
                    } else {
                        texto += valores[i] + ",";
                    }

                }
                escritura.printf("%s%n", texto);
            }
            escritura.close();
        } catch (IOException e) {
            System.err.println("Error de acceso al archivo");
        }

    }

    /**
     * Genera matriz según la cantidad de filas que desea el usuario
     * 
     * @param n cantidad de filas
     */
    public void generarMatriz(int n) {
        matriz = new Double[n][3];

        for (int i = 0; i < n; i++) {
            matriz[i][0] = (double) ((int) (Math.random() * 90) + 10);
            matriz[i][1] = (Math.random() * 1000);
            matriz[i][2] = (Math.random() * 9900) + 100;
        }

        // for (int i = 0; i < matriz.length; i++) {
        // System.out.println();
        // for (int j = 0; j < matriz[i].length; j++) {
        // System.out.print(matriz[i][j] + " ");

        // }
        // }
    }

    /**
     * Comprueba si la primera fila son números enteros
     * 
     * @param matriz matriz
     * @throws ExcepcionEntero excepción de números enteros
     */
    public void comprobarPrimeraFila(Double[][] matriz) throws ExcepcionEntero {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] % 1 != 0) {
                throw new ExcepcionEntero("La primera fila deben ser números ENTEROS");
            }
        }
    }

    /**
     * Separa el contenido del archivo en una matriz de n filas por 3 columnas
     * 
     * @return matriz
     */

    private Boolean extraerMatrizDeNum() {
        panel.setMediaText1("");
        panel.setMediaText2("");
        panel.setMediaText3("");
        String textoArchivo = leerArchivo();
        Boolean matrizCreada = true;
        // System.err.println(textoArchivo);
        String[] elementos = textoArchivo.split(",");

        for (String elemento : elementos) {
            if (!elemento.matches("[\\d.]+")) {
                matrizCreada = false;
                panel.setError("El archivo contiene texto");
            }
        }

        matriz = new Double[elementos.length / 3][3];
        int k = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                try {
                    matriz[i][j] = Double.parseDouble(elementos[k]);
                    k++;
                } catch (NumberFormatException er) {
                    panel.setError("El archivo contiene texto");
                }
            }
        }
        System.err.println(matrizCreada);
        return matrizCreada;
    }

    /**
     * Lee el contenido del archivo
     * 
     * @return contenido del archivo
     */
    public String leerArchivo() {
        String txt = "";
        try (Scanner sc = new Scanner(ruta)) {
            while (sc.hasNext()) {
                txt += sc.nextLine() + ",";
            }
        } catch (IOException e) {
            System.err.println("Error de acceso al archivo: " + e.getMessage());
        }
        return txt;
    }

    /**
     * Calcula la media de una columna
     * 
     * @param matriz matriz de números
     * @param a      número de columna
     * @return media
     */

    public double media(Double[][] matriz, int a) {
        double n = 0;
        for (int i = 0; i < matriz.length; i++) {
            n += matriz[i][a];
        }
        return n / matriz.length;
    }
}