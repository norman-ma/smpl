package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpSubstr extends Exp {

    Exp exp1, exp2, exp3;

    public ExpSubstr(Exp e1, Exp e2, Exp e3) {
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
        
    }

    public Exp getStrExpression() {
        return exp1;
    }

    public Exp getLow() {
        return exp2;
    }
    
    public Exp getHigh() {
        return exp3;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpSubstr(this, arg);
    }

    @Override
    public String toString() {
       return "substr("+exp1.toString()+", "+exp2.toString()+", "+exp3.toString()+")";
    }
}
