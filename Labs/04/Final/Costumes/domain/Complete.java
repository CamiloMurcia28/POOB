package domain;  
import java.util.ArrayList;

/**
* Write a description of class Complete Costume.
*
* @author Jeisson Casallas & Camilo Murcia
* @version 1.0
*/
public class Complete extends Costume{

    private int makeUp;
    private ArrayList<Basic> pieces;
    
    /**
     * Constructs a new complete custom
     * @param name 
     * @param makeUp
     * @param discount 
     */
    public Complete(String name, int makeUp, int discount){
        this.name=name;
        this.makeUp=makeUp;
        this.discount=discount;
        pieces= new ArrayList<Basic>();
    }

    /**
     * Add a new basic piece
     * @param b
     */   
    public void addBasic(Basic b){
        pieces.add(b);
    } 
    
    /**
     * Return the price of the costume
     * 
     * @return int return the price of the costume
     * @throws CostumeShopException COMPLETE_EMPYTY if the costume is not complete
     * 
     */
    @Override
    public int price() throws CostumeShopException{
        Integer price = 0;
        if(pieces.isEmpty()){
            throw new CostumeShopException(CostumeShopException.COMPLETE_EMPTY);
        }
        for(Basic piece: pieces){
            try{
                price+= piece.price();
            }catch(CostumeShopException e ){
                throw new CostumeShopException(e.getMessage());
            }
        }
        price+=makeUp;
        price = price - (int)((price*discount) * Math.pow(100,-1));
        return price;
    }
    
     /**
     * Calculates an estimate price
     * For basics where the price cannot be known or has error, the first o last value is assumed
     * @param type (first, last) 
     * @return total price of the costume
     * @throws CostumeShopException COMPLETE_EMPTY, if it don't have basics. IMPOSSIBLE, if it can't be calculated
     */
    public int price(String type) throws CostumeShopException{
        Integer price = 0;
        if(pieces.isEmpty()){
            throw new CostumeShopException(CostumeShopException.COMPLETE_EMPTY);
        }
        for (Basic piece : pieces) {
            try {
                price += piece.price();
            } catch (CostumeShopException e) {
                if(type.equals("first")){      
                    price += pieces.get(0).price();
                }else if(type.equals("last")){
                    price += pieces.get(pieces.size()-1).price();
                }else{
                    throw new CostumeShopException(CostumeShopException.IMPOSSIBLE_COMPLETE);
                }
            }
        }
        return price;
    }   
    
     /**
     * Calculates an estimate price
     * For basics where the price cannot be known, if makeUp then the makeUp is assumed
     * @param unknown 
     * @return 
     * @throws CostumeShopException COMPLETE_EMPTY, if it don't have basics. PRICE_UNKNOWN, if some basic is unknown and not makeUp. PRICE_ERROR, if some basic has error
     */
    public int price(boolean makeUp) throws CostumeShopException{
        Integer price = 0;
        if(pieces.isEmpty()){
            throw new CostumeShopException(CostumeShopException.COMPLETE_EMPTY);
        }
        for(Basic piece : pieces){
            try{
                price += piece.price();
            }catch(CostumeShopException e){
                if(!makeUp){
                    throw new CostumeShopException(CostumeShopException.PRICE_UNKNOWN);
                }else if(makeUp && !e.getMessage().equals(CostumeShopException.PRICE_ERROR)){
                    price += this.makeUp;
                }else{
                    throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
                }
            }
        }
        return price;
    }  
    
    /** 
     * Returns if the costume has makeUp
     * 
     * @return a boolean depending if there is makeUp 
     */
    public boolean isMakeUp(){
        boolean value = false;
        if(this.makeUp > 0){
            value = true;
        }
        return value;
    }
    
    @Override
    public String data() throws CostumeShopException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Maquillaje "+ makeUp+". Descuento: "+ discount);
        for(Basic b: pieces) {
            answer.append("\n\t"+b.data());
        }
        return answer.toString();
    } 
    

}
