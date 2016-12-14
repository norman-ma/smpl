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
public class TypeSMPLException extends SMPLException{
    private static final long serialVersionUID = 1L;

    public TypeSMPLException() {
        super("Type Error");
    }
    
    public TypeSMPLException(String message) {
        super(message);
    }
    
    public TypeSMPLException(SMPLType expected, SMPLType received) {
        super("Type Error: Expected " + expected + " but got " + received);
    }
    
    public TypeSMPLException(String message, Throwable cause) {
        super(message, cause);
    }
}
