package smpl.semantics;

import java.util.*;
import smpl.values.SMPLValue;

public class Evaluator 
    implements Visitor<Environment<SMPLValue<?>>, SMPLValue<?>> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SMPLValue<?> result;	// result of evaluation

    /**
     * The global environment associated with this evaluator.
     */
    protected Environment<SMPLValue<?>> globalEnv;
    
    /**
     * The plotting device used by this interpreter.
     */
    
    public Evaluator() {
	// perform initialisations here
	result = SMPLValue.make(0);
        globalEnv = new Environment<>();
    }
    
    /**
     * @return The global environment used by this evaluator.  This will be the
     * parent environemnt of all environments that might arise during the 
     * tree walk of an AST that this Evaluator instance may perform.
     */
    public Environment<SMPLValue<?>> getGlobalEnv() {
        return globalEnv;
    }  
    
}
