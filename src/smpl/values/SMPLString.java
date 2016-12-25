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
public class SMPLString extends SMPLValue<SMPLString>{

    String value; 
    
    public SMPLString(String s){
        value = s;
    }
    
    public SMPLString substring(int start, int stop){
        return make(value.substring(start, stop));
    }
    
    public SMPLString concat(SMPLValue<?> s) throws TypeSMPLException {
        return make( value.concat( s.stringValue()) );
    }    
    
    public int compareTo(SMPLValue<?> a) throws SMPLException{
        if(a.getType().equals(getType())){
            int x = value.compareTo(((SMPLString)a).stringValue());
            
            if(x == 0){
                return x;
            }else if(x < 0){
                return -1;
            }else{
                return 1;
            }
        }else{
            return -1;
        }
    }
    
    @Override
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
