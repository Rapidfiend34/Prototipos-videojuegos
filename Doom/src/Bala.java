import java.awt.*;

public class Bala {
    private int x;
    private int y;
    private int velocidad;
    private boolean visible;
    private int direction;
    private static final int radio =20;
    private static final int IZQUIERDA=3,DERECHA=1,ARRIBA=0,ABAJO=2;
    public Bala(int x, int y,int vel,int dir) {
        this.x = x;
        this.y = y;
        this.velocidad = 5;
        this.direction=dir;
        this.visible = true;
    }
    public void mover() {
        switch (direction) {
            case IZQUIERDA:
                x -= velocidad;
                break;
            case DERECHA:
                x += velocidad;
                break;
            case ARRIBA:
                y -= velocidad;
                break;
            case ABAJO:
                y += velocidad;
                break;
        }
    }
    public int getDirection(){
        return this.direction;
    }

    public int getX() {
        System.out.println("x:"+x);
        return x;
    }

    public int getY() {
        System.out.println("y:"+y);
        return y;
    }

    public boolean isVisible() {
        return visible;
    }


    public void dibujar(Graphics g,int anchoC,int altoC) {
        g.setColor(Color.YELLOW);
        switch (direction) {
            case ARRIBA:
                g.fillOval(x+(anchoC/2) - radio, y - radio, radio * 2, radio * 2);
                break;
            case ABAJO:
                g.fillOval(x+(anchoC/2) - radio, y+(altoC) - radio, radio * 2, radio * 2);
                break;
            case DERECHA:
                g.fillOval(x+(anchoC) - radio, y+(altoC/2) - radio, radio * 2, radio * 2);
                break;
            case IZQUIERDA:
                g.fillOval(x - radio, y+(altoC/2) - radio, radio * 2, radio * 2);
                break;
        }
       // g.fillOval(x+(anchoC/2) - radio, y+(altoC/2) - radio, radio * 2, radio * 2);
    }
    public int getVel(){
        return this.velocidad;
    }
}
