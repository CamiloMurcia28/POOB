package domain;  
 
import java.util.ArrayList;

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
        int price = makeUp;
        if(pieces.size() == 0){
            throw new CostumeShopException(CostumeShopException.COMPLETE_EMPTY);
        }
        else{
            int alternativePrice = calculateValidPrice(type);
            for(Basic c: pieces){
                try{
                    price += c.price();
                }
                catch(CostumeShopException e){
                    if(alternativePrice == 0){
                        throw new CostumeShopException(CostumeShopException.IMPOSSIBLE_COMPLETE);
                    }
                    price += alternativePrice;
                }
            }
        }
        return price * (1 - (discount / 100));
    }  
    
    /**
     * Method used to calculate a valid price value.
     * @param type (first, last)
     * @return if type (first) returns the first valid price of the basics costumes, if type (last) returns the last valid price of the basics costumes.
     */
    private int calculateValidPrice(String type){
        int price = 0;
        int index;
        boolean flag = true;
        if(type.equals("first")){
            index = 0;
            while(index < pieces.size() && flag){
                try{
                    price = pieces.get(index).price();
                    flag = false;
                }
                catch(CostumeShopException e){
                }
            }
        }
        else if(type.equals("last")){
            index = pieces.size();
            while(index > 0 && flag){
                try{
                    price = pieces.get(index).price();
                    flag = false;
                }
                catch(CostumeShopException e){
                }
                index--;
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
        return 0;
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
