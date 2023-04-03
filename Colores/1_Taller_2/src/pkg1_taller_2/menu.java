/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import static pkg1_taller_2.JPInferior.y;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class menu extends JFrame implements ActionListener {

    private boolean q = false;
    private int lng = 1000;
    public int num;
    public int com;
    public Object[] volver = new Object[1];
    public Object[] complejidad = new Object[10];
    public boolean partida = false;
    public boolean encontrsdo;
    public int puntos = 0;
    public int numr = 0;
    public int numa = 1;
    Standby gif;
    TableroJB JBT;
    JPanel Jpsup;
    JPanel Jpinf;
    JPanel Inf1;
    JPanel Inf2;
    JPanel Inf3;
    JPanel Inf4;
    JPanel jpbot;
    JSplitPane barra1;
    JSplitPane barra2;
    JSplitPane barra3;
    JMenuBar JMB;
    JMenu Jm;
    JMenuItem JMIM;
    JMenuItem JMIS;
    JPInferior Inf;
    JPSuperior sup;
    Panel p;
    Object[] niveles = new Object[10];

    // Inicializamos la interfaz de la pantalla principal
    public menu() {
        super("Taller 2");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("R.png"));
        this.setSize(lng, lng);
        this.setLocationRelativeTo(null);
        IniComponents();
        this.getContentPane().setLayout(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
    }

    // Inicializar los componentes de la pantalla
    public void IniComponents() {
        gif = new Standby();
        p = new Panel();
        p.add(gif, BorderLayout.CENTER);
        Inf = new JPInferior();
        Inf.b.addActionListener(this);
        sup = new JPSuperior();
        Jm = new JMenu("MENU");
        Jm.setText("MENU");
        Jm.setBounds(0, 0, 150, 10);
        barra1 = new JSplitPane(SwingConstants.HORIZONTAL, Inf, p);
        barra1.setBounds(0, 890 - 30, lng, 10);
        barra2 = new JSplitPane(SwingConstants.HORIZONTAL, p, sup);
        barra2.setBounds(0, 180 - 30, lng, 10);
        JMenuBar JMB = new JMenuBar();
        JMIM = new JMenuItem("NUEVA PARTIDA");
        JMIM.addActionListener(this);
        JMIS = new JMenuItem("SALIR");
        JMIS.addActionListener(this);
        barra3 = new JSplitPane(SwingConstants.HORIZONTAL, p, sup);
        barra3.setBounds(0, 0, lng, 10);
        this.getContentPane().add(Inf);
        this.getContentPane().add(p);
        this.getContentPane().add(barra1);
        this.getContentPane().add(barra2);
        this.getContentPane().add(barra3);
        this.getContentPane().add(sup);
        JMB.add(Jm);
        Jm.add(JMIM);
        Jm.add(JMIS);
        this.setJMenuBar(JMB);

    }

    // En el caso que el usuario inicie la partida desde el apartado del JMenuItem
    // surgirán
    // una serie de pantallas emergentes para determinar los parametros que quiera
    // el usuario
    public void PartidaEmpezada() {
        volver = new Object[] { "OK" };
        if (!partida) {
            for (int i = 0; i < niveles.length; i++) {
                niveles[i] = i + 1;
            }
            for (int i = 0; i < niveles.length; i++) {
                complejidad[i] = i + 1;
            }
            num = 1 + JOptionPane.showOptionDialog(null, "NÚMERO DE NIVELES", "PULSAR BOTÓN",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, niveles, niveles[0]);

            if (num != 0) {
                com = 1 + JOptionPane.showOptionDialog(null, "COMPLEJIDAD A SELECCIONAR", "PULSAR BOTON",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, complejidad,
                        complejidad[0]);
            }

        } else {
            JOptionPane.showOptionDialog(null, "¡¡¡NO SE PUEDE INICIAR PARTIDA MIENTRAS HAY UNA EN CURSO!!!",
                    "PULSAR BOTON", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, volver,
                    volver[0]);
        }
    }

    public static void main(String[] args) {
        new menu().setVisible(true);
    }
    
    // Inicializa el primer tablero
    public void empezar() {
        System.out.println(num);
        p.remove(gif);
        JBT = new TableroJB(2, com, num, this);
        p.add(JBT);
        p.repaint();
    }

    // Comporbamos el boton que se ha interaccionado
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == JMIS)) {
            System.exit(0);
        }

        if ((e.getSource() == JMIM) || (e.getSource() == Inf.b)) {
            PartidaEmpezada();
            if ((num != 0) && (com != 0)) {
                partida = true;
                empezar();
            }
        }
    }

}
