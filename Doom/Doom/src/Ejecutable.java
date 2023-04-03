import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ejecutable {
    public static final int VelocidadBala=10;
    private MapaDoom mapa;
    private Jugador jugador;
    public static final int WIDTH=800,HEIGHT=600;
    public void ejecutar() {
        // Inicializar el mapa y el jugador
        mapa = new MapaDoom(10, 10);
        jugador = new Jugador(2, 2);

        // Crear la ventana y agregar el panel de juego
        JFrame ventana = new JFrame("Doom");
        PanelMapa panelJuego = new PanelMapa(mapa,jugador);
        panelJuego.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ventana.add(panelJuego);

        // Agregar el KeyListener al panel de juego
        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        jugador.moverArriba(mapa);
                        break;
                    case KeyEvent.VK_DOWN:
                        jugador.moverAbajo(mapa);
                        break;
                    case KeyEvent.VK_LEFT:
                        jugador.moverIzquierda(mapa);
                        break;
                    case KeyEvent.VK_RIGHT:
                        jugador.moverDerecha(mapa);
                        break;
                    case KeyEvent.VK_SPACE:
                        panelJuego.agregarBala(jugador.getPosX(), jugador.getPosY(), VelocidadBala, jugador.getDireccion());
                        break;
                }
                panelJuego.repaint();

                /*while (true) {
                    panelJuego.moverBalas();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        // Manejar la excepción
                    }
                }*/
            }
        });
        panelJuego.setFocusable(true);
        panelJuego.requestFocus();

        // Mostrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        Thread hiloJuego = new Thread(() -> {
            while (true) {
                panelJuego.moverBalas();
                try {
                   // Thread.sleep(panelJuego.getBalaVel());
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    // Manejar la excepción
                }
            }
        });
        hiloJuego.start();
    }
}

