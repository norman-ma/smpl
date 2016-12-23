package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class Specification extends Exp{

    Exp exp1, exp2, exp3;

    public Specification(Exp a, Exp b) {
	exp1 = a;
	exp2 = b;
    }
    
     public Specification(Exp a) {
	exp3 = a;
    }

    public Exp getNumber() {
	return exp1;
    }

    public Exp getProcedure() {
	return exp2;
    }
    
    public Exp getExpression(){
        return exp3;
    }
    
    @Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitSpecification(this, arg);
    }
    
    public String toString(){
        return String.format("%s : %s",exp1.toString(),exp2.toString());
    }

}
