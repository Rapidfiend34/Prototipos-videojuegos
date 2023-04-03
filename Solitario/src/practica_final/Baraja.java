package practica_final;

import java.util.Random;
import practica_final.Carta.categoria;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

//Creamos un objeto para gestionar la manipulacion de las cartas de la baraja
public class Baraja {

    private final int cantidad = 52;
    private final int numCartas = 13;
    private Carta[] cart;
    //Construimos una baraja con cada una de las cartas ordenadas por numero y categoria
    public Baraja() {
        cart = new Carta[cantidad];
        int contador = 0;
        for (categoria aux : categoria.values()) {
            for (int i = 0; i < numCartas; i++) {
                cart[contador] = new Carta(i + 1, aux, 0, 0);
                contador++;
            }
        }
    }
    //Sorteamos las cartas para asi tener la baraja completamente sorteada
    public void barajar() {
        for (int i = 0; i < cantidad; i++) {
            Carta aux;
            int valor = 0;
            valor = new Random().nextInt(cantidad);
            aux = cart[valor];
            cart[valor] = cart[i];
            cart[i] = aux;

        }
    }
    //Modificamos las cartas de la baraja al estado no visible para que asi no sean visibles en el tablero
    public void AnularC() {
        int contador = 0;
        for (categoria aux : categoria.values()) {
            for (int i = 0; i < numCartas; i++) {

                cart[contador].SetVisible(false);
                contador++;
            }
        }
    }
    //Se repartira cada numCartas cartas a cada uno de los jugadores
    public Carta[] Repartir(int a) {
        Carta[] ver = new Carta[numCartas];
        int ind = 0;
        switch (a) {
            case 1:
                ind = 0;
                for (int i = 0; i < numCartas; i++) {
                    ver[ind] = new Carta(cart[i].GetNum(), cart[i].cat, cart[i].GetPosX(), cart[i].GetPosY());
                    ind++;

                }
                break;
            case 2:
                ind = 0;
                for (int i = numCartas; i < 26; i++) {
                    ver[ind] = new Carta(cart[i].GetNum(), cart[i].cat, cart[i].GetPosX(), cart[i].GetPosY());
                    ind++;

                }
                break;
            case 3:
                ind = 0;
                for (int i = 26; i < 39; i++) {

                    ver[ind] = new Carta(cart[i].GetNum(), cart[i].cat, cart[i].GetPosX(), cart[i].GetPosY());
                    ind++;

                }
                break;
            case 4:
                ind = 0;
                for (int i = 39; i < 52; i++) {

                    ver[ind] = new Carta(cart[i].GetNum(), cart[i].cat, cart[i].GetPosX(), cart[i].GetPosY());
                    ind++;

                }
                break;
        }
        return ver;
    }
    //Comprobara si la carta seleccionada por los jugadores es valida para jugarla
    public boolean ComprobarCorrecta(int aux, Carta a) {
        int indice;
        int posterior;
        for (int i = 0; i < 4; i++) {
            indice = aux + i * numCartas;
            if (aux < 6) {
                posterior = indice + 1;
            } else {
                posterior = indice - 1;
            }
            //En el caso de que se halle una carta con el mismo numero en la baraja y a su vez no sea visible en el tablero analizaremos
            //si la carta continua proxima al centro es visible en el tablero
            if ((cart[indice].GetNum() == a.GetNum())&&(cart[indice].GetCat()==a.GetCat()) && (!(cart[indice].IsVisible()))) {

                if ((cart[posterior].IsVisible()) && (a.GetNum() != 7)) {
                    cart[indice] = new Carta(a.GetNum(), a.cat, a.GetPosX(), a.GetPosY());
                    return true;
                }
                if (a.GetNum() == 7) {
                    cart[indice] = new Carta(a.GetNum(), a.cat, a.GetPosX(), a.GetPosY());
                    return true;
                }
            }
        }

        return false;
    }
    //Verificar si la carta seleccionada por el usuario es de su mazo
    public boolean dentroCarta(int ind, int a, int b, Carta[] aux) {

        if ((a >= aux[ind].GetPosX()) && (a <= (aux[ind].GetPosX() + 65)) && (b <= 659) && (b >= 573) && (aux[ind].IsVisible())) {
            return true;
        }
        return false;
    }
    //Extraer la carta de la baraja
    public Carta GetCard(int aux){
        return this.cart[aux];
    }
}
