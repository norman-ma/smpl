/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 *
 * @author anon
 */
public class ExpCall extends Exp {
    private final Exp proc;
    private final Exp argExps;

    public ExpCall(Exp exp1, Exp exp2) {
        this.proc = exp1;
        this.argExps = exp2;
    }

    public Exp getProc() {
        return this.proc;
    }

    public Exp getArgExps() {
        return this.argExps;
    }    
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S context) throws SMPLException {
        return v.visitExpCall(this, context);
    }
    
    public String toString(){
        return "";
    }
    
}