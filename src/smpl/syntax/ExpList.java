/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SMPLException;
 
public class ExpList extends Exp {

    ArrayList<Exp> lst;

    public ExpList(ArrayList<Exp> l) {
        lst = l;
    }

    public ArrayList<Exp> getExpList() {
        return lst;
    }
    
@Override
    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	return v.visitExpList(this, arg);
    }

    @Override
    public String toString() {	
         String l;
        int n = lst.size();
        switch (n) {
            case 0: l = ""; break;
            case 1: l = lst.get(0).toString(); break;
            default: 
                l = lst.get(0).toString();
                for (int i = 1; i < n; i++) {
                    l += ", " + lst.get(i);
                }
        }
        
        return "[ "+l+" ]";
    }
}