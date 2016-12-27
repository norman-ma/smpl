/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import smpl.semantics.Environment;
import smpl.semantics.Visitor;
import smpl.syntax.Exp;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public class SMPLPromise extends SMPLValue<SMPLPromise>{
    
    Exp expression;
    Environment<SMPLValue<?>> env;
    boolean kept;
    SMPLValue<?> value;
    
    public SMPLPromise(Exp e, Environment<SMPLValue<?>> en){
        expression = e;
        env = en;
        kept = false;
    }
    
    public Exp getExpression(){
        return expression;
    }
    
    public Environment<SMPLValue<?>> getEnv(){
        return env;
    }
    
    public void keep(SMPLValue<?> v){
        kept = true;
        value = v;
    }
    
    public boolean kept(){
        return kept;
    }
    
    public SMPLValue<?> value(){
        return value;
    }
    
    public <S,T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
    	return v.visitSMPLPromise(this, arg);
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    @Override
    public SMPLType getType() {
        return SMPLType.PROMISE;
    }

    @Override
    public int compareTo(SMPLValue<?> arg) throws SMPLException {
        if(arg.getType().equals(getType())){
            if(((SMPLPromise)arg).getExpression() == expression && ((SMPLPromise)arg).getEnv() == env){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
    
    
}
