/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

/**
 *
 * @author namro_000
 */
public class SMPLVector extends SMPLValue<SMPLVector>{
    
    SMPLValue<?>[] value;
    int len;
    
    public SMPLVector(SMPLValue<?>...members){
        int i = members.length;
        value = new SMPLValue<?>[i];
        for(int c = 0; c<i; c++){
            value[c] = members[c];
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
                    items += ", "+value[i].toString();
                }
        }
        
        return "[: "+items+" :]";
    }
    
}
