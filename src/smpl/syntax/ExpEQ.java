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
 * @author romariotomlin
 */
public class ExpEQ extends Exp {

    Exp exp1;
    Exp exp2;

    public ExpEQ(Exp e1, Exp e2) {
        exp1 = e1;
        exp2 = e2;
    }

    public Exp getExpL() {
        return exp1;
    }

    public Exp getExpR() {
        return exp2;
    }
    
@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitExpEQ(this, arg);
    }

    @Override
    public String toString() {	
        if(isNegative()){
            return "-("+exp1.toString() + " = " + exp2.toString()+")";
        }else{
            return exp1.toString() + " = " + exp2.toString();
        }
    }
}