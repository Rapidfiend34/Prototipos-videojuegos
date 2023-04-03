/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class PanelB extends JPanel implements ActionListener {

    JPanel JP1;
    JPanel JP2;
    JPanel JP3;
    JPanel JP4;
    JLabel JL1;
    JLabel JL2;
    JLabel JL3;
    JLabel JL4;
    JLabel JLC1;
    JLabel JLC2;
    JLabel JLC3;
    JLabel JLC4;
    //Inicializamos las etiquetas y los paneles necesarios para la implementacion de los marcadores
    public PanelB() {
        this.setBounds(0, 10, 1000, 170);
        this.setLayout(null);
        this.setBackground(Color.black);
        JP1 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10, 10, 450, 55);
        JP1.setBackground(Color.black);
        JP2 = new JPanel();
        JP2.setLayout(null);
        JP2.setBounds(10, 75, 450, 55);
        JP2.setBackground(Color.black);
        JP3 = new JPanel();
        JP3.setLayout(null);
        JP3.setBounds(535, 10, 450, 55);
        JP3.setBackground(Color.black);
        JP4 = new JPanel();
        JP4.setLayout(null);
        JP4.setBounds(535, 75, 450, 55);
        JP4.setBackground(Color.black);
        JL1=new JLabel("NIVELES PARTIDA");
        JL1.setBounds(10+10, 5, 310, 45);
        JL1.setFont(new Font("arial",0,20));
        JL1.setHorizontalAlignment(JLabel.CENTER);
        JL1.setLayout(null);
        JL1.setBackground(Color.white);
        JL1.setOpaque(true);
        JP1.add(JL1);
        JL2=new JLabel("NIVEL ACTUAL");
        JL2.setBounds(10+10, 5, 310, 45);
        JL2.setFont(new Font("arial",0,20));
        JL2.setHorizontalAlignment(JLabel.CENTER);
        JL2.setLayout(null);
        JL2.setBackground(Color.white);
        JL2.setOpaque(true);
        JP2.add(JL2);
        JL3=new JLabel("NIVELES RESTANTES");
        JL3.setBounds(10+10, 5, 310, 45);
        JL3.setFont(new Font("arial",0,20));
        JL3.setHorizontalAlignment(JLabel.CENTER);
        JL3.setLayout(null);
        JL3.setBackground(Color.white);
        JL3.setOpaque(true);
        JP3.add(JL3);
        JL4=new JLabel("PUNTUACION");
        JL4.setBounds(10+10, 5, 310, 45);
        JL4.setFont(new Font("arial",0,20));
        JL4.setHorizontalAlignment(JLabel.CENTER);
        JL4.setLayout(null);
        JL4.setBackground(Color.white);
        JL4.setOpaque(true);
        JP4.add(JL4);
        JLC1=new JLabel("000");
        JLC1.setBounds(330+20, 5, 75, 45);
        JLC1.setFont(new Font("arial",0,20));
        JLC1.setForeground(Color.red);
        JLC1.setHorizontalAlignment(JLabel.CENTER);
        JLC1.setLayout(null);
        JLC1.setBackground(Color.white);
        JLC1.setOpaque(true);
        JP1.add(JLC1);
        JLC2=new JLabel("000");
        JLC2.setBounds(330+20, 5, 75, 45);
        JLC2.setFont(new Font("arial",0,20));
        JLC2.setForeground(Color.red);
        JLC2.setHorizontalAlignment(JLabel.CENTER);
        JLC2.setLayout(null);
        JLC2.setBackground(Color.white);
        JLC2.setOpaque(true);
        JP2.add(JLC2);
        JLC3=new JLabel("000");
        JLC3.setBounds(330+20, 5, 75, 45);
        JLC3.setFont(new Font("arial",0,20));
        JLC3.setForeground(Color.red);
        JLC3.setHorizontalAlignment(JLabel.CENTER);
        JLC3.setLayout(null);
        JLC3.setBackground(Color.white);
        JLC3.setOpaque(true);
        JP3.add(JLC3);
        JLC4=new JLabel("000");
        JLC4.setBounds(330+20, 5, 75, 45);
        JLC4.setFont(new Font("arial",0,20));
        JLC4.setForeground(Color.red);
        JLC4.setHorizontalAlignment(JLabel.CENTER);
        JLC4.setLayout(null);
        JLC4.setBackground(Color.white);
        JLC4.setOpaque(true);
        JP4.add(JLC4);
        this.add(JP1);
        this.add(JP2);
        this.add(JP3);
        this.add(JP4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
