/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class Standby extends JPanel {
    ImageIcon gif;
    public Standby(){
        this.setBounds(0,0,1000, 700);
        this.setLayout(null);
    }
    //Pintamos la imagen animada en el panel correspondiente
    @Override
    public void paint(Graphics g){
        gif=new ImageIcon(getClass().getResource("uib.gif"));
        g.drawImage(gif.getImage(), 0, 0, getWidth(), getHeight(), this);
        this.setLayout(null);
        this.setOpaque(false);
        super.paint(g);
    }
}
