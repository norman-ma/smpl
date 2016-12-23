package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpCdr extends Exp {

    Exp exp1;

    public ExpCdr(Exp e1) {
        exp1 = e1;
    }

    public Exp getExp() {
        return exp1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpCdr(this, arg);
    }

    @Override
    public String toString() {
       return "cdr(" + exp1.toString() + ")";
    }
}
