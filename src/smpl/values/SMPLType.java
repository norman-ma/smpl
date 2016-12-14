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
public enum SMPLType {
    INTEGER("int"),
    REAL("real"),
    BOOLEAN("boolean"),
    STRING("string"),
    CHARACTER("char"),
    EMPTYLIST("nil"),
    VECTOR("vector"),
    SUBVECTOR("subvector"),
    PAIR("pair"),
    LIST("list"),
    FUNCTION("user function");
    
    private final String docString;
    
    SMPLType(String s){
        docString = s;
    }
}
