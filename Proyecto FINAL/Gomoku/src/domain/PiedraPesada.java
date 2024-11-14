package domain;
public class PiedraPesada extends Piedra {

    protected final int puntacion = 2;

    public PiedraPesada(int row, int col){
        super(row, col);
    }

    public int getPuntuacion(){
        return this.puntacion;
    }
}