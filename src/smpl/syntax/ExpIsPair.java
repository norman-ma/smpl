package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpIsPair extends Exp {

    Exp exp1;

    public ExpIsPair(Exp e1) {
        exp1 = e1;
    }

    public Exp getExp() {
        return exp1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpIsPair(this, arg);
    }

    @Override
    public String toString() {
       return "pair?(" + exp1.toString() + ")";
    }
}
