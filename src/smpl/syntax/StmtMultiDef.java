package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 * Class to represent a variable assignment (definition) in the AST.
 * @author newts
 */
public class StmtMultiDef extends Statement {

    ArrayList<String> vars;
    Exp exp;

    public StmtMultiDef(ArrayList<String> id, Exp e) {
        vars = id;
        exp = e;
    }

    public ArrayList<String> getVars() {
        return vars;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitStmtMutiDef(this, arg);
    }
    
    @Override
    public String toString() {
        String v;
        int n = vars.size();
        switch (n) {
            case 0: v = ""; break;
            case 1: v = vars.get(0); break;
            default: 
                v = vars.get(0);
                for (int i = 1; i < n; i++) {
                    v += ", " + vars.get(i);
                }
        }
        return String.format("%s = %s", v, exp.toString());
    }
}
