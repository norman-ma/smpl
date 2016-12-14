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
