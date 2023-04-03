/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import static practica_final.Inicio.DIMI;
import static practica_final.Inicio.DIMJ;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

//Creamos un objeto tablero para implementar las cartas en la interfaz 
public class Tablero extends JPanel {
     //Array bidimensional para el conjunto de cartas que se mostraran en el tablero
    private Carta[][] t;
    //Array bidimensional de casillas que representan al fondo del tablero que se mostrará por pantalla en todo momento en este
    private CasillaDibujo[][] casillas;
    //Baraja será la situación de las cartas en este para luego tratarlas en como se mostrarán
    private Baraja B;
    private static final int MAXIMO = 800;
    private final int ANCHURA = MAXIMO / DIMJ;
    private final int ALTURA=MAXIMO/DIMI;
    //construimos el tablero dependiendo de las cartas ofrecidas por la baraja
    public Tablero(Baraja aux){
       this.B=aux;
        this.setBounds(0, 10, 1000, 680);
        this.setLayout(null);
        Mostrar();      
        this.setBackground(Color.red);
    }
    //Dibujara las cartas junto el fondo de estas
    public void Mostrar(){
         t = new Carta[DIMI][DIMJ];
         casillas=new CasillaDibujo[DIMI][DIMJ];
         int cont=0;
        int y = 135;
        int Cy=130;
        for (int i = 0; i < DIMI; i++) {
            int x = 10;
            int Cx=5;
        for (int j = 0; j < DIMJ; j++) {
            casillas[i][j]=new CasillaDibujo(false,Cx,Cy,70,90);
               t[i][j]=B.GetCard(cont);
              
               t[i][j].SetPosX(x);
               t[i][j].SetPosY(y);
              
               
                x += ANCHURA+15;
                Cx+=ANCHURA+15;
                cont++;
            }
            y += ALTURA-105;
            Cy+=ALTURA-105;
        }
    }
       @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMI; i++) {
        for (int j = 0; j < DIMJ; j++) {
            casillas[i][j].paintComponent(g);
            //En el caso de que la carta no sea visible no la dibujara
           if(t[i][j].IsVisible()){
           t[i][j].paintComponent(g);
            }
        }}
    }
}
