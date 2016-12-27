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
public class StmtFor extends Exp 
{

    StmtDefinition def;
    Exp comp, body;
    Statement end;


    public StmtFor(StmtDefinition e1, Exp e2, Statement e3, Exp e4) 
    {
        def = e1;
        comp = e2;
        end = e3;
        body = e4;
    }    
    
    public StmtDefinition getDefinition() 
    {
        return def;
    }

    public Exp getComparison() 
    {
        return comp;
    }
    
    public Statement getEndProcedure()
    {
        return end;
    }
    
    public Exp getBody()
    {
        return body;
    }
    
@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitStmtFor(this, arg);
    }

    @Override
    public String toString() 
    {	
        return String.format("for(%s;%s;%s)%s", def.toString(), comp.toString(), end.toString(), body.toString());
    }
    
}