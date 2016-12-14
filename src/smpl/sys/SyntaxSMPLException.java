/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

/**
 *
 * @author namro_000
 */
public class SyntaxSMPLException extends SMPLException {

    public SyntaxSMPLException() {
        super("Syntax Error:");
    }
    
    public SyntaxSMPLException(String msg) {
        super(msg);
    }    
    
    public SyntaxSMPLException(String msg, Throwable t) {
        super(msg, t);
    }
}
