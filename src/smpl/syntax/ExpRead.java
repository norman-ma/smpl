package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 * Class to represent multiplication expression in the AST.
 * @author newts
 */
public class ExpRead extends Exp {

    boolean i;
    
    public ExpRead() {
      i=false;
    }
    
    public ExpRead(boolean b) {
      i=b;
    }

    public boolean isInt(){
        return i;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpRead(this, arg);
    }

    @Override
    public String toString() {
        return "";
    }
}
