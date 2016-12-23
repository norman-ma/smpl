package smpl.syntax;

public class Specification {

    Exp exp1, exp2, exp3;

    public Specification(Exp a, Exp b) {
	exp1 = a;
	exp2 = b;
    }
    
     public Specification(Exp a) {
	exp3 = a;
    }

    public Exp getNumber() {
	return exp1;
    }

    public Exp getProcedure() {
	return exp2;
    }
    
    public Exp getExpression(){
        return exp3;
    }
    
    public String toString(){
        return String.format("%s : %s",exp1.toString(),exp2.toString());
    }

}
