package smpl.syntax;

public class Case {

    Exp exp1;
    Exp exp2;

    public Case(Exp a, Exp b) {
	exp1 = a;
	exp2 = b;
    }

    public Exp getPredicate() {
	return exp1;
    }

    public Exp getConsequent() {
	return exp2;
    }
    
    public String toString(){
        return String.format("%s = %s",exp1.toString(),exp2.toString());
    }

}
