package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public abstract class Exp extends Statement {
    
    protected boolean negative = false;

    public boolean isNegative() {
        return negative;
    }
    
    public void negate(){
        negative = !negative;
    }
    
}
