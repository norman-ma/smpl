/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

/**
 *
 * @author namro_000
 */
public class SMPLCharacter extends SMPLValue<SMPLCharacter>{
    
    char value;

    public SMPLCharacter(Character c){
        value = c;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.CHARACTER;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    } 
    
}
