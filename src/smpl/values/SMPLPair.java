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
public class SMPLPair extends SMPLValue<SMPLPair>{

    SMPLValue<?> car, cdr;
    
    public SMPLPair(){}
    
    public SMPLPair(SMPLValue<?> a, SMPLValue<?> b){
        car = a;
        cdr = b;
    }
    
    public SMPLValue<?> getCar(){
        return car;
    }
    
    public SMPLValue<?> getCdr(){
        return cdr;
    }
    
    public void setCar(SMPLValue<?> a){
        car = a;
    }
    
    public void setCdr(SMPLValue<?> a){
        cdr = a;
    }
    
    
    @Override
    public SMPLType getType() {
        return SMPLType.PAIR;
    }
    
    @Override
    public String toString(){
        return String.format("(%s,%s)", car.toString(), cdr.toString());
    }
    
}
