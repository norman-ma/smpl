package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class ExpVar extends Exp {

    String var;

    public ExpVar(String id) {
	var = id;
    }

    public String getVar() {
	return var;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	return v.visitExpVar(this, arg);
    }

    @Override
    public String toString() {
	
        if(isNegative()){
            return "-"+ var;
        }else{
            return var;
        }
    }
}
