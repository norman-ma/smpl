package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
import java.util.ArrayList;

/**
 * Class to represent an AST node for a let expression.
 * @author newts
 */
public class StmtLet extends Exp {
    ArrayList<Binding> bindings;
    Exp body;

    public StmtLet(ArrayList<Binding> bs, Exp bod) {
	bindings = bs;
	body = bod;
    }

    public ArrayList<Binding> getBindings() {
	return bindings;
    }

    public Exp getBody() {
	return body;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitStmtLet(this, state);
    }

    @Override
    public String toString() {
        String binds;
        int n = bindings.size();
        switch (n) {
            case 0: binds = ""; break;
            case 1: binds = bindings.get(0).toString(); break;
            default: 
                binds = bindings.get(0).toString();
                for (int i = 1; i < n; i++) {
                    binds += ", " + bindings.get(i).toString();
                }
        }
	return "let " + binds + " in " + body.toString();
    }

}
