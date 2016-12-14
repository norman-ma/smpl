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
public class SMPLInt extends SMPLValue<SMPLReal>{
    
    int value;
    
    public SMPLInt(Integer v){
        value = v;
    }
    
    @Override
    public SMPLType getType(){
        return SMPLType.INTEGER;
    }
    
    public SMPLValue<?> add(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value + a.intValue());
        }else{
            return make(value + a.doubleValue());
        }
    }
    
    public SMPLValue<?> sub(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value - a.intValue());
        }else{
            return make(value - a.doubleValue());
        }
    }
    
    public SMPLValue<?> mul(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value * a.intValue());
        }else{
            return make(value * a.doubleValue());
        }
    }
    
    public SMPLValue<?> div(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value / a.intValue());
        }else{
            return make(value / a.doubleValue());
        }
    }
    
    public SMPLValue<?> mod(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value % a.intValue());
        }else{
            return make(value % a.doubleValue());
        }
    }
    
    @Override
    public int intValue(){
        return value;
    }
    
    @Override
    public double doubleValue(){
        return (double) value;
    }
    
    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
