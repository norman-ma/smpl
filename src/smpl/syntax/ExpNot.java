package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpNot extends Exp {

    Exp exp;

    public ExpNot(Exp e) {
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpNot(this, arg);
    }

    @Override
    public String toString() {
       return "not "+exp.toString();
    }
}
