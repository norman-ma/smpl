package smpl.semantics;

import smpl.syntax.StmtLet;
import smpl.syntax.Statement;
import smpl.syntax.StmtDefinition;
import smpl.syntax.StmtSequence;
import smpl.syntax.ExpLit;
import smpl.syntax.ExpDiv;
import smpl.syntax.ExpMul;
import smpl.syntax.ExpAdd;
import smpl.syntax.ExpVar;
import smpl.syntax.ExpMod;
import smpl.syntax.ExpSub;
import smpl.syntax.Binding;
import smpl.syntax.SMPLProgram;
import smpl.syntax.Exp;
import smpl.syntax.ExpApplyProc;
import smpl.syntax.ExpProcedure;
import smpl.sys.SMPLException;
import smpl.values.SMPLValue;
import smpl.values.SMPLProcedure;
import smpl.sys.RuntimeSMPLException;
import smpl.values.SMPLType;
import java.awt.geom.Point2D;
import java.util.*;
import smpl.syntax.ExpAnd;
import smpl.syntax.ExpBitAnd;
import smpl.syntax.ExpBitOr;
import smpl.syntax.ExpCall;
import smpl.syntax.ExpCar;
import smpl.syntax.ExpCase;
import smpl.syntax.ExpCdr;
import smpl.syntax.ExpComp;
import smpl.syntax.ExpConcat;
import smpl.syntax.ExpEQ;
import smpl.syntax.ExpGE;
import smpl.syntax.ExpGT;
import smpl.syntax.ExpGet;
import smpl.syntax.ExpIf;
import smpl.syntax.ExpIsEql;
import smpl.syntax.ExpIsEqv;
import smpl.syntax.ExpIsPair;
import smpl.syntax.ExpLE;
import smpl.syntax.ExpLT;
import smpl.syntax.ExpLazy;
import smpl.syntax.ExpList;
import smpl.syntax.ExpMult;
import smpl.syntax.ExpNEQ;
import smpl.syntax.ExpNot;
import smpl.syntax.ExpOr;
import smpl.syntax.ExpPair;
import smpl.syntax.ExpPow;
import smpl.syntax.ExpRead;
import smpl.syntax.ExpSize;
import smpl.syntax.ExpSubstr;
import smpl.syntax.ExpVector;
import smpl.syntax.StmtPrint;

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

       /**
     * Visit a node representing the overall program.  This will be similar to
     * visiting the sequence of statements that make up the program, but is
     * provided as a separate method so that any top-level, one-time actions 
     * can be taken to initialise the context for the program, if necessary.
     * @param p The program node to be traversed.
     * @param arg The environment to be used while traversing the program.
     * @return The result of the last statement of the program, after evaluating
     * all the preceding ones in order.
     * @throws FnPlotException if any of the statements in the body of the 
     * program throws an exception.
     */
    @Override
    public SMPLValue<?> visitSMPLProgram(SMPLProgram p, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	result = p.getSeq().visit(this, arg);
        if(p.isNegative()){
            return result.mul(SMPLValue.make(-1));
        }else{
            return result;
        }
    }

    @Override
    public SMPLValue<?> visitStmtSequence(StmtSequence sseq, Environment<SMPLValue<?>> env)
	throws SMPLException {
	ArrayList<Statement> seq = sseq.getSeq();
	Iterator<Statement> iter = seq.iterator();
	result = SMPLValue.make(0); // default result
        for (Statement s : seq) {
            result = s.visit(this, env);
        }
	// return last value evaluated
	return result;
    }

    @Override
    public SMPLValue<?> visitStmtDefinition(StmtDefinition sd, Environment<SMPLValue<?>> env)
	throws SMPLException {
	result = sd.getExp().visit(this, env);
	env.put(sd.getVar(), result);
	return result;
    }

    @Override
    public SMPLValue<?> visitStmtLet(StmtLet let, Environment<SMPLValue<?>> env) 
	throws SMPLException {
	ArrayList<Binding> bindings = let.getBindings();
	Exp body = let.getBody();

	int size = bindings.size();
	String[] vars = new String[size];
	SMPLValue<?>[] vals = new SMPLValue<?>[size];
	Binding b;
	for (int i = 0; i < size; i++) {
	    b = bindings.get(i);
	    vars[i] = b.getVar();
	    // evaluate each expression in bindings
	    result = b.getValExp().visit(this, env);
	    vals[i] = result;
	}
	// create new env as child of current
	Environment<SMPLValue<?>> newEnv = new Environment<> (vars, vals, env);
	return body.visit(this, newEnv);
    }
    
    @Override
     public SMPLProcedure visitExpProcedure(ExpProcedure fn, Environment<SMPLValue<?>> env)
        throws SMPLException {
        return new SMPLProcedure(fn,env);          
     }
    
    @Override
    public SMPLValue<?> visitExpApplyProc(ExpApplyProc exp, Environment<SMPLValue<?>> env)
        throws SMPLException{
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpAdd(ExpAdd exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
        if(exp.isNegative()){
            return val1.add(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.add(val2);
        }	
    }

    @Override
    public SMPLValue<?> visitExpSub(ExpSub exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	if(exp.isNegative()){
            return val1.sub(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.sub(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpMul(ExpMul exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = (SMPLValue) exp.getExpL().visit(this, arg);
	val2 = (SMPLValue) exp.getExpR().visit(this, arg);
	if(exp.isNegative()){
            return val1.mul(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.mul(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpDiv(ExpDiv exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = (SMPLValue) exp.getExpL().visit(this, arg);
	val2 = (SMPLValue) exp.getExpR().visit(this, arg);
	if(exp.isNegative()){
            return val1.div(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.div(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpMod(ExpMod exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = (SMPLValue) exp.getExpL().visit(this, arg);
	val2 = (SMPLValue) exp.getExpR().visit(this, arg);
	if(exp.isNegative()){
            return val1.mod(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.mod(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpLit(ExpLit exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
        if(exp.isNegative()){
            return exp.getVal().mul(SMPLValue.make(-1));
        }else{
            return exp.getVal();
        }	
    }

    @Override
        public SMPLValue<?> visitExpVar(ExpVar exp, Environment<SMPLValue<?>> env)
	throws SMPLException {
        if(exp.isNegative()){
            return env.get(exp.getVar()).mul(SMPLValue.make(-1));
        }else{
            return env.get(exp.getVar());
        }	
    } 

    @Override
    public SMPLValue<?> visitExpPow(ExpPow exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
	val1 = (SMPLValue) exp.getExpL().visit(this, arg);
	val2 = (SMPLValue) exp.getExpR().visit(this, arg);
	if(exp.isNegative()){
            return val1.pow(val2).mul(SMPLValue.make(-1));
        }else{
            return val1.pow(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpEQ(ExpEQ s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpGE(ExpGE s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpGT(ExpGT s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpLE(ExpLE s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpLT(ExpLT s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpNEQ(ExpNEQ s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpMult(ExpMult s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpCase(ExpCase s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitStmtPrint(StmtPrint s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpCall(ExpCall s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpIf(ExpIf s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpLazy(ExpLazy s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpRead(ExpRead s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpNot(ExpNot s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpAnd(ExpAnd s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpOr(ExpOr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpBitAnd(ExpBitAnd s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpBitOr(ExpBitOr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpComp(ExpComp s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpCar(ExpCar s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpCdr(ExpCdr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpIsEql(ExpIsEql s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpIsEqv(ExpIsEqv s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpIsPair(ExpIsPair s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpList(ExpList s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpPair(ExpPair s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpSize(ExpSize s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpSubstr(ExpSubstr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpVector(ExpVector s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpConcat(ExpConcat s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMPLValue<?> visitExpGet(ExpGet s, Environment<SMPLValue<?>> arg) throws SMPLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
