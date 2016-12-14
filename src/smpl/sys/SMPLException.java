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
public class SMPLException extends Exception {

    private static final long serialVersionUID = 1L;

    public SMPLException() {
        super("SMPL Error");
    }
    
    public SMPLException(String msg) {
        super(msg);
    }
    
    public SMPLException(String msg, Throwable cause) {
        super(msg, cause);
    }

}