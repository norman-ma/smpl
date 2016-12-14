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
public class SMPLList extends SMPLPair{
    
    
    SMPLPair node;
    SMPLType type;
    
    public SMPLList(){
        type = SMPLType.EMPTYLIST;
    }
    
    public SMPLList(SMPLValue<?>...members){
        
    }
    
    private void createList(SMPLValue<?>[] list){
        
    }
}
