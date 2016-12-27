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
public class SMPLInt extends SMPLValue<SMPLInt>{
    
    int value;
    String string;
    int base;
    
    public SMPLInt(Integer v, int i){
        value = v;
        base = i;
        string = Integer.toString(value, base);
    }
    
    @Override
    public SMPLType getType(){
        return SMPLType.INTEGER;
    }
    
    public SMPLValue<?> add(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value + a.intValue(), base);
        }else{
            return make(value + a.doubleValue());
        }
    }
    
    public SMPLValue<?> sub(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value - a.intValue(), base);
        }else{
            return make(value - a.doubleValue());
        }
    }
    
    public SMPLValue<?> mul(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make(value * a.intValue(), base);
        }else{
            return make(value * a.doubleValue());
        }
    }
    
    public SMPLValue<?> div(SMPLValue<?> a) throws SMPLException{
        try{
            if(a.isInteger()){
                return make(value / a.intValue(), base);
            }else{
                return make(value / a.doubleValue());
            }
        }catch(ArithmeticException ar){
            throw new MathSMPLException("MathError: "+ar.getMessage());
        }
    }
    
    public SMPLValue<?> mod(SMPLValue<?> a) throws SMPLException{
        try{
            if(a.isInteger()){
                return make(value % a.intValue(), base);
            }else{
                return make(value % a.doubleValue());
            }
        }catch(ArithmeticException ar){
            throw new MathSMPLException("MathError: "+ar.getMessage());
        }
    }
    
    public SMPLValue<?> pow(SMPLValue<?> a) throws SMPLException{
        if(a.isInteger()){
            return make((int)Math.pow(value,a.intValue()), base);
        }else{
            return make(Math.pow(value,a.doubleValue()));
        }
    }
    
    public SMPLInt bitAnd(SMPLInt a) throws SMPLException{
        return make(value & a.intValue(),base);
    }
    
    public SMPLInt bitOr(SMPLInt a) throws SMPLException{
        return make(value | a.intValue(), base);
    }
    
    public SMPLInt comp() throws SMPLException{
        return make(~value, base);
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
        return value;
    }
    
    @Override
    public double doubleValue(){
        return (double) value;
    }
    
    @Override
    public String toString(){
        return string;
    }
}
