package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;


public class ExpLazy extends Exp {

	Exp exp1;
	
	public ExpLazy(Exp expr) {
		this.exp1 = expr;	
	}
	
	public Exp getExp() {
		return exp1;
	}
		
	
	
	@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitExpLazy(this, arg);
    }

    @Override
    public String toString() {	
        return "lazy(" +exp1.toString() + ")";
        
    }
    
}