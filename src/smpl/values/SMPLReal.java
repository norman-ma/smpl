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
public class SMPLReal extends SMPLValue<SMPLReal>{
    
    double value;
    
    public SMPLReal(){
        this(0D);
    }
    
    public SMPLReal(Double v){
        value = v;
    }
    
    @Override
    public SMPLType getType(){
        return SMPLType.REAL;
    }
    
    public SMPLReal add(SMPLValue<?> a) throws SMPLException{
        return make(value+a.doubleValue());
    }
    
    public SMPLReal sub(SMPLValue<?> a) throws SMPLException{
        return make(value-a.doubleValue());
    }
    
    public SMPLReal mul(SMPLValue<?> a) throws SMPLException{
        return make(value*a.doubleValue());
    }
    
    public SMPLReal div(SMPLValue<?> a) throws SMPLException{
        return make(value/a.doubleValue());
    }
    
    public SMPLReal mod(SMPLValue<?> a) throws SMPLException{
        return make(value%a.doubleValue());
    }
    
    public SMPLReal pow(SMPLValue<?> a) throws SMPLException{
        return make(Math.pow(value, a.doubleValue()));
    }
    
    public int compareTo(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            if(value < a.intValue()){
                return -1;
            }else if (value > a.intValue()){
                return 1;
            }else{
                return 0;
            }
        }else if(a.getType().equals(SMPLType.REAL)){
            if(value < a.doubleValue()){
                return -1;
            }else if (value > a.doubleValue()){
                return 1;
            }else{
                return 0;
            }
        }else{
            throw new TypeSMPLException("Compare operation called with non-numeric type");
        }
    }
    
    @Override
    public int intValue(){
        return (int) value;
    }
    
    @Override
    public double doubleValue(){
        return value;
    }
    
    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
