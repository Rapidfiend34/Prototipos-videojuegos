/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.awt.Graphics;
import javax.swing.JPanel;
import static practica_final.Inicio.DIMJ;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

//Creamos un objeto como mano del usuario para tratar las cartas del dibujo de su mano en la interfaz
public class DibujoManoUsuario extends JPanel {

    private Baraja total;
    private Carta[] CartasM = new Carta[DIMJ];

    public DibujoManoUsuario(Baraja b) {
        this.setBounds(0, 520, 1000, 200);
        this.total = b;
        this.CartasM = total.Repartir(1);
        MostrarM();
    }
    //Establece las coordenadas de las cartas para dibujarlas en la posicion del tablero
    public void MostrarM() {
        int y = 55;
        int x = 14;
        for (int i = 0; i < DIMJ; i++) {
            CartasM[i].SetPosX(x);
            CartasM[i].SetPosY(y);
            x += 75;
        }
    }
    //Descarta la carta de la mano una vez haya sido utilizada
    public void eliminarC() {
        for (int i = 0; i < 13; i++) {
            CartasM[i].SetVisible(false);
        }
    }
    //Comprobar si la carta seleccionada se encuentra dentro de la mano
    public boolean ComprobarDCMano(int aux,int auxX,int auxY){
       return this.total.dentroCarta(aux, auxX, auxY, CartasM);
    }
    //Extraer la carta en base al parametro
    public Carta GetMano(int ind){
        return this.CartasM[ind];
    }
    //Anular la carta especificada de la mano
    public void AnularCMAno(int ind){
        CartasM[ind].SetVisible(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMJ; i++) {
            //Dibujara la carta siempre que sea visible, es decir jugable
            if (CartasM[i].IsVisible()) {
                CartasM[i].paintComponent(g);
            }
        }
    }
    
}
