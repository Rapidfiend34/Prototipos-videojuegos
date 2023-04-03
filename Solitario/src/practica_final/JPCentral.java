/*
 * To change this license header, choose License Headers in Project Properties.
 * To chang8 this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

public class JPCentral extends JPanel {

    private Tablero game;
    private Inicio Ini;
    private CasillaDibujo DCasillaInf;
    private CasillaDibujo Cuadro1J;
    private CasillaDibujo Cuadro2J;
    private CasillaDibujo Cuadro3J;
    private JLabel Cont1;
    private JLabel Cont2;
    private JLabel Cont3;
    private JLabel CartasJ;
    private Baraja b = new Baraja();
    //Creamos un componente como panel central para gestionar los contadores y todo tipo de interacciones de dibujos por raton
    public JPCentral(Inicio I) {
        this.Ini = I;
        this.setBackground(new Color(42, 151, 42));
        this.setSize(1280, 670);
        this.setLayout(null);
        IniComponents();
    }
    //Construimos sus componentes internos y propiedades
    public void IniComponents() {
        game = new Tablero(Ini.getBaraja());
         DCasillaInf = new CasillaDibujo(false, 0, 0, 70, 90);
        DCasillaInf.setBounds(5, 570, 100, 130);
        Cuadro1J = new CasillaDibujo(false, 0, 0, 125, 175);
        Cuadro1J.setBounds(185, 15, 75, 105);
        this.Cuadro1J.setBackground(Color.red);
        Cuadro2J = new CasillaDibujo(false, 0, 0, 125, 175);
        Cuadro2J.setBounds(455, 15, 75, 105);
        Cuadro3J = new CasillaDibujo(false, 0, 0, 125, 175);
        Cuadro3J.setBounds(725, 15, 75, 105);
        Cont1 = new JLabel("0");
        Cont1.setBounds(320, 10, 310, 45);
        Cont1.setFont(new Font("arial", Font.BOLD, 85));
        Cont1.setHorizontalAlignment(JLabel.CENTER);
        Cont1.setForeground(Color.WHITE);
        Cont1.setLayout(null);
        Cont2 = new JLabel("0");
        Cont2.setBounds(590, 10, 310, 45);
        Cont2.setFont(new Font("arial", Font.BOLD, 85));
        Cont2.setHorizontalAlignment(JLabel.CENTER);
        Cont2.setForeground(Color.WHITE);
        Cont2.setLayout(null);
        Cont3 = new JLabel("0");
        Cont3.setBounds(860, 10, 310, 45);
        Cont3.setFont(new Font("arial", Font.BOLD, 85));
        Cont3.setHorizontalAlignment(JLabel.CENTER);
        Cont3.setForeground(Color.WHITE);
        Cont3.setLayout(null);
        CartasJ = new JLabel("0");
        CartasJ.setBounds(-10, 520, 100, 45);
        CartasJ.setFont(new Font("arial", Font.BOLD, 50));
        CartasJ.setHorizontalAlignment(JLabel.CENTER);
        CartasJ.setForeground(Color.WHITE);
        CartasJ.setLayout(null);
        game = new Tablero(Ini.getBaraja());
        Cuadro1J.add(Cont1);
        Cuadro2J.add(Cont2);
        Cuadro3J.add(Cont3);
        this.setLayout(null);
        this.add(CartasJ);
        this.add(DCasillaInf);
        this.add(Cuadro1J);
        this.add(Cuadro2J);
        this.add(Cuadro3J);
        this.add(game);
    }
    //Restaura algunos de los valores iniciales del panel
    public void Default() {
        Baraja b=new Baraja();
        Ini.EliminarMazo();
        this.remove(game);
        game = new Tablero(b);
        this.add(game);
        this.add(DCasillaInf);
        this.Cuadro1J.SetVer(false);
        this.Cuadro2J.SetVer(false);
        this.Cuadro3J.SetVer(false);
        this.Cont1.setText("0");
        this.Cont2.setText("0");
        this.Cont3.setText("0");
        this.CartasJ.setText("0");
        this.repaint();
    }
    //Modifica los contadores y sus respectivos dibujos para iniciar el contador de la partida
    public void Empezar(){
         Cuadro1J.SetVer(true);
            Cuadro2J.SetVer(true);
            Cuadro3J.SetVer(true);
            remove(DCasillaInf);
            CartasJ.setText("13");
            Cont1.setText("13");
            Cont2.setText("13");
            Cont3.setText("13");
    }
    //Extraer el contenido del contador dependiendo del parametro introducido
    public String ExtraerTxt(int aux){
        String res="";
        switch (aux){
            case 1:
                res=this.Cont1.getText();
                break;
            case 2:
                res=this.Cont2.getText();
                break;
            case 3:
               res= this.Cont3.getText();
               break;
            case 4:
                res=this.CartasJ.getText();
                break;
        }
        return res;
    }
    //Actualizar el valor de cada uno del contador dependiendo del parametro introducido
    public void ActualizarTxt(String res,int aux){
        switch (aux){
            case 1:
                this.Cont1.setText(res);
                break;
            case 2:
                this.Cont2.setText(res);
                break;
            case 3:
                this.Cont3.setText(res);
               break;
            case 4:
                this.CartasJ.setText(res);
                break;
        }
    }
    //Añadir componente tablero al panel central cuando sea declarado
    public void AddT(){
        this.add(game);
    }
    //Eliminar componente tablero del panel central cuando sea declarado
    public void DeleteT(){
        this.remove(game);
    }
    //Inicializar un nuevo tablero con los valores de la baraja
    public void IniT(Baraja aux){
        this.game=new Tablero(aux);
    }
}
