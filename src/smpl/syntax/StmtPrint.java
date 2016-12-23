package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 * Class to represent a variable assignment (definition) in the AST.
 * @author newts
 */
public class StmtPrint extends Statement {

    Exp exp;
    boolean ln;
    
    public StmtPrint(Exp e) {
        exp = e;
        ln = false;
    }
    
    public StmtPrint(Exp e,boolean b){
        exp = e;
        ln = b;
    }    

    public Exp getExp() {
        return exp;
    }
    
    public boolean isLn(){
        return ln;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitStmtPrint(this, arg);
    }
    
    @Override
    public String toString() {
        return exp.toString();
    }
}
