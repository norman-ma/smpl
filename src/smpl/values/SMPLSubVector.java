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
public class SMPLSubVector extends SMPLValue{    
    
    SMPLValue<?>[] vals;    
    
    public SMPLSubVector(SMPLValue<?>[] v){
        vals = v;
    }     
    
    public int getSize(){
        return vals.length;
    }
    
    public SMPLValue<?>[] getValues(){
        return vals;
    }
    
    @Override
    public SMPLType getType(){
        return SMPLType.SUBVECTOR;
    }
    
}
