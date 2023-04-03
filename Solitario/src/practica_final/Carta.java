package practica_final;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T 
 *         Marc Gomez Barceló 43232431K
 */
//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0
//Creamos un objeto para analizar las propiedades de las cartas
public class Carta {

    private BufferedImage imagen;
    private boolean visible;
    private int ejex;
    private int ejey;
    private int num;
    public categoria cat;

    public enum categoria {
        DIAMANTE, TREBOL, PICA, CORAZON
    };

    //Construira la carta correspondiente a sus parametros de entrada
    public Carta(int numero, categoria Palo, int x, int y) {
        String DireccionIm = "Cartes/";
        this.visible = true;
        this.ejex = x;
        this.ejey = y;
        this.num = numero;
        this.cat = Palo;
        switch (Palo) {
            case DIAMANTE:
                DireccionIm = "Cartes/" + Integer.toString(num);
                DireccionIm += "_of_diamonds.png";
                break;
            case TREBOL:
                DireccionIm = "Cartes/" + Integer.toString(num);
                DireccionIm += "_of_clubs.png";
                break;
            case PICA:
                DireccionIm = "Cartes/" + Integer.toString(num);
                DireccionIm += "_of_spades.png";
                break;
            case CORAZON:
                DireccionIm = "Cartes/" + Integer.toString(num);
                DireccionIm += "_of_hearts.png";
                break;
        }
        try {

            imagen = ImageIO.read(new File(DireccionIm));

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(imagen, ejex, ejey, 65, 85, null);

    }

    //Extraer valor numerico del objeto cartas
    public int GetNum() {
        return num;
    }

    //Extraer valor de la categoria del objeto cartas
    public categoria GetCat() {
        return cat;
    }

    //Extraer valor de la posicion en el eje x del objeto cartas
    public int GetPosX() {
        return ejex;
    }

    //Extraer valor de la posicion en el eje y del objeto cartas
    public int GetPosY() {
        return ejey;
    }

    //Declarar un valor al atributo especificado
    public void SetPosX(int aux) {
        this.ejex = aux;
    }

    public void SetPosY(int aux) {
        this.ejey = aux;
    }

    public void SetVisible(boolean aux) {
        this.visible = aux;
    }

    //Comprobar si la carta es visible
    public boolean IsVisible() {
        return this.visible;
    }
}
