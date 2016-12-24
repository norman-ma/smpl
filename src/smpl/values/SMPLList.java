/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.Arrays;

/**
 *
 * @author namro_000
 */
public class SMPLList extends SMPLPair{
    
    SMPLPair base;
    SMPLType type;
    
    public SMPLList(){
        type = SMPLType.EMPTYLIST;
    }
    
    public SMPLList(SMPLValue<?>[] members){
        base = new SMPLPair();
	linkList(base,members);
        type = SMPLType.PAIR;
    }	
    
    private void linkList(SMPLPair node, SMPLValue<?>[] list){
	SMPLPair next = new SMPLPair();
	if(list.length==1){
            node.setCar(list[0]);
            node.setCdr(new SMPLList());
	}else{
            node.setCar(list[0]);
            node.setCdr(next);
            linkList(next,Arrays.copyOfRange(list,1,list.length));
        }		
    }
    
    public String toString(){
        if(type.equals(SMPLType.EMPTYLIST)){
            return "nil";
        }else{
            return base.toString();
        }
    }
}
