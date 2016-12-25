/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.Arrays;
import smpl.sys.SMPLException;

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
	linkList(this,members);
        type = SMPLType.PAIR;
    }	
    
    private SMPLList(SMPLPair p){
        base = p;
        type = SMPLType.PAIR;
    }
    
    private void linkList(SMPLList node, SMPLValue<?>[] list){
	SMPLList next = new SMPLList(new SMPLPair());
	if(list.length==1){
            node.setCar(list[0]);
            node.setCdr(new SMPLList());
	}else{
            node.setCar(list[0]);
            node.setCdr(next);
            linkList(next,Arrays.copyOfRange(list,1,list.length));
        }		
    }
    
    @Override
    public int compareTo(SMPLValue<?> a)throws SMPLException{
        if(getType().equals(SMPLType.EMPTYLIST)){
            if(a.getType().equals(getType())){
                return 0;
            }else{
                return -1;
            }
        }else{
            return base.compareTo(a);
        }
    }
    
    @Override
    public SMPLValue<?> getCar(){
        return base.getCar();
    }
    
    @Override
    public SMPLValue<?> getCdr(){
        return base.getCdr();
    }
    
    @Override
    public void setCar(SMPLValue<?> v){
        base.setCar(v);
    }
    
    @Override
    public void setCdr(SMPLValue<?> v){
        base.setCdr(v);
    }
    
    @Override
    public SMPLType getType(){
        return type;
    }
    
    @Override
    public String toString(){
        if(type.equals(SMPLType.EMPTYLIST)){
            return "nil";
        }else{
            return base.toString();
        }
    }
}
