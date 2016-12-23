package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
import java.util.ArrayList;

/**
 * Class to represent an AST node for a let expression.
 * @author newts
 */
public class ExpCase extends Exp {
    ArrayList<Case> cases;

    public ExpCase(ArrayList<Case> cs) {
	cases = cs;
    }

    public ArrayList<Case> getCases() {
	return cases;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpCase(this, state);
    }

    @Override
    public String toString() {
        String c;
        int n = cases.size();
        switch (n) {
            case 0: c = ""; break;
            case 1: c = cases.get(0).toString(); break;
            default: 
                c = cases.get(0).toString();
                for (int i = 1; i < n; i++) {
                    c += ", " + cases.get(i).toString();
                }
        }
	return "case {" + c +"}";
    }

}
