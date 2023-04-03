
public class MapaDoom {
    private int[][] mapa;
    public static final int wall=1;
    public static final int play=3;
    public static final int nothing=0;

    private int alto;
    private int ancho;

    public MapaDoom(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
        mapa = new int[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                mapa[i][j] = nothing;
            }
        }
    }
    public void agregarJugador(Jugador player){

        mapa[player.getPosX()][player.getPosY()] = play;
    }
    public boolean ComprobarValidez(Jugador player){
        if(mapa[player.getPosX()][player.getPosY()]==1){
            return false;
        }
        return true;
    }
    public int obtenerAncho(){
        return ancho;
    }
    public int obtenerAlto(){
        return alto;
    }
    public int obtenerCelda(int x, int y){
        return this.mapa[x][y];
    }
    public int obtenerCeldaM(int x, int y){
        System.out.println(this.mapa[x][y]);
        return this.mapa[x][y];
    }
    public boolean esTransitable(int res){

                if(res==1){
                  return false;
                }


        return true;
    }
    public void ActualizarJugador(){
        int []eje=this.obtenerJugador();
        this.mapa[eje[0]][eje[1]]=nothing;
    }
    public int[] obtenerJugador(){
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if(this.mapa[i][j]==3){
                    int[] res=new int[2];
                    res[0]=i;
                    res[1]=j;
                    return res;
                }
            }
        }
        return null;
    }



    public void agregarPared(int fila, int columna, int longitud, boolean horizontal) {
        // Verificar que la pared no sobrepase los límites del mapa
        if (fila < 0 || fila >= alto || columna < 0 || columna >= ancho) {
            throw new IllegalArgumentException("Coordenadas fuera de los límites del mapa");
        }

        // Verificar que la longitud sea positiva y no sobrepase los límites del mapa
        if (longitud < 1 || (horizontal && columna + longitud > ancho) || (!horizontal && fila + longitud > alto)) {
            throw new IllegalArgumentException("Longitud inválida para la dirección especificada");
        }

        // Agregar las celdas correspondientes a la pared
        for (int i = 0; i < longitud; i++) {

            int f = fila + (horizontal ? 0 : i);
            int c = columna + (horizontal ? i : 0);
            mapa[f][c] = wall;
        }
    }

    public int getCelda(int fila, int columna) {
        return mapa[fila][columna];
    }
}
