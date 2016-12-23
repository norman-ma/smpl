package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpComp extends Exp {

    Exp exp1;

    public ExpComp(Exp e1) {
        exp1 = e1;
    }

    public Exp getExp() {
        return exp1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpComp(this, arg);
    }

    @Override
    public String toString() {
       return "~ " + exp1.toString();
    }
}
