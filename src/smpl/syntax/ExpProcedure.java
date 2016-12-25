package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
import java.util.ArrayList;

/**
 *
 * @author newts
 */
public class ExpProcedure extends Exp {
    
    ArrayList<String> parameters;
    String  identifier;
    
    Exp body;

    public ExpProcedure() {
        super();
    }

    public ExpProcedure(ArrayList<String> parameters, Exp body) {
        this.parameters = parameters;
        this.body = body;
    }
    
    public ExpProcedure(ArrayList<String> parameters, String v, Exp body) {
        this.parameters = parameters;
        this.identifier = v;
        this.body = body;
    }
    
    public ExpProcedure(String v, Exp body) {
        this.identifier = v;
        this.body = body;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }
    
    public String getIdentifier(){
        return identifier;
    }

    public Exp getBody() {
        return body;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpProcedure(this, state);
    }

    @Override
    public String toString() {
        String params;
        
        if(parameters == null){
            return "proc "+identifier+" "+body.toString();
        }else{
            int n = parameters.size();
            switch (n) {
                case 0: params = ""; break;
                case 1: params = parameters.get(0); break;
                default: 
                    params = parameters.get(0);
                    for (int i = 1; i < n; i++) {
                        params += ", " + parameters.get(i);
                    }
            }
            if(identifier == null){
                return "proc ("+params+") "+body.toString();
            } else {
                return "proc ("+params+" . "+ identifier +") "+ body.toString();
            }
        }
    }

}
