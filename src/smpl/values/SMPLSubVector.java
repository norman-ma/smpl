/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.ArrayList;

/**
 *
 * @author namro_000
 */
public class SMPLSubVector extends SMPLVector{
    
    SMPLInt size;
    SMPLProcedure procedure;
    SMPLValue<?>[] value;
    
    public SMPLSubVector(SMPLInt s, SMPLProcedure proc){
        size = s;
        procedure = proc;
    }    
    
    public SMPLValue<?>[] value(){
        return value;
    }
    
    public int getSize(){
        return size.intValue();
    }
    
    public SMPLProcedure getProcedues(){
        return procedure;
    }
    
    public void eval(){
        ArrayList<SMPLValue<?>> temp = new ArrayList<>(); 
        for(int i = 0; i<size();i++){
            //unfinished... this is where evaluation on function would take place
        }
        value = new SMPLValue<?>[temp.size()];
        for(int i = 0; i<temp.size();i++){
            value[i] = temp.get(i);
        }
    }
    
    @Override
    public SMPLType getType(){
        return SMPLType.SUBVECTOR;
    }
    
}
