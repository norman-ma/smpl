/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import smpl.sys.RuntimeSMPLException;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public class SMPLTuple extends SMPLValue<SMPLTuple>{
    
    SMPLValue<?>[] values;
    int len;
    
    public SMPLTuple(SMPLValue<?>[] v){
        values  = v;
        len = v.length;
    }
    
    public SMPLValue<?> get(int indx) throws SMPLException{
        try{
            return values[indx];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new RuntimeSMPLException("Tuple index out of bounds: "+e.getMessage());
        }
    }
    
    public SMPLValue<?>[] getValues(){
        return values;
    }
    
    public int size(){
        return len;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.TUPLE;
    }

    @Override
    public int compareTo(SMPLValue<?> arg) throws SMPLException {
        if(arg.getType().equals(getType())){
            int n = ((SMPLTuple)arg).getValues().length;
            
            for(int i = 0; i < n; i++){
                try{
                    if(values[i].compareTo(((SMPLTuple)arg).get(i)) == 0){}               
                    else{
                        return -1;
                    }
                }catch(Exception e){
                    return -1;
                }
                return 0;
            }
        }else{
            return -1;
        }
        
        return -1;
    }
    
     public String toString(){
        String items; 
        int len = values.length;
        switch (len) {
            case 0: items = ""; break;
            case 1: items = values[0].toString(); break;
            default: 
                items = values[0].toString();
                for (int i = 1; i < len; i++) {
                    items += ", "+values[i].toString();
                }
        }
        
        return "("+items+")";
    }
    
}

