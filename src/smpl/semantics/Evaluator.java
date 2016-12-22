package smpl.semantics;

import java.util.*;
import smpl.syntax.ExpAdd;
import smpl.syntax.ExpApplyProc;
import smpl.syntax.ExpDiv;
import smpl.syntax.ExpLit;
import smpl.syntax.ExpMod;
import smpl.syntax.ExpMul;
import smpl.syntax.ExpPow;
import smpl.syntax.ExpProcedure;
import smpl.syntax.ExpSub;
import smpl.syntax.ExpVar;
import smpl.syntax.SMPLProgram;
import smpl.syntax.StmtDefinition;
import smpl.syntax.StmtLet;
import smpl.syntax.StmtSequence;
import smpl.sys.SMPLException;
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

    @Override
    public SMPLValue<?> visitSMPLProgram(SMPLProgram p, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitStmtSequence(StmtSequence s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitStmtDefinition(StmtDefinition s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpAdd(ExpAdd exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpSub(ExpSub exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpMul(ExpMul exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpDiv(ExpDiv exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpMod(ExpMod exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpPow(ExpPow exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpProcedure(ExpProcedure exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpApplyProc(ExpApplyProc exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpVar(ExpVar exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpLit(ExpLit exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitStmtLet(StmtLet s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
