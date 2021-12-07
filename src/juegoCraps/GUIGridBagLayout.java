package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
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
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private Image imagenOtroTamanho;
    private ImageIcon imagenNuevoTamanho;
    private JTextArea mensajesSalida, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUIGridBagLayout(){
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

    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;

        this.add(headerProject,constraints); //Change this line if you change JFrame Container's Layout
        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;

        this.add(ayuda,constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;

        this.add(salir,constraints);


    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

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

            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalida.setRows(4);
            mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
        }
    }

}



