package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
import java.util.ArrayList;

/**
 *
 * @author newts
 */
public class ExpApplyProc extends Exp {
    
    ArrayList<Exp> arguments;
    Exp function;
    ExpApplyProc functionApp;
    String variable;

    public ExpApplyProc() {
        super();
    }

    public ExpApplyProc( Exp fn, ArrayList<Exp> args) {
        this.arguments = args;
        this.function = fn;
    }
    
    public ExpApplyProc( String v, ArrayList<Exp> args) {
        this.arguments = args;
        this.variable = v;
    }
    
    public ExpApplyProc( ExpApplyProc fa, ArrayList<Exp> args) {
        this.arguments = args;
        this.functionApp = fa;
    }

    public ArrayList<Exp> getArguments() {
        return arguments;
    }    

    public Exp getFunction() {
        return function;
    }
    
    public ExpApplyProc getFunctionExp() {
        return functionApp;
    }
    
    public String getVariable(){
        return variable;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpApplyProc(this, state);
    }

    @Override
    public String toString() {
        String args;
        int n = arguments.size();
        switch (n) {
            case 0: args = ""; break;
            case 1: args = arguments.get(0).toString(); break;
            default: 
                args = arguments.get(0).toString();
                for (int i = 1; i < n; i++) {
                    args += ", " + arguments.get(i).toString();
                }
        }
        String out;    
        
        if(variable != null){
            out = variable+"(" + args + ")";
        } else if(function != null){
            out = "("+function.toString()+")(" + args + ")";
        } else{
            out = functionApp.toString()+"(" + args + ")";
        }

        if(isNegative()){
            out = "-"+out;
        }
        return out;        
    }

}
