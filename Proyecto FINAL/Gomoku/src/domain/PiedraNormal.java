package domain;
public class PiedraNormal extends Piedra {

    protected final int puntacion = 1;

    public PiedraNormal(int row, int col){
        super(row, col);
    }

    public int getPuntuacion(){
        return this.puntacion;
    }

}
