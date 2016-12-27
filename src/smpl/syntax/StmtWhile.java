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
public class StmtWhile extends Exp 
{

    Exp comp, body;


    public StmtWhile(Exp e1, Exp e2) 
    {
        comp = e1;
        body = e2;
    }    
    
    public Exp getComparison() 
    {
        return comp;
    }
    
    public Exp getBody()
    {
        return body;
    }
    
@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitStmtWhile(this, arg);
    }

    @Override
    public String toString() 
    {	
        return String.format("while(%s)%s",comp.toString(),body.toString());
    }
    
}