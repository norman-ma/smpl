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
public class SMPLString extends SMPLValue<SMPLString>{

    String value; 
    
    public SMPLString(String s){
        value = s;
    }
    
    public SMPLString substring(int start, int stop){
        return make(value.substring(start, stop));
    }
    
    public SMPLString concat(SMPLValue<?> s){
        return make(value.concat(s.toString()));
    }    
    
    public String stringValue(){
        return value;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.STRING;
    }
    
    @Override
    public String toString(){
        return value;
    }
    
    
    
}
