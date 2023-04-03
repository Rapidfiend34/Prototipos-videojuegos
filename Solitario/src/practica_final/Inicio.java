/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T 
 *         Marc Gomez Barceló 43232431K
 */
//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0
//Creamos la pantalla principal del programa
public class Inicio extends JFrame implements ActionListener, MouseListener {

    public static final int DIMI = 4;
    public static final int DIMJ = 13;
    private boolean mezclado = true;
    private DibujoManoUsuario M;
    private int turno = 0;
    private Carta CartaS;
    private Carta[] C1 = new Carta[DIMJ];
    private Carta[] C2 = new Carta[DIMJ];
    private Carta[] C3 = new Carta[DIMJ];
    private Baraja b;
    private boolean permitido = true;
    private static JPInferior JPInf;
    private static JPCentral Jpanel;

    public Inicio() {
        this.setTitle("Juego del 7");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("Cartes/Logo.jpg"));
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        IniComponents();
    }

    //Inicializamos los paneles diferenciados en panel central y el panel inferior
    public void IniComponents() {
        b = new Baraja();
        Jpanel = new JPCentral(this);
        JPInf = new JPInferior();
        this.getContentPane().add(Jpanel);
        this.getContentPane().add(JPInf);
        JPInf.AñadirListener(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Inicio().setVisible(true);
    }

    //Generar el tablero despues de realizar modificaciones durante la partida y a la hora de mezclar cartas
    public void ActualizarT(Baraja aux) {
        Jpanel.DeleteT();
        Jpanel.IniT(b);
        Jpanel.AddT();
        Jpanel.repaint();
    }

    //Generar el mazo del usuario un vez cumpla una interaccion con las cartas de este
    public void ActualizarM(int a) {
        Jpanel.remove(M);
        M.AnularCMAno(a);
        Jpanel.add(M);
        Jpanel.repaint();
    }

    //Crear los mazos de cada uno de los jugadores incluido el usuario
    public void CrearM() {
        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1:
                    M = new DibujoManoUsuario(b);
                    Jpanel.add(M);
                    break;
                case 2:
                    C1 = b.Repartir(i);
                    break;
                case 3:
                    C2 = b.Repartir(i);
                    break;
                case 4:
                    C3 = b.Repartir(i);
                    break;
            }

        }
    }

    //Una vez alguno de los jugadores se quede sin cartas sem abrirá una ventana emergente felicitando al ganador
    public void Ganador(int a) {
        String imagen = "Cartes/Jug" + Integer.toString(a) + "Riu.png";
        Icon icono = new ImageIcon(imagen);
        Toolkit.getDefaultToolkit().beep();
        if (a == 0) {
            JOptionPane.showMessageDialog(null, "¡¡¡HAS GANADO!!!", "VICTORIA", JOptionPane.INFORMATION_MESSAGE, icono);
        } else {
            JOptionPane.showMessageDialog(null, "¡¡¡EL JUGADOR " + a + " HA GANADO!!!", "DERROTA", JOptionPane.INFORMATION_MESSAGE, icono);
        }
    }

    //Gestionar cada una de las posibles acciones de la IA por turnos
    public void TurnoIA() {
        turno++;
        boolean aux = false;
        switch (turno) {
            case 1:
                for (int i = 0; (i < DIMJ) && (!aux); i++) {
                    //cuando la carta sea visible, es decir disponible, validará si se puede utilizar o no
                    if (C1[i].IsVisible()) {
                        aux = b.ComprobarCorrecta(C1[i].GetNum() - 1, C1[i]);
                    }
                    //En el caso de poder ser utilizada se actualizadara su contador y el mensaje de la etiqueta "mensajes"
                    if (aux) {
                        ActualizarT(b);
                        C1[i].SetVisible(false);
                        String text = Jpanel.ExtraerTxt(1);
                        int num = Integer.parseInt(text) - 1;
                        text = Integer.toString(num);
                        Jpanel.ActualizarTxt(text, 1);
                        //Si el contador llega al valor 0 se considerará que el jugador ha ganado, pot lo tanto declarariamos el metodo Ganador() para felicitarlo
                        // y a su vez actualizamos el estado de los botones
                        if (num == 0) {
                            Ganador(1);
                            JPInf.ActualizarB4();
                            //En el caso de no haber ganado en este turno se actualiza el mensaje de la etiqueta
                        } else {
                            JPInf.ActualizarMensajeCIA(C1[i], 1);
                        }
                    }
                }
                //Si no puede poner ninguna carta se actualizara la etiqueta indicando que el jugador pasa
                if (!aux) {
                    JPInf.ActualizarMensajeP(1);
                }

                break;
            case 2:
                for (int i = 0; (i < DIMJ) && (!aux); i++) {
                    //cuando la carta sea visible, es decir disponible, validará si se puede utilizar o no
                    if (C2[i].IsVisible()) {
                        aux = b.ComprobarCorrecta(C2[i].GetNum() - 1, C2[i]);
                    }
                    //En el caso de poder ser utilizada se actualizadara su contador y el mensaje de la etiqueta "mensajes"
                    if (aux) {
                        ActualizarT(b);
                        C2[i].SetVisible(false);
                        String text = Jpanel.ExtraerTxt(2);
                        int num = Integer.parseInt(text) - 1;
                        text = Integer.toString(num);
                        Jpanel.ActualizarTxt(text, 2);
                        //Si el contador llega al valor 0 se considerará que el jugador ha ganado, pot lo tanto declarariamos el metodo Ganador() para felicitarlo
                        // y a su vez actualizamos el estado de los botones
                        if (num == 0) {
                            Ganador(2);
                            JPInf.ActualizarB4();
                            //En el caso de no haber ganado en este turno se actualiza el mensaje de la etiqueta
                        } else {
                            JPInf.ActualizarMensajeCIA(C2[i], 2);
                        }
                    }
                }
                //Si no puede poner ninguna carta se actualizara la etiqueta indicando que el jugador pasa
                if (!aux) {
                    JPInf.ActualizarMensajeP(2);
                }
                break;
            case 3:
                for (int i = 0; (i < DIMJ) && (!aux); i++) {
                    //cuando la carta sea visible, es decir disponible, validará si se puede utilizar o no
                    if (C3[i].IsVisible()) {
                        aux = b.ComprobarCorrecta(C3[i].GetNum() - 1, C3[i]);
                    }
                    //En el caso de poder ser utilizada se actualizadara su contador y el mensaje de la etiqueta "mensajes"
                    if (aux) {
                        ActualizarT(b);
                        C3[i].SetVisible(false);
                        String text = Jpanel.ExtraerTxt(3);
                        int num = Integer.parseInt(text) - 1;
                        text = Integer.toString(num);
                        Jpanel.ActualizarTxt(text, 3);
                        //Si el contador llega al valor 0 se considerará que el jugador ha ganado, pot lo tanto declarariamos el metodo Ganador() para felicitarlo
                        // y a su vez actualizamos el estado de los botones
                        if (num == 0) {
                            Ganador(3);
                            JPInf.ActualizarB4();
                            //En el caso de no haber ganado en este turno se actualiza el mensaje de la etiqueta
                        } else {
                            JPInf.ActualizarB3();
                            JPInf.ActualizarMensajeCIA(C3[i], 3);

                        }
                        permitido = true;
                        turno = 0;

                    }
                }
                //Si no puede poner ninguna carta se actualizara la etiqueta indicando que el jugador pasa
                if (!aux) {
                    turno = 0;
                    permitido = true;
                    JPInf.ActualizarMensajeP(3);
                    JPInf.ActualizarB3();

                }
                break;
        }
    }

    //Restaurar valores para volver al estado inicial del programa
    public void Restart() throws Exception {

        Jpanel.removeMouseListener(this);
        b = new Baraja();
        //En el caso de estar en estado de mezclar las cartas debe generar otro mazo 
        if (mezclado) {
            M = new DibujoManoUsuario(b);
            Jpanel.add(M);
        }
        JPInf.DefaultInf();
        Jpanel.Default();
        Jpanel.remove(M);
        JPInf.getBRestart().addActionListener(this);
        this.repaint();
        permitido = true;
    }

    public void EliminarMazo() {
        Jpanel.remove(M);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JPInf.getBPass()) {
            //Si el usuario decide pasar la ronda no tendra permitido seleccionar sus cartas del mazo hasta que no toque su turno
            JPInf.ActualizarB2();
            JPInf.ActualizarM("Has pasado");
            permitido = false;
            turno = 0;
        }
        if ((e.getSource() == JPInf.getBPlayerT()) && (!permitido)) {
            TurnoIA();
        }
        if (e.getSource() == JPInf.getBMix()) {
            //Se mezclaran las cartas actualizando asi el mazo, ademas habilitara los botones de jugar y reiniciar
            JPInf.ActualizarM("La baraja está mezclada");
            JPInf.getBPlay().setEnabled(true);
            JPInf.getBRestart().setEnabled(true);
            b.barajar();
            ActualizarT(b);

        }
        if (e.getSource() == JPInf.getBRestart()) {
            try {
                Restart();
            } catch (Exception ex) {

            }
        }

        if (e.getSource() == JPInf.getBPlay()) {
            //Creara la mano de usuario en la interfaz, aplicara interaccion en el panel y creara el tablero junto a los contadores inicializados
            M = new DibujoManoUsuario(b);
            Jpanel.addMouseListener(this);
            CrearM();
            Jpanel.Empezar();
            b = new Baraja();
            b.AnularC();
            ActualizarT(b);
            JPInf.ActualizarM("Las cartas están repartidas, es tu turno, pon un 7 si lo tienes");
            JPInf.ActualizarB1();
            mezclado = false;
        }

    }

    //Comprobar la validez de la carta seleccionada por el usuario en su mano
    public void ComprobarCS(MouseEvent e) {
        if (permitido) {
            int x = 0, y = 0, i, j = 0;
            x = e.getX();
            y = e.getY();
            boolean encontrado = false;
            //Comprobar si la carta seleccionada se enuentra en la mano
            for (i = 0; i < DIMJ && !encontrado; i++) {
                encontrado = M.ComprobarDCMano(i, x, y);
                if (encontrado) {
                    CartaS = M.GetMano(i);

                }
            }
            i--;
            j--;
            //Una vez se haya encontrado la carta comprobar si es valida
            if (encontrado) {
                encontrado = b.ComprobarCorrecta(CartaS.GetNum() - 1, CartaS);

                if (encontrado) {
                    //En el caso de se valida se actualizaran los indicadores y se actualizara tanto el tablero como la mano
                    String text = Jpanel.ExtraerTxt(4);
                    int num = Integer.parseInt(text) - 1;

                    if (num == 0) {
                        //Si el contador llega al valor 0 se considerará que el usuario ha ganado, pot lo tanto declarariamos el metodo Ganador() para felicitarlo
                        // y a su vez actualizamos el estado de los botones
                        Ganador(0);
                        JPInf.ActualizarB4();

                    } else {
                        JPInf.ActualizarMensajeCJ(CartaS);
                    }
                    ActualizarT(b);
                    ActualizarM(i);
                    text = Integer.toString(num);
                    Jpanel.ActualizarTxt(text, 4);
                    JPInf.ActualizarB2();
                    permitido = false;
                    turno = 0;
                } else {
                    //En el caso de ser una carta no valida sonara un sonido indicando que deberia realizar otra accion
                    Sonido sound = new Sonido();
                    sound.setFile("Sonido/incorrecto.wav");
                    sound.play();
                }
            }
        }

    }
    //Extraer la baraja de la partida

    public Baraja getBaraja() {
        return this.b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            ComprobarCS(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
