import java.util.HashMap;

/** NPTensor.java
 * 
 * @author CIS 2023-02
 */
    
public class NPTensor{
    
    HashMap <String, Tensor> nptDictionary = new HashMap<>();
    //private HashMap<String, Tensor> variables;
    
    //Ciclo 1(declarar, asignar y consultar)
    
    //Assign a tensor to a variable
    //All elements have the same value
    public void NPTensor(String name, int shape[], int value ){
        Tensor t = new Tensor(shape, value);
        nptDictionary.put(name, t);
    }    
    
    //Assign a tensor to a variable
    //The values are given
    public void NPTensor(String name, int shape[], int[] values ){
        Tensor t = new Tensor(shape, values);
        nptDictionary.put(name, t);
    }    
    
    public Tensor ConsultarNPTensor(String name){
        Tensor t = null;
        if(nptDictionary.containsKey(name)){
            t = nptDictionary.get(name);
        }
        return t;
    }
        
    //Fin del ciclo 1
    
    
    //Ciclo 2(Dimensiones, redimensionar y barajar)
    
    //Assigns the value of an operation to a variable (unary operations)
    // a := unary b
    //The unary operators are: shape, reshape, shuffle
    public void assign(String a, String unary, String b){
        
    }    
    
    //Assigns the value of an operation to a variable (unary operations)
    // a := unary b
    //The unary operators with parameters are: slide [start, end, step], mean (axis), find (value)
    public void assign(String a, String unary, String b, int [] parameters){
    }  
    
    //Assigns the value of an operation to a variable (simple binary operations)
    // a := b simple c
    //The simple operators are: +, -, * (one to one)
    public void assign(String a, String b, String sBinary, String c){
    }   
    

    
    //Returns the string representtion of a tensor
    public String toString(String variable){
        return null;
    }
    
    //If the last operation was successful
    public boolean ok(){
        return false;
    }
}
    



