package domain;

/**
 * La clase Player representa a un jugador en el juego Vintage Jewel.
 */
public class Player {

    /**
     * Número único que identifica al jugador.
     */
    public final byte numeroJugador;

    /**
     * Puntos acumulados por el jugador durante el juego.
     */
    public int points;

    /**
     * Constructor de la clase Player.
     *
     * @param numeroJugador Número único que identifica al jugador.
     */
    public Player(byte numeroJugador) {
        this.numeroJugador = numeroJugador;
        this.points = 0;
    }

    /**
     * Agrega puntos al total de puntos del jugador.
     *
     * @param pointsWon Puntos ganados que se agregarán al total del jugador.
     */
    public void addPoints(int pointsWon) {
        this.points += pointsWon;
    }

    /**
     * Obtiene la cantidad actual de puntos del jugador.
     *
     * @return La cantidad actual de puntos del jugador.
     */
    public int getPoints() {
        return points;
    }
    
        public void setScore(){
        this.points = 0;
    }
}
