package smpl.semantics;

import smpl.syntax.ExpAdd;
import smpl.syntax.ExpAnd;
import smpl.syntax.ExpApplyProc;
import smpl.syntax.ExpBitAnd;
import smpl.syntax.ExpBitOr;
import smpl.syntax.ExpCall;
import smpl.syntax.ExpCar;
import smpl.syntax.ExpCase;
import smpl.syntax.ExpCdr;
import smpl.syntax.ExpComp;
import smpl.syntax.ExpConcat;
import smpl.syntax.ExpDiv;
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
import smpl.syntax.ExpLit;
import smpl.syntax.ExpMod;
import smpl.syntax.ExpMul;
import smpl.syntax.ExpMult;
import smpl.syntax.ExpNEQ;
import smpl.syntax.ExpNot;
import smpl.syntax.ExpOr;
import smpl.syntax.ExpPair;
import smpl.syntax.ExpPow;
import smpl.syntax.ExpProcedure;
import smpl.syntax.ExpRead;
import smpl.syntax.ExpSize;
import smpl.syntax.ExpSub;
import smpl.syntax.ExpSubstr;
import smpl.syntax.ExpVar;
import smpl.syntax.ExpVector;
import smpl.syntax.SMPLProgram;
import smpl.syntax.StmtDefinition;
import smpl.syntax.StmtLet;
import smpl.syntax.StmtPrint;
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
    
    public T visitExpEQ(ExpEQ s, S arg)throws SMPLException;
    
    public T visitExpGE(ExpGE s, S arg)throws SMPLException;
    
    public T visitExpGT(ExpGT s, S arg)throws SMPLException;
    
    public T visitExpLE(ExpLE s, S arg)throws SMPLException;
    
    public T visitExpLT(ExpLT s, S arg)throws SMPLException;
    
    public T visitExpNEQ(ExpNEQ s, S arg)throws SMPLException;
    
    public T visitExpMult(ExpMult s, S arg)throws SMPLException;
    
    public T visitExpCase(ExpCase s, S arg)throws SMPLException;
   
    public T visitStmtPrint(StmtPrint s, S arg)throws SMPLException;
    
    public T visitExpCall(ExpCall s, S arg)throws SMPLException;
    
    public T visitExpIf(ExpIf s, S arg)throws SMPLException;
    
    public T visitExpLazy(ExpLazy s, S arg)throws SMPLException;
    
    public T visitExpRead(ExpRead s, S arg)throws SMPLException;   
    
    public T visitExpNot(ExpNot s, S arg)throws SMPLException;  
    
    public T visitExpAnd(ExpAnd s, S arg)throws SMPLException;
    
    public T visitExpOr(ExpOr s, S arg)throws SMPLException;   
    
    public T visitExpBitAnd(ExpBitAnd s, S arg)throws SMPLException;
    
    public T visitExpBitOr(ExpBitOr s, S arg)throws SMPLException;   
    
    public T visitExpComp(ExpComp s, S arg)throws SMPLException;
    
    public T visitExpCar(ExpCar s, S arg)throws SMPLException;
    
    public T visitExpCdr(ExpCdr s, S arg)throws SMPLException;
    
    public T visitExpIsEql(ExpIsEql s, S arg)throws SMPLException;
    
    public T visitExpIsEqv(ExpIsEqv s, S arg)throws SMPLException;
    
    public T visitExpIsPair(ExpIsPair s, S arg)throws SMPLException;
    
    public T visitExpList(ExpList s, S arg)throws SMPLException;
    
    public T visitExpPair(ExpPair s, S arg)throws SMPLException;
    
    public T visitExpSize(ExpSize s, S arg)throws SMPLException;
    
    public T visitExpSubstr(ExpSubstr s, S arg)throws SMPLException;
    
    public T visitExpVector(ExpVector s, S arg)throws SMPLException;
    
    public T visitExpConcat(ExpConcat s, S arg)throws SMPLException;
    
    public T visitExpGet(ExpGet s, S arg)throws SMPLException;
    
}
