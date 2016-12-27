/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import smpl.sys.SMPLException;
/**
 *
 * @author namro_000
 */
public class MathSMPLException extends SMPLException{
    private static final long serialVersionUID = 1L;

    public MathSMPLException() {
        super("Math Error");
    }
    
    public MathSMPLException(String message) {
        super(message);
    }
    
    public MathSMPLException(String message, Throwable cause) {
        super(message, cause);
    }
}
