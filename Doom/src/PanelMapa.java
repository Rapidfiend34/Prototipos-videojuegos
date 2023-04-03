import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;


    public class PanelMapa extends JPanel {
        private MapaDoom mapa; // El mapa que se va a dibujar
        private ArrayList<Bala> balas = new ArrayList<Bala>();


        // Constructor que recibe el mapa a dibujar
        public PanelMapa(MapaDoom mapa,Jugador jugador) {
            this.mapa = mapa;
            mapa.agregarPared(0, 0, 10, true); // Agregar pared en la fila superior
            mapa.agregarPared(0, 0, 10, false); // Agregar pared en la columna izquierda
            mapa.agregarPared(9, 0, 10, true); // Agregar pared en la fila inferior
            mapa.agregarPared(0, 9, 10, false); // Agregar pared en la columna derecha
            mapa.agregarJugador(jugador); // Agregar jugador en el centro del mapa

        }

        // Método para dibujar el mapa en el panel
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int anchoCelda = getWidth() / mapa.obtenerAncho();
            int altoCelda = getHeight() / mapa.obtenerAlto();
            for (int x = 0; x < mapa.obtenerAncho(); x++) {
                for (int y = 0; y < mapa.obtenerAlto(); y++) {
                    int tipoCelda = mapa.obtenerCelda(x, y);
                    Color colorCelda;
                    switch (tipoCelda) {
                        case 1:
                            colorCelda = Color.BLACK;
                            break;
                        case 2:
                            colorCelda = Color.WHITE;
                            break;
                        default:
                            colorCelda = Color.GRAY;
                            break;
                    }
                    g.setColor(colorCelda);
                    g.fillRect(x * anchoCelda, y * altoCelda, anchoCelda, altoCelda);
                }
            }
            int[] jugador = mapa.obtenerJugador();
            g.setColor(Color.RED);
            g.fillOval(jugador[0] * anchoCelda, jugador[1] * altoCelda, anchoCelda, altoCelda);
            dibujarBalas(g);
            //moverBalas();
        }
        public void agregarBala(int x, int y, int velocidad, int direccion) {
            int anchoCelda = getWidth() / mapa.obtenerAncho();
            int altoCelda = getHeight() / mapa.obtenerAlto();
            balas.add(new Bala(x*anchoCelda , y*altoCelda, velocidad, direccion));
        }
        public void dibujarBalas(Graphics g) {
            int anchoCelda = getWidth() / mapa.obtenerAncho();
            int altoCelda = getHeight() / mapa.obtenerAlto();
            for (Bala bala : balas) {
                bala.dibujar(g,anchoCelda,altoCelda);
            }

        }
        public int getBalaVel(){
            return this.balas.get(0).getVel();
        }
        public void moverBalas() {
            int anchoCelda = getWidth() / mapa.obtenerAncho();
            int altoCelda = getHeight() / mapa.obtenerAlto();
            for (int i = 0; i < balas.size(); i++) {
                Bala bala = balas.get(i);
                bala.mover();
                if (bala.getX() < 0 || bala.getX() > 800 || bala.getY() < 0 || bala.getY() > 600) {
                    // Eliminar la bala si sale de los límites del panel
                    balas.remove(i);
                }
                if(bala.getDirection()==1&& mapa.obtenerCeldaM((int) Math.ceil(bala.getX()/anchoCelda)+1,(bala.getY()/altoCelda))==1){
                    balas.remove(i);
                }else if((bala.getDirection()==0||bala.getDirection()==3)&&mapa.obtenerCeldaM((int) Math.ceil(bala.getX()/anchoCelda),(bala.getY()/altoCelda))==1){
                    balas.remove(i);
                }else if((bala.getDirection()==2&&mapa.obtenerCeldaM((int) Math.ceil(bala.getX()/anchoCelda),(bala.getY()/altoCelda)+1)==1)){
                    balas.remove(i);
                }
            }
            repaint();
        }
    }

