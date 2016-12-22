package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SMPLException;

public class SMPLProgram extends Exp {
    StmtSequence seq;

    public SMPLProgram(StmtSequence s) {
	seq = s;
    }

    public StmtSequence getSeq() {
	return seq;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg)
	throws SMPLException {
	return v.visitSMPLProgram(this, arg);
    }

    @Override
    public String toString() {
	return seq.toString();
    }
}
