/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.ArrayList;
import smpl.sys.RuntimeSMPLException;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public class SMPLVector extends SMPLValue<SMPLVector>{
    
    SMPLValue<?>[] value;
    int len;
    
    public SMPLVector(SMPLValue<?>[] members){
        int i = members.length;
        ArrayList<SMPLValue<?>> temp = new ArrayList<>();
        for(int c = 0; c<i; c++){
           if (members[c].getType().equals(SMPLType.SUBVECTOR)){
               SMPLValue<?>[] v = ((SMPLSubVector)members[c]).getValues();
               for(SMPLValue<?> a: v){
                   temp.add(a);
               }
           }else{
               temp.add(members[c]);
           }
        }        
        i = temp.size();
        value = new SMPLValue<?>[i];
        
        for(int c = 0; c < i; c++){
            value[c] = temp.get(c);
        }
        len = value.length;
    }
    
    public SMPLValue<?> get(int n)throws SMPLException{
        try{
            return value[n];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new RuntimeSMPLException("Vector index out of bounds: "+e.getMessage());
        }
    }
    
    public int size(){
        return len;
    }

    @Override
    public SMPLType getType() {
        return SMPLType.VECTOR;
    }
    
    @Override
    public int compareTo(SMPLValue<?> a) throws SMPLException{
        if(a.getType().equals(getType())){
            int l = ((SMPLVector) a).size();
            
            if(l == len){
                for(int i = 0; i < len; i++){
                    SMPLValue<?> x,y;
                    x = get(i);
                    y = ((SMPLVector) a).get(i);
                    if(x.compareTo(y)==0){}
                    else{
                        return -1;
                    }
                    return 0;
                }
            }else{
                return -1;
            }
        }else{
            return -1;
        }
        return -1;
    }

    @Override    
    public String toString(){
        String items;       
        switch (len) {
            case 0: items = ""; break;
            case 1: items = value[0].toString(); break;
            default: 
                items = value[0].toString();
                for (int i = 1; i < len; i++) {
                    items += ", "+value[i].toString();
                }
        }
        
        return "["+items+"]";
    }
    
}
