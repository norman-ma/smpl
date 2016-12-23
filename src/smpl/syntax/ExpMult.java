package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

/**
 * Class to represent modulo (%) expression in the AST.
 * @author newts
 */
public class ExpMult extends Exp {

    ArrayList<Exp> exps;

    public ExpMult(ArrayList<Exp> e) {
        exps = e;
    }

    public ArrayList<Exp> getExpressions() {
        return exps;
    }
    
    public Exp getExpression(int i){
        return exps.get(i);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpMult(this, arg);
    }

    @Override
     public String toString() {
        String e;
        int n = exps.size();
        switch (n) {
            case 1: e = exps.get(0).toString(); break;
            default: 
                e = exps.get(0).toString();
                for (int i = 1; i < n; i++) {
                    e += ", " + exps.get(i);
                }
        }
        
        return "("+e+")";
    }
}
