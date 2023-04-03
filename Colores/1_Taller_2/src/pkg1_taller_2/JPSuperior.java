/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1_taller_2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Fernandez Ramon,Aaron
 */
//Creamos el panel superior en el que se implementara el panel de los marcadores
public class JPSuperior extends JPanel  {
    public PanelB JPB=new PanelB();
    public JSplitPane barra3 =new JSplitPane();
    public JPSuperior(){
        this.setBounds(0, 0, 1000,180-30);
        this.setLayout(null);
        this.setBackground(Color.green);
        IniComponents();
    }
    public void IniComponents(){
      this.add(JPB);
    }
   
}
