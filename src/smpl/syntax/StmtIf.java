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
public class StmtIf extends Exp 
{

    Exp exp1;
    Exp exp2;
    Exp exp3;

    public StmtIf(Exp e1, Exp e2) 
    {
        exp1 = e1;
        exp2 = e2;
    }
    
    public StmtIf(Exp e1, Exp e2, Exp e3)
    {
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    public Exp getExpIf() 
    {
        return exp1;
    }

    public Exp getExpThen() 
    {
        return exp2;
    }
    
    public Exp getExpElse()
    {
        return exp3;
    }
    
@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitStmtIf(this, arg);
    }

    @Override
    public String toString() 
    {	
            String a = "if " + exp1.toString() + " then " + exp2.toString()+")";
            String b = "if " + exp1.toString() + " then " + exp2.toString()+ " else "+ exp3.toString();
            
            if (exp3 == null)
            {
                return a;
            }else{
                return b;
            }
    }
    
}