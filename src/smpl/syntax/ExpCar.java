package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpCar extends Exp {

    Exp exp1;

    public ExpCar(Exp e1) {
        exp1 = e1;
    }

    public Exp getExp() {
        return exp1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpCar(this, arg);
    }

    @Override
    public String toString() {
       return "car(" + exp1.toString() + ")";
    }
}
