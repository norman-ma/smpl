package smpl.semantics;

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

/**
 * The generic Visitor interface for the Arithmetic parser
 * example.
 * @param <S> The type of the information needed by the visitor
 * @param <T> The type of result returned by the visitor 
 */
public interface Visitor<S, T> {
    
    public T visitSMPLProgram(SMPLProgram p, S arg)throws SMPLException;
    
    public T visitStmtSequence(StmtSequence s, S arg)throws SMPLException;
    
    public T visitStmtDefinition(StmtDefinition s, S arg)throws SMPLException;
    
    public T visitExpAdd(ExpAdd exp, S arg)throws SMPLException;
    
    public T visitExpSub(ExpSub exp, S arg)throws SMPLException;
    
    public T visitExpMul(ExpMul exp, S arg)throws SMPLException;
    
    public T visitExpDiv(ExpDiv exp, S arg)throws SMPLException;
    
    public T visitExpMod(ExpMod exp, S arg)throws SMPLException;
    
    public T visitExpPow(ExpPow exp, S arg)throws SMPLException;
    
    public T visitExpProcedure(ExpProcedure exp, S arg)throws SMPLException;
    
    public T visitExpApplyProc(ExpApplyProc exp, S arg)throws SMPLException;
    
    public T visitExpVar(ExpVar exp, S arg)throws SMPLException;
    
    public T visitExpLit(ExpLit exp, S arg)throws SMPLException;
    
    public T visitStmtLet(StmtLet s, S arg)throws SMPLException;
}
