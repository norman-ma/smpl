package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 * Class to represent modulo (%) expression in the AST.
 * @author newts
 */
public class ExpMod extends Exp {

    Exp exp1;
    Exp exp2;

    public ExpMod(Exp e1, Exp e2) {
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
        return v.visitExpMod(this, arg);
    }

    @Override
    public String toString() {
        if(isNegative()){
            return "-("+exp1.toString() + " % " + exp2.toString()+")";
        }else{
            return exp1.toString() + " % " + exp2.toString();
        }
    }
}
