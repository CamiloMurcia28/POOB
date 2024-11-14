package CostumeShop2023; 
import domain.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * CostumeShop
 * @author POOB  
 * @version ECI 2022
 */

public class CostumeShop{
    private LinkedList<Costume> costumes;
    private HashMap<String,Basic> basics;

    /**
     * Create a CostumeShop
     */
    public CostumeShop() throws CostumeShopException{
        costumes = new LinkedList<Costume>();
        basics = new HashMap<String,Basic>();
        addSome();
        JOptionPane.showMessageDialog(null, "¡Bienvenido a nuestro sistema de compra de disfraces!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Returns the size of the costumes list
     */
    public int getSizeCostumes(){
        return costumes.size();
    }
    
    /*
     * Create some Costumes from console
     */
    private void addSome() throws CostumeShopException{
        //para que funcionen las pruebas de unidad.
        /**String [][] basics = {{"Camisa","5000","10"},
                            {"Pantalon","10000","20"}};
        try{
            for (String [] c: basics){
                addBasic(c[0],c[1],c[2]);
            }
            String [][] Complete = {{"Zorro", "2000","0","Camisa\nPantalon"}};
            for (String [] s: Complete){
                addComplete(s[0],s[1],s[2],s[3]);
            }
        }catch(Exception e){
            Log.record(e);
            throw e;
            }*/
    
    }

    /**
     * Consult a costume
     * @param name
     * @return 
     */
    public Costume consult(String name){
        Costume c=null;
        try{
            for(int i=0;i<costumes.size() && c == null;i++){
                if (costumes.get(i).name().compareToIgnoreCase(name)==0) 
                   c=costumes.get(i);
            }
        }catch(Exception e){
            Log.record(e);
            throw e;
        }
        return c;
    }

    
    /**
     * Add a new basic costume
     * @param name 
     * @param price
     * @param discount
    */
    public void addBasic(String name, String price, String discount) throws CostumeShopException {
        try{
            int priceValue = Integer.parseInt(price);
            int discountValue = Integer.parseInt(discount);
            if (priceValue < 0) {
                throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
            }
            if (basics.containsKey(name.toUpperCase())) {
                throw new CostumeShopException(CostumeShopException.COSTUME_DUPLICATE);
            }
            if (discountValue < 0){
                throw new CostumeShopException(CostumeShopException.NEGATIVE_DISCOUNT);
            }
            Basic nc = new Basic(name, priceValue, Integer.parseInt(discount));
            costumes.add(nc);
            basics.put(name.toUpperCase(), nc);
            JOptionPane.showMessageDialog(null, "Se ha ingreado un disfraz basico correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            Log.record(e);
            throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
        } catch (CostumeShopException ee){
            Log.record(ee);
            throw ee;
        } catch (Exception eee){
            Log.record(eee);
            throw eee;
        }
    }
    
    /**
     * Add a new Complete costume
     * @param name 
     * @param makeUp
     * @param basics
    */
    public void addComplete(String name, String makeUp, String discount, String theBasics) throws CostumeShopException {
        try{
            int priceValue = Integer.parseInt(makeUp);
            int discountValue = Integer.parseInt(discount);
            if (basics.containsKey(name.toUpperCase())) {
                throw new CostumeShopException(CostumeShopException.COSTUME_DUPLICATE);
            }
            if (priceValue < 0) {
                throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
            }
            Complete c = new Complete(name, Integer.parseInt(makeUp), Integer.parseInt(discount));
            String[] aBasics = theBasics.split("\n");
            for (String b : aBasics) {
                c.addBasic(basics.get(b.toUpperCase()));
            }
            costumes.add(c);
            JOptionPane.showMessageDialog(null, "Se ha ingreado un disfraz completo correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            Log.record(e);
            throw new CostumeShopException(CostumeShopException.PRICE_ERROR);
        }catch (CostumeShopException ee){
            Log.record(ee);
            throw ee;
            
        } catch (Exception eee){
            Log.record(eee);
            throw eee;
        }
    }
    
    /**
     * Consults the costumes that start with a prefix
     * @param  
     * @return 
     */
    public LinkedList<Costume> select(String prefix){
        LinkedList <Costume> answers= new LinkedList<>();
        prefix=prefix.toUpperCase();
        try{
            for(int i=0;i<costumes.size();i++){
                if(costumes.get(i).name().toUpperCase().startsWith(prefix)){
                    answers.add(costumes.get(i));
                }   
            }
        }catch(Exception e){
            Log.record(e);
            throw e;
        }
        return answers;
    }

    /**
     * Consult selected costumes
     * @param selected
     * @return  
     */
    public String data(LinkedList<Costume> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(costumes.size()+ " disfraces\n");
        try{
            for(Costume p : selected) {
                try{
                    answer.append('>' + p.data());
                    answer.append("\n");
                }catch(CostumeShopException e){
                    answer.append("**** "+e.getMessage());
                }
            }
        }catch(Exception e){
            Log.record(e);
            throw e;
        }
        return answer.toString();
    }
    
    
     /**
     * Return the data of costumes with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all costumes
     * @return  
     */    
    public String toString(){
        JOptionPane.showMessageDialog(null, "Aca tienes la lista de disfraces", "Listado", JOptionPane.INFORMATION_MESSAGE);
        return data(costumes);
    }
    
    /**
     * Consult the number of costumes
     * @return 
     */
    public int numberCostumes(){
        return costumes.size();
    }

}
