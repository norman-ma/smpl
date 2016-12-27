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
import javax.swing.text.html.parser.DTDConstants;
import smpl.syntax.Case;
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
import smpl.syntax.StmtFor;
import smpl.syntax.ExpGE;
import smpl.syntax.ExpGT;
import smpl.syntax.ExpGet;
import smpl.syntax.StmtIf;
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
import smpl.syntax.StmtWhile;
import smpl.syntax.Specification;
import smpl.syntax.StmtInc;
import smpl.syntax.StmtMultiDef;
import smpl.syntax.StmtPrint;
import smpl.values.SMPLBoolean;
import smpl.values.SMPLInt;
import smpl.values.SMPLPair;
import smpl.values.SMPLList;
import smpl.values.SMPLPromise;
import smpl.values.SMPLString;
import smpl.values.SMPLTuple;
import smpl.values.SMPLVector;
import smpl.values.TypeSMPLException;

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
	result = SMPLValue.make(0, 10);
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
            return result.mul(SMPLValue.make(-1,10));
        }else{
            return result;
        }
    }

    @Override
    public SMPLValue<?> visitStmtSequence(StmtSequence sseq, Environment<SMPLValue<?>> env)
	throws SMPLException {
	ArrayList<Statement> seq = sseq.getSeq();
	Iterator<Statement> iter = seq.iterator();
	result = SMPLValue.make(0,10); // default result
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
            SMPLProcedure fn;
        
       if(exp.getFunction() != null){
            fn = (SMPLProcedure) exp.getFunction().visit(this,env);
       }else if(exp.getVariable() != null){
            String var = exp.getVariable();                           
            if(env.get(var).getType().equals(SMPLType.PROCEDURE)){ 
                fn = (SMPLProcedure) env.get(var);                  
            } else{
                throw new RuntimeSMPLException(env.get(var).getType()+"("+env.get(var)+") is not callable");
            }           
       } else{
            Exp fa = exp.getFunctionExp();
            
            if(fa.visit(this, env).getType().equals(SMPLType.PROCEDURE)){
                fn = (SMPLProcedure) fa.visit(this, env);                   
            }else{
                throw new RuntimeSMPLException(fa.visit(this, env).getType()+"("+fa.visit(this, env)+") is not callable");
            }
       }
        
        ExpProcedure proc= fn.getProcedure();
        Environment<SMPLValue<?>> closingenv= fn.getClosingEnv();
        
        ArrayList<Exp> args = exp.getArguments();
        int c;
        String id;
        
        String[] vars;
        SMPLValue<?>[] vals;
        
        if(proc.getParameters() == null){
            id = proc.getIdentifier();
            int n = args.size();
            SMPLValue<?>[] a = new SMPLValue<?>[n];
            
            for(int i = 0; i<n; i++){
                a[i] = args.get(i).visit(this, env);
            }
            
            vars = new String[1];
            vals = new SMPLValue<?>[1];
            vars[0] = id;
            vals[0] = SMPLValue.makeList(a);
        }else{
            ArrayList<String> params = proc.getParameters();
            if(proc.getIdentifier() != null){
                c = params.size();
                int n = args.size();
                
                if(n < c){
                    throw new RuntimeSMPLException("Procedure accepts minimum "+c+" arguments. ("+n+" given)");
                }
                
                id = proc.getIdentifier();
                vars = new String[c+1];
                vals = new SMPLValue<?>[c+1];
                for(int i = 0; i <c; i++){
                    vars[i] = params.get(i);
                    vals[i] = args.get(i).visit(this, env);
                }                
                
                SMPLValue<?>[] a = new SMPLValue<?>[n-c];
            
                for(int i = c; i<n; i++){
                    a[i-c] = args.get(i).visit(this, env);
                }
                
                vars[c] = id;
                vals[c] = SMPLValue.makeList(a);                
            }else{
                c = params.size();
                int a = args.size();
                
                if(a != c){
                    throw new RuntimeSMPLException("Procedure accepts exactly "+c+" arguments. ("+a+" given)");
                }
                vars = new String[c];
                vals = new SMPLValue<?>[c];
                for(int i = 0; i <c; i++){
                    vars[i] = params.get(i);
                    vals[i] = args.get(i).visit(this, env);
                }
            }
        }        
        
	Exp body = proc.getBody();

	Environment<SMPLValue<?>> newEnv = new Environment<> (vars, vals, closingenv);
        if(exp.isNegative()){
           return body.visit(this, newEnv).mul(SMPLValue.make(-1,10));
        }else{
            return body.visit(this, newEnv);
        }	
    }

    @Override
    public SMPLValue<?> visitExpAdd(ExpAdd exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
	SMPLValue<?> val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        if(exp.isNegative()){
            return val1.add(val2).mul(SMPLValue.make(-1,10));
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
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(exp.isNegative()){
            return val1.sub(val2).mul(SMPLValue.make(-1,10));
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
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(exp.isNegative()){
            return val1.mul(val2).mul(SMPLValue.make(-1,10));
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
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(exp.isNegative()){
            return val1.div(val2).mul(SMPLValue.make(-1,10));
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
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(exp.isNegative()){
            return val1.mod(val2).mul(SMPLValue.make(-1,10));
        }else{
            return val1.mod(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpLit(ExpLit exp, Environment<SMPLValue<?>> arg)
	throws SMPLException {
        if(exp.isNegative()){
            return exp.getVal().mul(SMPLValue.make(-1,10));
        }else{
            return exp.getVal();
        }	
    }

    @Override
        public SMPLValue<?> visitExpVar(ExpVar exp, Environment<SMPLValue<?>> env)
	throws SMPLException {
        if(exp.isNegative()){
            return env.get(exp.getVar()).mul(SMPLValue.make(-1,10));
        }else{
            return env.get(exp.getVar());
        }	
    } 

    @Override
    public SMPLValue<?> visitExpPow(ExpPow exp, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
	val1 = (SMPLValue) exp.getExpL().visit(this, arg);
	val2 = (SMPLValue) exp.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(exp.isNegative()){
            return val1.pow(val2).mul(SMPLValue.make(-1,10));
        }else{
            return val1.pow(val2);
        }
    }

    @Override
    public SMPLValue<?> visitExpEQ(ExpEQ s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        
        if(val1.compareTo(val2) == 0){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpGE(ExpGE s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }        
        
         if(val1.compareTo(val2) == 1 || val1.compareTo(val2) == 0){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpGT(ExpGT s, Environment<SMPLValue<?>> arg) throws SMPLException {
         SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        if(val1.compareTo(val2) == 1){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpLE(ExpLE s, Environment<SMPLValue<?>> arg) throws SMPLException {
         SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        if(val1.compareTo(val2) == -1 || val1.compareTo(val2) == 0){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpLT(ExpLT s, Environment<SMPLValue<?>> arg) throws SMPLException {
         SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        if(val1.compareTo(val2) == -1){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpNEQ(ExpNEQ s, Environment<SMPLValue<?>> arg) throws SMPLException {
         SMPLValue<?> val1,val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        if(val1.compareTo(val2) == 1 || val1.compareTo(val2) == -1){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpMult(ExpMult s, Environment<SMPLValue<?>> arg) throws SMPLException {
        
        ArrayList<Exp> e = s.getExpressions();
        int n = e.size();
        
        SMPLValue<?>[] a = new SMPLValue<?>[n];
            
        for(int i = 0; i<n; i++){
            a[i] = e.get(i).visit(this, arg);
        }
        
        return SMPLValue.makeTuple(a);          
    }

    @Override
    public SMPLValue<?> visitExpCase(ExpCase s, Environment<SMPLValue<?>> arg) throws SMPLException {
        ArrayList<Case> cases = s.getCases();
        
        for(Case c: cases){
            Exp pred = c.getPredicate();
            Exp cons = c.getConsequent();
            try{
                if(pred.visit(this, arg).booleanValue()){
                    return cons.visit(this, arg);
                }
            }catch(Exception e){
                throw new RuntimeSMPLException(e.getMessage());
            }
        }
        
        return null;
    }

    @Override
    public SMPLValue<?> visitStmtPrint(StmtPrint s, Environment<SMPLValue<?>> arg) throws SMPLException {
        if(s.isLn()){
            System.out.println(s.getExp().visit(this, arg));
        } else{
            System.out.print(s.getExp().visit(this, arg));
        }
        
        return null;
    }

    @Override
    public SMPLValue<?> visitExpCall(ExpCall s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLProcedure proc;
        SMPLList lst;
        if(s.getProc().visit(this, arg).getType().equals(SMPLType.PROCEDURE))
            proc = (SMPLProcedure)s.getProc().visit(this, arg);
        else{
            throw new RuntimeSMPLException("Procedure expected, got "+s.getProc().visit(this, arg).getType().name()+" instead.");
        }
        
        if(s.getArgExps().visit(this, arg).getType().equals(SMPLType.LIST) || s.getArgExps().visit(this, arg).getType().equals(SMPLType.EMPTYLIST))
            lst = (SMPLList)s.getArgExps().visit(this, arg);
        else{
            throw new RuntimeSMPLException("List expected, got "+s.getArgExps().visit(this, arg).getType().name()+" instead.");
        } 
        
        ArrayList<Exp> args = new ArrayList<>();
        while(!lst.getType().equals(SMPLType.EMPTYLIST)){
            args.add(new ExpLit(lst.getCar()));
            lst = (SMPLList)lst.getCdr();
        }
        
        return (new ExpApplyProc(proc.getProcedure(),args)).visit(this,arg);
    }

    @Override
    public SMPLValue<?> visitStmtIf(StmtIf s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLBoolean pred;
        if(s.getExpIf().visit(this, arg).getType().equals(SMPLType.BOOLEAN))
            pred = (SMPLBoolean) s.getExpIf().visit(this, arg);
        else{
            throw new RuntimeSMPLException("Boolean expected, got "+s.getExpIf().visit(this, arg).getType().name()+" instead.");
        }
        
        Exp cons = s.getExpThen();
        Exp alt = null;
        
        if(s.getExpElse() != null){
            alt = s.getExpElse();
        }
        
        if(pred.booleanValue()){
            return cons.visit(this, arg);
        }else if(alt != null){
            return alt.visit(this, arg);
        }
        
        return null;
    }

    @Override
    public SMPLValue<?> visitExpLazy(ExpLazy s, Environment<SMPLValue<?>> arg) throws SMPLException {
        return new SMPLPromise(s.getExp(),arg);
    }

    @Override
    public SMPLValue<?> visitExpRead(ExpRead s, Environment<SMPLValue<?>> arg) throws SMPLException {
        Scanner in = new Scanner(System.in);        
        if(s.isInt()){
           System.out.print("> ");
           int value = in.nextInt();
           return SMPLValue.make(value,10);
        }else{
            System.out.print("> ");
            String value = in.next();
            return SMPLValue.make(value);
        }
    }

    @Override
    public SMPLValue<?> visitExpNot(ExpNot s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1;
        boolean b1;
	val1 = s.getExp().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        
        b1 = val1.booleanValue();
	
        return SMPLValue.make(!b1);
    }

    @Override
    public SMPLValue<?> visitExpAnd(ExpAnd s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
        boolean b1, b2;
	val1 = s.getExpL().visit(this, arg);
	val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        b1 = val1.booleanValue();
        b2 = val2.booleanValue();
	
        return SMPLValue.make(b1 && b2);
    }

    @Override
    public SMPLValue<?> visitExpOr(ExpOr s, Environment<SMPLValue<?>> arg) throws SMPLException {
	SMPLValue<?> val1, val2;
        boolean b1, b2;
	val1 = s.getExpL().visit(this, arg);
	val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
        b1 = val1.booleanValue();
        b2 = val2.booleanValue();
	
        return SMPLValue.make(b1 || b2);
    }

    @Override
    public SMPLValue<?> visitExpBitAnd(ExpBitAnd s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
	val1 = s.getExpL().visit(this, arg);
	val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(s.isNegative()){
            return val1.bitAnd(val2).mul(SMPLValue.make(-1,10));
        }else{
            return val1.bitAnd(val2);
        }        
    }

    @Override
    public SMPLValue<?> visitExpBitOr(ExpBitOr s, Environment<SMPLValue<?>> arg) throws SMPLException {
         SMPLValue<?> val1, val2;
	val1 = s.getExpL().visit(this, arg);
	val2 = s.getExpR().visit(this, arg);
        
        if(val1.getType().equals(SMPLType.PROMISE)){
            val1 = ((SMPLPromise)val1).visit(this, arg);
        }        
        if(val2.getType().equals(SMPLType.PROMISE)){
            val2 = ((SMPLPromise)val2).visit(this, arg);
        }
        
	if(s.isNegative()){
            return val1.bitOr(val2).mul(SMPLValue.make(-1,10));
        }else{
            return val1.bitOr(val2);
        }        
    }

    @Override
    public SMPLValue<?> visitExpComp(ExpComp s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val;
	val = s.getExp().visit(this, arg);
        
        if(val.getType().equals(SMPLType.PROMISE)){
            val = ((SMPLPromise)val).visit(this, arg);
        }        
       
	if(s.isNegative()){
            return val.comp().mul(SMPLValue.make(-1,10));
        }else{
            return val.comp();
        }        
    }

    @Override
    public SMPLValue<?> visitExpCar(ExpCar s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLPair val;
        if(s.getExp().visit(this, arg).getType().equals(SMPLType.BOOLEAN))
            val = (SMPLPair)s.getExp().visit(this, arg);    
        else{
            throw new TypeSMPLException(SMPLType.PAIR, s.getExp().visit(this, arg).getType());
        }     
        return val.getCar();
    }

    @Override
    public SMPLValue<?> visitExpCdr(ExpCdr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLPair val;
        if(s.getExp().visit(this, arg).getType().equals(SMPLType.BOOLEAN))
            val = (SMPLPair)s.getExp().visit(this, arg);    
        else{
            throw new TypeSMPLException(SMPLType.PAIR, s.getExp().visit(this, arg).getType());
        }   
        return val.getCdr();
    }

    @Override
    public SMPLValue<?> visitExpIsEql(ExpIsEql s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        if(val1.compareTo(val2) == 0){
            return SMPLValue.make(true);
        }else{
            return SMPLValue.make(false);
        }
    }

    @Override
    public SMPLValue<?> visitExpIsEqv(ExpIsEqv s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
        
        val1 = s.getExpL().visit(this, arg);
        val2 = s.getExpR().visit(this, arg);
        
        return SMPLValue.make(val1 == val2);
    }

    @Override
    public SMPLValue<?> visitExpIsPair(ExpIsPair s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val = s.getExp().visit(this, arg);        
        return SMPLValue.make(val.getType().equals(SMPLType.PAIR));
    }

    @Override
    public SMPLValue<?> visitExpList(ExpList s, Environment<SMPLValue<?>> arg) throws SMPLException {
        ArrayList<Exp> items = s.getExpList();
        int len = items.size();
        SMPLValue<?>[] values = new SMPLValue<?>[len];
        
        for(int i = 0; i < len; i++){
            values[i] = items.get(i).visit(this, arg);
        }
        
        return SMPLValue.makeList(values);
    }

    @Override
    public SMPLValue<?> visitExpPair(ExpPair s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue<?> val1, val2;
	val1 = s.getExpL().visit(this, arg);
	val2 = s.getExpR().visit(this, arg);
        
        return SMPLValue.makePair(val1,val2);
    }

    @Override
    public SMPLValue<?> visitExpSize(ExpSize s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLVector vec;
        if(s.getExp().visit(this, arg).getType().equals(SMPLType.VECTOR))
            vec = (SMPLVector)s.getExp().visit(this, arg);
        else{
            throw new TypeSMPLException(SMPLType.VECTOR, s.getExp().visit(this, arg).getType());
        }   
        
        return SMPLValue.make(vec.size(),10);
    }

    @Override
    public SMPLValue<?> visitExpSubstr(ExpSubstr s, Environment<SMPLValue<?>> arg) throws SMPLException {
        
        SMPLString val = (SMPLString)s.getStrExpression().visit(this, arg);
        int hi = s.getHigh().visit(this, arg).intValue();
        int lo = s.getLow().visit(this, arg).intValue();        
              
        return val.substring(lo, hi);
    }

    @Override
    public SMPLValue<?> visitExpVector(ExpVector s, Environment<SMPLValue<?>> arg) throws SMPLException {
        ArrayList<Specification> items = s.getExpList();
        int len = items.size();
        SMPLValue<?>[] values = new SMPLValue<?>[len];
        
        for(int i = 0; i < len; i++){
            values[i] = items.get(i).visit(this,arg);
        }
        
        return SMPLValue.makeVector(values);
    }

    @Override
    public SMPLValue<?> visitExpConcat(ExpConcat s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLString val1,val2;
        if(s.getExpL().visit(this, arg).getType().equals(SMPLType.BOOLEAN))
            val1 = (SMPLString)s.getExpL().visit(this, arg);    
        else{
            throw new TypeSMPLException(SMPLType.STRING, s.getExpL().visit(this, arg).getType());
        } 
        
        val2 = (SMPLString) s.getExpR().visit(this, arg);
        
        return val1.concat(val2);
    }

    @Override
    public SMPLValue<?> visitExpGet(ExpGet s, Environment<SMPLValue<?>> arg) throws SMPLException {
        SMPLValue v,i;
        SMPLVector vec;
        
        v = s.getVector().visit(this, arg);
        i = s.getIndex().visit(this, arg);
        
        if(v.getType().equals(SMPLType.PROMISE)){
            v = ((SMPLPromise)v).visit(this, arg);
        } 
        
        if(i.getType().equals(SMPLType.PROMISE)){
            i = ((SMPLPromise)i).visit(this, arg);
        } 
        
        if(v.getType().equals(SMPLType.VECTOR))
            vec = (SMPLVector)v;
        else{
            throw new TypeSMPLException(SMPLType.VECTOR, v.getType());
        }  
        
        int index = i.intValue();
     
        return vec.get(index);
    }

    @Override
    public SMPLValue<?> visitSpecification(Specification s, Environment<SMPLValue<?>> arg) throws SMPLException {
        Exp e;
        int count;
        SMPLProcedure proc;
        
        if(s.getExpression() != null){
            e = s.getExpression();
            return e.visit(this, arg);
        }else{
            count = s.getNumber().visit(this, arg).intValue();            

            if(s.getProcedure().visit(this, arg).getType().equals(SMPLType.PROCEDURE))
                proc = (SMPLProcedure) s.getProcedure().visit(this, arg);
            else{
                throw new TypeSMPLException(SMPLType.PROCEDURE, s.getProcedure().visit(this, arg).getType());
            }  
            
            ArrayList<SMPLValue> values = new ArrayList<>();
            for(int i = 0; i < count; i++){
                ArrayList<Exp> args = new ArrayList<>();
                args.add(new ExpLit(SMPLValue.make(i,10)));
                ExpApplyProc app = new ExpApplyProc(proc.getProcedure(), args);
                values.add(app.visit(this, arg));
            }
            int n = values.size();
            SMPLValue<?>[] vals = new SMPLValue<?>[n];
            
            for(int i = 0; i < n; i++){
                vals[i] = values.get(i);
            }
            
            return SMPLValue.makeSubVector(vals);
        }        
    }    

    @Override
    public SMPLValue visitSMPLPromise(SMPLPromise s,Environment<SMPLValue<?>> arg) throws SMPLException {
        if(s.kept()){
            return s.value();
        }else{
            s.keep(s.getExpression().visit(this,s.getEnv()));
            return s.value();
        }
    }

    @Override
    public SMPLValue<?> visitStmtFor(StmtFor s, Environment<SMPLValue<?>> arg) throws SMPLException {
        StmtDefinition d = s.getDefinition();
        Exp comp = s.getComparison();
        Statement end = s.getEndProcedure();
        Exp body = s.getBody();
        
        Environment<SMPLValue<?>> loopenv = new Environment<>(new String[0], new SMPLValue<?>[0], arg);
        d.visit(this, loopenv);
        
        int i = loopenv.get(d.getVar()).intValue();
        
        if(comp.visit(this, loopenv).getType().equals(SMPLType.BOOLEAN))
            for(int c = i; comp.visit(this, loopenv).booleanValue(); end.visit(this, loopenv)){
                result = body.visit(this, loopenv);
            }
        else{
            throw new TypeSMPLException(SMPLType.BOOLEAN, comp.visit(this, loopenv).getType());
        }  
        
        return result;
    }
    
    @Override
    public SMPLValue<?> visitStmtWhile(StmtWhile s, Environment<SMPLValue<?>> arg) throws SMPLException {
        Exp comp = s.getComparison();
        Exp body = s.getBody();
        if(comp.visit(this, arg).getType().equals(SMPLType.BOOLEAN))
            while(comp.visit(this, arg).booleanValue()){
                result = body.visit(this, arg);
            }
        else{
            throw new TypeSMPLException(SMPLType.BOOLEAN, comp.visit(this, arg).getType());
        }  
        
        return result;
    }

    @Override
    public SMPLValue<?> visitStmtInc(StmtInc s, Environment<SMPLValue<?>> state) throws SMPLException {
        String id = s.getIdentifier();
        Exp valExp = s.getValExp();
        
        SMPLValue<?> current = state.get(id);
        
        SMPLValue<?> newVal = current.add(valExp.visit(this, state));
        
        state.put(id, newVal);
        
        return newVal;
    }

    @Override
    public SMPLValue<?> visitStmtMutiDef(StmtMultiDef a, Environment<SMPLValue<?>> arg) throws SMPLException {
       
        ArrayList<String> vars = a.getVars();
        SMPLValue<?> v = a.getExp().visit(this, arg);
        SMPLTuple vals;
        
        if(v.getType().equals(SMPLType.PROMISE)){
            v = ((SMPLPromise)v).visit(this, arg);
        } 
         
        if(v.getType().equals(SMPLType.TUPLE)){
            vals = (SMPLTuple)v;
        }else{
            throw new TypeSMPLException(SMPLType.TUPLE,v.getType());
        }
        
        if(vars.size() == vals.size()){        
            for(int i = 0; i < vars.size(); i++){
                SMPLValue<?> r = vals.get(i);
                arg.put(vars.get(i), r);
            }
        }else{
            throw new RuntimeSMPLException(vars.size()+" values expected but got "+ vals.size());
        }       
    
        result = vals;
	return result;
    }
}
