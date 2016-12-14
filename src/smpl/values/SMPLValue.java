/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.*;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public abstract class SMPLValue<T extends SMPLValue<T>> {
    
    private static final long serialVersionUID = 1L; 
    
    public abstract SMPLType getType(); 
    
    public static SMPLReal make(Double v){
        return new SMPLReal(v);
    }
    
    public static SMPLInt make(Integer i){
        return new SMPLInt(i);
    }
    
    public static SMPLString make(String s){
        return new SMPLString(s);
    }
    
    public static SMPLCharacter make(Character c){
        return new SMPLCharacter(c);
    }
    
    public boolean isInteger(){
        return getType().equals(SMPLType.INTEGER);
    }
    
    
    public int intValue() throws TypeSMPLException {
        throw new TypeSMPLException(SMPLType.INTEGER, getType());
    }

    
    public double doubleValue() throws TypeSMPLException {
        throw new TypeSMPLException(SMPLType.REAL, getType());
    }

    
    public SMPLFunction funValue() throws TypeSMPLException{
        throw new TypeSMPLException(SMPLType.FUNCTION, getType());
    }
    
}
