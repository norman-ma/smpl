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
public class SMPLCharacter extends SMPLValue<SMPLCharacter>{
    
    char value;

    public SMPLCharacter(Character c){
        value = c;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.CHARACTER;
    }
    
    public char charValue(){
        return value;
    }
    
    public int compareTo(SMPLValue<?> a) throws SMPLException{
        if(a.getType().equals(getType())){
            char x = a.charValue();
            
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
    public String toString() {
        return String.valueOf(value);
    } 
    
}
