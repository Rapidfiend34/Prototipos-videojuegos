/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class CuadradoJB extends JButton {
    private Rectangle2D.Float rec;
    private int xr;
    private int yr;
    private int ar;
    private int lr;
    private Color col;
    private Color borde;
    private boolean Incorrecto=false;
    public CuadradoJB(Rectangle2D.Float r, Color c){
        this.rec=r;
        this.col=c;
    }
    public CuadradoJB(int x,int y,int anchura,int altura,Color color){
        this.xr=x;
        this.yr=y;
        this.ar=anchura;
        this.lr=altura;
        this.col=color;
        this.setBounds(xr, yr,ar , lr);
        this.setBackground(col);
        this.setLayout(null);
        this.setVisible(true);
    }
}
