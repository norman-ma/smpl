/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import smpl.semantics.Environment;
import smpl.syntax.ExpProcedure;

/**
 *
 * @author namro_000
 */
public class SMPLProcedure extends SMPLValue<SMPLProcedure>{
    
    ExpProcedure procedure;
    Environment<SMPLValue<?>> closingEnv;

    public SMPLProcedure(ExpProcedure p, Environment<SMPLValue<?>> e){
        procedure = p;
        closingEnv = e;
    }
    
    public ExpProcedure getProcedure(){
        return procedure;
    }
    
    public Environment<SMPLValue<?>> getClosingEnv(){
        return closingEnv;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.PROCEDURE;
    }
    
    public String toString(){
        return procedure.toString();
    }
}
