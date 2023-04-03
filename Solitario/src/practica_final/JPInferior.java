/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0
//Creamos un componente como panel inferior para gestionar todo componente afine a los mensajes de estado e interacciones con botones
public class JPInferior extends JPanel {

    private JTextField mensajes;
    private JButton JBMezclar;
    private JButton JBJugar;
    private JButton JBReiniciar;
    private JButton JBTurnoJ;
    private JButton JBPasar;
    private JPanel JPBotones;
    private JSplitPane barra1;
    private JSplitPane barra2;

    public JPInferior() {
        this.setBounds(0, 670, 1000, 100);
        this.setLayout(null);
        IniComponentsInf();
    }
    //Construimos los componentes con sus propiedades
    private void IniComponentsInf() {
       mensajes = new JTextField();
        mensajes.setBounds(0, 73, 1000, 25);
        mensajes.setEditable(false);
        mensajes.setLayout(null);
        mensajes.setText("Antes de jugar debeis mezclar las cartas");
        mensajes.setFont(new Font("arial", Font.BOLD, 15));
        JPBotones = new JPanel(null);
        JPBotones.setBounds(0, 25, 1000, 35);
        barra2 = new JSplitPane(SwingConstants.HORIZONTAL);
        barra2.setBounds(0, 0, 1000, 20);
        barra2.setLayout(null);
        barra1 = new JSplitPane(SwingConstants.HORIZONTAL, JPBotones, mensajes);
        barra1.setBounds(0, 65, 1000, 20);
        JBMezclar = new JButton("Mezclar");
        JBMezclar.setBounds(330, 0, 100, 30);
        JBMezclar.setFocusable(false);
        JBJugar = new JButton("Jugar");
        JBJugar.setBounds(440, 0, 100, 30);
        JBJugar.setEnabled(false);
        JBJugar.setFocusable(false);
        JBReiniciar = new JButton("Reiniciar");
        JBReiniciar.setBounds(550, 0, 100, 30);
        JBReiniciar.setEnabled(false);
        JBReiniciar.setFocusable(false);
        JBTurnoJ = new JButton("Turno jugador");
        JBTurnoJ.setBounds(360, 0, 120, 30);
        JBTurnoJ.setEnabled(true);
        JBTurnoJ.setFocusable(false);
        JBPasar = new JButton("Pasar");
        JBPasar.setBounds(380, 0, 100, 30);
        JBPasar.setEnabled(true);
        JBPasar.setFocusable(false);
        JPBotones.add(JBMezclar);
        JPBotones.add(JBJugar);
        JPBotones.add(JBReiniciar);
        this.add(JPBotones);
        this.add(barra2);
        this.add(barra1);
        this.add(mensajes);
    }
    //Actualizar tanto los botones como los mensajes en el estado de truno del usuario
    public void ActualizarB1() {
        JPBotones.remove(JBMezclar);
        JPBotones.remove(JBJugar);
        JPBotones.remove(JBReiniciar);
        JPBotones.add(JBPasar);
        JBReiniciar.setBounds(490, 0, 100, 30);
        JPBotones.add(JBReiniciar);
        JPBotones.repaint();
        this.add(mensajes);
    }
    //Actualizar tanto los botones como los mensajes en el estado de truno de los jugadores
    public void ActualizarB2() {
        JPBotones.remove(JBPasar);
        JPBotones.remove(JBReiniciar);
        JPBotones.add(JBTurnoJ);
        JBReiniciar.setBounds(490, 0, 100, 30);
        JPBotones.add(JBReiniciar);
        JPBotones.repaint();
        this.add(mensajes);
    }
    //Actualizar tanto los botones como los mensajes en el estado de truno del usuario despues del turno del ultimo jugador
    public void ActualizarB3() {

        JPBotones.remove(JBTurnoJ);
        JPBotones.remove(JBReiniciar);
        JPBotones.add(JBPasar);
        JBReiniciar.setBounds(490, 0, 100, 30);
        JPBotones.add(JBReiniciar);
        JPBotones.repaint();
        this.add(mensajes);
    }
    //Actualizar tanto los botones como los mensajes en el estado de finalizacion de la partida
    public void ActualizarB4() {
        JBTurnoJ.setEnabled(false);
        JPBotones.repaint();
        mensajes.setText("Simulacion finalizada");
    }
    //Actualizar el mensaje depues de que el usuario haya introducido una carta en el tablero
    public void ActualizarMensajeCJ(Carta aux) {
        if (aux.GetNum() < 10) {

            mensajes.setText("Has puesto el [0" + aux.GetNum() + " " + aux.cat + "]");
        } else {
            mensajes.setText("Has puesto el [" + aux.GetNum() + " " + aux.cat + "]");
        }
    }
    //Actualizar el mensaje depues de que los jugadores de la IA no hayan introducido ninguna carta al tablero
    public void ActualizarMensajeP(int aux) {
        mensajes.setText("El jugador " + aux + " pasa");
    }
    //Actualizar el mensaje depues de que los jugadores de la IA hayan introducido una carta en el tablero
    public void ActualizarMensajeCIA(Carta aux, int player) {
        if (aux.GetNum() < 10) {

            mensajes.setText("El jugador " + player + " Ha puesto el [0" + aux.GetNum() + " " + aux.cat + "]");
        } else {
            mensajes.setText("El jugador " + player + " Ha puesto el [" + aux.GetNum() + " " + aux.cat + "]");
        }
    }
    //Restablecer los botones y el mensaje en el estado inicial
    public void DefaultInf() {
        JPBotones.remove(JBPasar);
        JPBotones.remove(JBTurnoJ);
        JBTurnoJ.setEnabled(true);
        JPBotones.remove(JBMezclar);
        JPBotones.remove(JBJugar);
        JPBotones.add(JBMezclar);
        JPBotones.add(JBJugar);
        JBJugar.setEnabled(false);
        JBReiniciar.setEnabled(false);
        JBReiniciar.setBounds(550, 0, 100, 30);
        JPBotones.add(JBReiniciar);
        JPBotones.repaint();
        mensajes.setText("Antes de jugar debeis mezclar las cartas");
    }
    //Actualizar el mensaje de diferentes estados durante la partida
    public void ActualizarM(String aux){
        mensajes.setText(aux);
    }
    //Añadir action listener a cada uno de los botones a interactuar
    public void AñadirListener(Inicio aux){
        JBMezclar.addActionListener(aux);
        JBJugar.addActionListener(aux);
        JBTurnoJ.addActionListener(aux);
        JBPasar.addActionListener(aux);
        JBReiniciar.addActionListener(aux);
    }
    public JButton getBMix(){
    return this.JBMezclar;
}
    public JButton getBPlay(){
    return this.JBJugar;
}
    public JButton getBPass(){
    return this.JBPasar;
}
    public JButton getBRestart(){
    return this.JBReiniciar;
}
    public JButton getBPlayerT(){
    return this.JBTurnoJ;
}
}
