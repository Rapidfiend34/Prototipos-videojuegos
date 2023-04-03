/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class TableroJB extends JPanel implements ActionListener {

    public Object[] volver = new Object[1];
    public static boolean error = false;
    public static int DIMENSION = 2;
    private static final int MAXIM = 700;
    public static int COSTAT = MAXIM / DIMENSION;
    private static int dificultad;
    public int max = 0;
    private int nt;
    private static int R;
    private static int V;
    private static int A;
    private static int a;
    private static int b;
    private static int actual;
    private int puntuacion;
    private int Po;
    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private static menu m;
    private static Color BASE = new Color(R, V, A);
    private static Color COMPLEJO = Color.DARK_GRAY;
    public CuadradoJB t[][];

    //Inicializar el tablero con las dimensiones del nivel correspondiente
    public TableroJB(int tam, int dif, int top, menu q) {
        volver = new Object[]{"OK"};
        this.m = q;
        this.max = top;
        this.setBounds(0, 0, 1000, 700);
        this.setLayout(null);
        this.DIMENSION = tam;
        this.dificultad = dif;
        this.COSTAT = MAXIM / tam;
        t = new CuadradoJB[DIMENSION][DIMENSION];
        Random R1 = new Random();
        nt = m.num;
        num1 = Integer.toString(nt);
        m.sup.JPB.JLC1.setText(num1);
        Po = 1;
        num2 = Integer.toString(Po);
        m.sup.JPB.JLC2.setText(num2);
        actual = m.num - 1;
        num3 = Integer.toString(actual);
        m.sup.JPB.JLC3.setText(num3);
        puntuacion = 0;
        num4 = Integer.toString(puntuacion);
        m.sup.JPB.JLC4.setText(num4);
        //Generamos valores aleatorios para inicializar el color de las casillas
        R = R1.nextInt(150);
        V = R1.nextInt(150);
        A = R1.nextInt(150);
        a = R1.nextInt(DIMENSION);
        b = R1.nextInt(DIMENSION);
        Color BASE = new Color(R, V, A);
        Color COMPLEJO = new Color(R + 30 - (2 * this.dificultad), V + 30 - 2 * this.dificultad, +A + 30 - (2 * this.dificultad));
        int y = 6;
        //Generamos las casillas necesarias por cada fila y columna del tablero
        for (int i = 0; i < DIMENSION; i++) {
            int x = 145;
            for (int j = 0; j < DIMENSION; j++) {
                if ((i == a) && (j == b)) {
                    
                    t[i][j] = new CuadradoJB(x, y, COSTAT - 5, COSTAT - 5, COMPLEJO);
                    this.add(t[i][j]);
                    System.out.println("era" + a + " " + b);
                    t[i][j].addActionListener(this);
                    this.setLayout(null);
                    x += COSTAT;
                } else {
                    t[i][j] = new CuadradoJB(x, y, COSTAT - 5, COSTAT - 5, BASE);
                    this.add(t[i][j]);
                    t[i][j].addActionListener(this);
                    this.setLayout(null);
                    x += COSTAT;
                }
            }
            y += COSTAT;
        }
        this.setLayout(null);
        this.setVisible(true);
    }

    public void NextLevel() {
        error = false;
        Delete();
        this.DIMENSION = DIMENSION + 1;
        this.COSTAT = MAXIM / DIMENSION;
        t = new CuadradoJB[DIMENSION][DIMENSION];
        Random R1 = new Random();
        //Generamos valores aleatorios para inicializar el color de las casillas
        R = R1.nextInt(150);
        V = R1.nextInt(150);
        A = R1.nextInt(150);
        a = R1.nextInt(DIMENSION);
        b = R1.nextInt(DIMENSION);
        Color BASE = new Color(R, V, A);
        Color COMPLEJO = new Color(R + 30 - (2 * this.dificultad), V + 30 - 2 * this.dificultad, +A + 30 - (2 * this.dificultad));
        int y = 6;
        //Generamos las casillas necesarias por cada fila y columna del tablero
        for (int i = 0; i < DIMENSION; i++) {
            int x = 145;
            for (int j = 0; j < DIMENSION; j++) {
                if ((i == a) && (j == b)) {
                    
                    t[i][j] = new CuadradoJB(x, y, COSTAT - 5, COSTAT - 5, COMPLEJO);
                    this.add(t[i][j]);
                    System.out.println("era" + a + " " + b);
                    t[i][j].addActionListener(this);
                    this.setLayout(null);
                    x += COSTAT;
                } else {
                    t[i][j] = new CuadradoJB(x, y, COSTAT - 5, COSTAT - 5, BASE);
                    this.add(t[i][j]);

                    t[i][j].addActionListener(this);
                    this.setLayout(null);
                    x += COSTAT;
                }
            }
            y += COSTAT;
        }
        this.repaint();
        this.setLayout(null);
        this.setVisible(true);
    }
//Borramos el tablero actual
    public void Delete() {
        for (int ind = 0; ind < DIMENSION; ind++) {
            for (int res = 0; res < DIMENSION; res++) {
                this.remove(t[ind][res]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        max--;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (e.getSource() == t[a][b]) {           
//En caso de que finalice la partida al llegar al último nivel, se mostrara un mensaje para felicitar al jugador y a continuacion
                    if (max != 0) {
//Si la partida finaliza y el usuario a acertado en la última opcion
                    } else {
                        puntuacion += DIMENSION;
                        num4 = Integer.toString(puntuacion);
                        m.sup.JPB.JLC4.setText(num4);
                        volver = new Object[]{"SALIR"};
                        JOptionPane.showOptionDialog(null, "¡¡¡BIEN JUGADOO PARTIDA FINALIZADA CON "+puntuacion+" PUNTOS!!!", "PULSAR BOTON", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, volver, volver[0]);
                        this.Delete();
                        nt = 0;
                        num1 = Integer.toString(nt);
                        m.sup.JPB.JLC1.setText(num1);
                        Po = 0;
                        num2 = Integer.toString(Po);
                        m.sup.JPB.JLC2.setText(num2);
                        actual =0;
                        num3 = Integer.toString(actual);
                        m.sup.JPB.JLC3.setText(num3);
                        puntuacion = 0;
                        num4 = Integer.toString(puntuacion);
                        m.sup.JPB.JLC4.setText(num4);
                        System.exit(0);
                        max = 0;
                    }
//En el caso de que el usuario haya elegido una casilla erronea se marcaran los bordes de color
                } else {
                    System.out.println(a + " " + b);
                    t[a][b].setBorder(BorderFactory.createLineBorder(Color.green, 5));
                    ((JButton) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.red, 5));
                    error = true;
                    if (max != 0) {

                    } else {
                        if (puntuacion > 0) {
                            puntuacion -= 1;
                            num4 = Integer.toString(puntuacion);
                            m.sup.JPB.JLC4.setText(num4);
                        }

                        volver = new Object[]{"VOLVER"};
                        JOptionPane.showOptionDialog(null, "¡¡¡BIEN JUGADOO PARTIDA FINALIZADA CON "+puntuacion+" PUNTOS!!!", "PULSAR BOTON", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, volver, volver[0]);
                       nt = 0;
                        num1 = Integer.toString(nt);
                        m.sup.JPB.JLC1.setText(num1);
                        Po = 0;
                        num2 = Integer.toString(Po);
                        m.sup.JPB.JLC2.setText(num2);
                        actual =0;
                        num3 = Integer.toString(actual);
                        m.sup.JPB.JLC3.setText(num3);
                        puntuacion = 0;
                        num4 = Integer.toString(puntuacion);
                        m.sup.JPB.JLC4.setText(num4);
                        System.exit(0);
                    }
                }
            }

        }
//Si el usuario a seleccionado la casilla erronea saltara un mensaje de error
        if (error) {
            if (puntuacion > 0) {
                puntuacion -= 1;
                num4 = Integer.toString(puntuacion);
                m.sup.JPB.JLC4.setText(num4);
            }
            JOptionPane.showOptionDialog(null, "¡¡¡CUADRADO SELECCIONADO ERRONEO!!!", "PULSAR BOTON", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, volver, volver[0]);

        } else {
            System.out.println("actual" + actual);
            puntuacion += DIMENSION;
            num4 = Integer.toString(puntuacion);
            m.sup.JPB.JLC4.setText(num4);
        }

        Po += 1;
        num2 = Integer.toString(Po);
        m.sup.JPB.JLC2.setText(num2);
        actual -= 1;
        num3 = Integer.toString(actual);
        m.sup.JPB.JLC3.setText(num3);
        this.NextLevel();
    }
}
