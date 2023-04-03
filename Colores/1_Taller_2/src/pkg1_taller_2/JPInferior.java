package pkg1_taller_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernandez Ramon,Aaron
 */
public class JPInferior extends JPanel implements ActionListener{
    Object[] niveles=new Object[10];
    Object[] complejidad=new Object[10];
    Object[] volver=new Object[1];
    public boolean partida=false;
    public static int x=1000;
    public static int y=1000;
    public static JButton Ex;
    public int num;
    public int com;
    public JButton b;
    public JButton EX;
    //Inicializamos el panel inferior con los botones necesarios 
    public JPInferior(){
        for(int i=0;i<niveles.length;i++){
            niveles[i]=i+1;
        }
        this.setBounds(-5,(y-y/10)-30, x, y/10);
        this.setLayout(null);
         b=new JButton("NUEVA PARTIDA");
        b.setBounds(10,10,480,53);
        b.setBackground(Color.black);
        b.setFocusPainted(false);
        b.setForeground(Color.white);
         this.add(b,BorderLayout.WEST);
        Ex=new JButton("SALIR") ;
        Ex.setBounds(505,10,480,53);
        Ex.setBackground(Color.black);
        Ex.setFocusPainted(false);
        Ex.setForeground(Color.white);
       this.add(Ex, BorderLayout.WEST);
      Ex.addActionListener(this);
    }
    //Una vez cliquemos en el botón para iniciar una nueva partida surgiran unas pantallas 
    //emergentes para indicar que parametros quiere implementar el usuario
     private void PartidaEmpezada(){
       volver=new Object[]{"OK"};
        if(!partida){
        for(int i=0;i<niveles.length;i++){
            niveles[i]=i+1;
        }
        for(int i=0;i<niveles.length;i++){
            complejidad[i]=i+1;
        }
        num=1+ JOptionPane.showOptionDialog(null, "NÚMERO DE NIVELES", "PULSAR BOTÓN", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, niveles, niveles[0]);
        System.out.println(num);
        if(num!=0){
           com=1+JOptionPane.showOptionDialog(null, "COMPLEJIDAD A SELECCIONAR","PULSAR BOTON" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, complejidad, complejidad[0]);
        }
     System.out.println(com);
        }else{
         JOptionPane.showOptionDialog(null, "¡¡¡NO SE PUEDE INICIAR PARTIDA MIENTRAS HAY UNA EN CURSO!!!","PULSAR BOTON" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, volver,volver[0]);
    }
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Ex){
            System.exit(0);
        }
        if(e.getSource()==b){
           PartidaEmpezada();
          menu m=new menu();
          // partida=true;
          m.partida=true;
        }
    }
    public void actualizar(){
        partida=true;
    }
}
