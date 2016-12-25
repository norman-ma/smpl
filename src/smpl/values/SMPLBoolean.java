/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public class SMPLBoolean extends SMPLValue<SMPLBoolean>{

    boolean value;
    
    public SMPLBoolean(Boolean b){
        value = b;
    }
    
    @Override
    public boolean booleanValue(){
        return value;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.BOOLEAN;
    }
    
    public int compareTo(SMPLValue<?> a)throws SMPLException{
        if(a.getType().equals(getType())){
            boolean x = a.booleanValue();
            if(value == x){
                return 0;                
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
    
    @Override
    public String toString(){
        return String.valueOf(value);
    }
    
}
