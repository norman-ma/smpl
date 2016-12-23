package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpGet extends Exp {

    Exp exp1, exp2;

    public ExpGet(Exp e1, Exp e2) {
        exp1 = e1;
        exp2 = e2;
    }

    public Exp getVector() {
        return exp1;
    }

    public Exp getIndex() {
        return exp2;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpGet(this, arg);
    }

    @Override
    public String toString() {
       return exp1.toString()+" and "+exp2.toString();
    }
}
