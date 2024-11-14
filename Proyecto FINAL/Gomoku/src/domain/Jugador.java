package domain;
import java.awt.Color;


/**
 * Write a description of interface Jugador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Jugador
{
    void setColor(Color color);
    Color getColor();
    void setToken(Piedra piedra);
    void startTime();
    int getTotalTime();

    public void setColor();
}
