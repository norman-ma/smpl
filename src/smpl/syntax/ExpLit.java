package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
import smpl.values.SMPLValue;

/**
 * Class to represent numerical literals in the AST.
 * @author newts
 */
public class ExpLit extends Exp {

    SMPLValue<?> val;
    
    public ExpLit(SMPLValue<?> v) {
        val = v;
    }

    public ExpLit(Integer v) {
        val = SMPLValue.make(v);
    }
    
    public ExpLit(Double v) {
        val = SMPLValue.make(v);
    }

    public ExpLit(String v){
        val = SMPLValue.make(v);
    }
    
    public ExpLit(Character v){
        val = SMPLValue.make(v);
    }
    
    public ExpLit(Boolean v){
        val = SMPLValue.make(v);
    }
    
     public ExpLit(){
        val = SMPLValue.make();
    }
    
    public SMPLValue<?> getVal() {
        return val;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpLit(this, arg);
    }

    @Override
    public String toString() {
        if(isNegative()){
            return "-"+val.toString();
        }else{
            return val.toString();
        }
    }
}
