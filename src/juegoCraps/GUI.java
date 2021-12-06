package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps Class
 * @autor Sheilly Ortega <sheilly.ortega@correounivalle.edu.co>
 * @version v.1.0.0 date:21/11/2021
 *
 * (ejemplo v.17.3.8 se lee: versión 17 del sofware, la tercera actualización del software
 *  * y la octava corrección de errores)
 */
public class GUI extends JFrame {
    public static final String MENSAJE_INICIO="Bienvenido a craps \n"
            + "Oprime el botón lanzar para iniciar el juego"
            + "\n Si tu tiro de salida es 7 u 11 ganas con Natural"
            + "\n Si tu tiro de salida es 2, 3 u 12 pierdes con Craps"
            + "\n Si sacas cualquier otro valor establecerás el Punto"
            + "\n Estando en Punto podrás saguir lanzando los dados"
            + "\n pero ahora ganarás si sacas nuevamente el valor del Punto"
            + "\n sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private Image imagenOtroTamanho;
    private ImageIcon imagenNuevoTamanho;
    private JTextArea resultados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     *
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        imagenOtroTamanho = imageDado.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
        dado1 = new JLabel(imagenNuevoTamanho);
        dado2 = new JLabel(imagenNuevoTamanho);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(450,250));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        resultados = new JTextArea(7,31);
        resultados.setText(MENSAJE_INICIO);
        resultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        JScrollPane scroll = new JScrollPane(resultados);
        this.add(scroll,BorderLayout.EAST);


    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();

            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            imagenOtroTamanho = imageDado.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
            dado1.setIcon(imagenNuevoTamanho);

            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            imagenOtroTamanho = imageDado.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
            dado2.setIcon(imagenNuevoTamanho);

            modelCraps.determinarJuego();
            resultados.setText(modelCraps.getEstadoToString());
        }
    }
}
