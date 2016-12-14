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
public class SMPLBoolean extends SMPLValue<SMPLBoolean>{

    boolean value;
    
    public SMPLBoolean(Boolean b){
        value = b;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.BOOLEAN;
    }
    
    public String toString(){
        return String.valueOf(value);
    }
    
}
