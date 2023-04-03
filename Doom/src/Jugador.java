import javax.swing.*;

public class Jugador {
    private int posX;
    private int  posY;
    private int direccion;
    public Jugador(int x,int y){
        this.posX=x;
        this.posY=y;
        this.direccion=0;
    }
    public void moverHorizontal(int cantidad,MapaDoom mapa) {
        int nuevaPos = posX + cantidad;
        if (nuevaPos >= 0 && nuevaPos < mapa.obtenerAncho() && mapa.esTransitable(mapa.getCelda(posY, nuevaPos))) {
            posX = nuevaPos;
        }
    }
    public void moverArriba(MapaDoom map){
        map.ActualizarJugador();

        this.posY--;
        if(!map.ComprobarValidez(this)){
            this.posY++;
        }
        direccion=0;
        map.agregarJugador(this);

    }
    public void moverAbajo(MapaDoom map){
        map.ActualizarJugador();
        this.posY++;
        if(!map.ComprobarValidez(this)){
            this.posY--;
        }
        direccion=2;
        map.agregarJugador(this);
    }
    public void moverDerecha(MapaDoom map){
        map.ActualizarJugador();
        this.posX++;
        if(!map.ComprobarValidez(this)){
            this.posX--;
        }
        direccion=1;
        map.agregarJugador(this);

    }
    public void moverIzquierda(MapaDoom map){
        map.ActualizarJugador();
        this.posX--;
        if(!map.ComprobarValidez(this)){
            this.posX++;
        }
        direccion=3;
        map.agregarJugador(this);
    }
    public int getPosX() { return posX;}
    public int getPosY() { return posY;}
    public int getDireccion() {
        return direccion;
    }
    // En la clase Jugador
    /*
    public void disparar(JPanel panelJuego) {
        // Crear una nueva bala amarilla en la posición actual del jugador
        Bala bala = new Bala(this.getX(), this.getY());
        // Añadir la bala a la lista de balas del panel de juego
        panelJuego.agregarBala(bala);
    }*/
    public void moverVertical(int cantidad,MapaDoom mapa) {
        int nuevaPos = posY + cantidad;
        if (nuevaPos >= 0 && nuevaPos < mapa.obtenerAlto() && mapa.esTransitable(mapa.getCelda(nuevaPos, posX))) {
            posY = nuevaPos;
        }
    }
}
