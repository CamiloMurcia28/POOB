package domain;  

/**
* Write a description of class Basic Costume.
*
* @author Jeisson Casallas & Camilo Murcia
* @version 1.0
*/
public class Basic extends Costume{
    
    private Integer price;
    
    public Basic(String name, Integer price, int discount){
        this.name=name;
        this.price=price;
        this.discount=discount;
    }   

    @Override
    public int price() throws CostumeShopException{
       
       if (price == null) throw new CostumeShopException(CostumeShopException.PRICE_EMPTY);
       if (price < 1) throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
       if(discount>0){
          price = price - (price*discount)/100; 
       }
       return price;
    }    
    
    public String data() throws CostumeShopException{
        return name+". Precio:" +price()+".Descuento"+discount;
    }
}