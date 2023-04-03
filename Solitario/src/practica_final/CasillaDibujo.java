/*
 * Defineix un panel amb un cercle, un cuadrat i dues línies
 * Tot es pinta sobre un objecte Graphics2D
 */
package practica_final;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Implementar el dibujo del rectangulo verde para fondo y marcador, a su vez implementara la parte de atras de las cartas
class CasillaDibujo extends JPanel {

    private final String fitxerImatge = "Cartes/card_back_blue.png";
    private BufferedImage img;
    private boolean ver;
    private int eX;
    private int eY;
    private int ANCHURA;
    private int ALTURA;
//Se dibujara dependiendo de sus parametros que determinaran las propiedades del dbujo
    public CasillaDibujo(boolean a, int q, int b, int c, int w) {
        this.ANCHURA = c;
        this.ALTURA = w;
        this.eX = q;
        this.eY = b;
        this.ver = a;
        try {

            img = ImageIO.read(new File(fitxerImatge));

        } catch (IOException e) {
            System.out.println("Error");
        }

    }
    //Extraer el valor de la booleana de verificacion
    public void SetVer(boolean aux){
        this.ver=aux;
    }

    //Dependiendo de si cambia a la imagen de carta o de rectangulo de fondo variarán sus dimensiones
    @Override
    public void paintComponent(Graphics g) {
        if (!ver) {
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setBackground(new Color(42, 151, 42).darker());
            g2d.clearRect(eX, eY, ANCHURA, ALTURA);
        } else {
              g.drawImage(img, 0, 0, 75, 100, null);
        }
    }
}
