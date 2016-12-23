package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpConcat extends Exp {

    Exp exp1, exp2;

    public ExpConcat(Exp e1, Exp e2) {
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
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpConcat(this, arg);
    }

    @Override
    public String toString() {
       return exp1.toString()+" @ "+exp2.toString();
    }
}
