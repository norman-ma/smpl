/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public class StmtInc extends Statement{
    
    String identifier;
    Exp value;
    
    public StmtInc(String id, Exp val){
        identifier = id;
        value = val;
    }
    
    public String getIdentifier(){
        return identifier;
    }
    
    public Exp getValExp(){
        return value;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitStmtInc(this, state);
    }

    @Override
    public String toString() {
        return identifier + " += " + value.toString();
    }
    
    
    
}
