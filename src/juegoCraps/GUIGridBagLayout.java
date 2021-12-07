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
        this.setUndecorated(true);
        getContentPane().setBackground(new Color(209,171,255));
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
        headerProject = new Header("Mesa Juego Craps", Color.GRAY);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;

        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout
        ayuda = new JButton(" ? ");
        ayuda.setFont(new Font("SansSerif", Font.BOLD+Font.PLAIN, 14));
        ayuda.setForeground(Color.white);
        ayuda.addActionListener(escucha);
        ayuda.setBackground(new Color(0,102,255));
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;

        this.add(ayuda, constraints);

        salir = new JButton("Salir");
        salir.setFont(new Font("SansSerif", Font.BOLD+Font.PLAIN, 14));
        salir.setForeground(Color.WHITE);
        salir.addActionListener(escucha);
        salir.setBackground(new Color(255, 81,51));
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;

        this.add(salir, constraints);

        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        imagenOtroTamanho = imageDado.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
        dado1 = new JLabel(imagenNuevoTamanho);
        dado2 = new JLabel(imagenNuevoTamanho);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(450,250));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.setBackground(Color.WHITE);
        panelDados.add(dado1);
        panelDados.add(dado2);

        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        add(panelDados, constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados");
        resultadosDados.setBackground(Color.WHITE);
        resultadosDados.setEditable(false);

        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(resultadosDados, constraints);

        lanzar = new JButton("lanzar");
        lanzar.setFont(new Font("SansSerif", Font.BOLD+Font.PLAIN, 14));
        lanzar.addActionListener(escucha);
        lanzar.setBackground(new Color(63,255,51));
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(lanzar, constraints);

        mensajesSalida = new JTextArea(4,31);
        mensajesSalida.setText("Usa el botón (?) para ver las reglas del juego");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Mensajes "));
        mensajesSalida.setBackground(Color.white);
        mensajesSalida.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajesSalida, constraints);




    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar){
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
                mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
            }else if(e.getSource()==ayuda){
                JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
            }else{
                System.exit(0);
            }
        }
    }

}



