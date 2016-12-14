/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.ArrayList;

/**
 *
 * @author namro_000
 */
public class SMPLVector extends SMPLValue<SMPLVector>{
    
    SMPLValue<?>[] value;
    int len;
    
    public SMPLVector(SMPLValue<?>...members){
        int i = members.length;
        SMPLSubVector subv;
        ArrayList<SMPLValue<?>> temp = new ArrayList<>();
        for(int c = 0; c<i; c++){
            if(members[c].getType().equals(SMPLType.SUBVECTOR)){
               subv = (SMPLSubVector)members[c];
               subv.eval();
               for(SMPLValue<?> v:subv.value()){
                   temp.add(v);
               }
            }
            else{
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
    
    public SMPLValue<?> get(int n){
        return value[n];
    }
    
    public int size(){
        return len;
    }

    @Override
    public SMPLType getType() {
        return SMPLType.VECTOR;
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
                    items += " "+value[i].toString();
                }
        }
        
        return "["+items+"]";
    }
    
}
