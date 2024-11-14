package domain;
public class PiedraTemporal extends Piedra {

    protected final int puntacion = 1;

    public PiedraTemporal(int row, int col){
        super(row, col);
    }

    public int getPuntuacion(){
        return this.puntacion;
    }

}