package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpSize extends Exp {

    Exp exp1;

    public ExpSize(Exp e1) {
        exp1 = e1;
    }

    public Exp getExp() {
        return exp1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpSize(this, arg);
    }

    @Override
    public String toString() {
       return "size(" + exp1.toString() + ")";
    }
}
