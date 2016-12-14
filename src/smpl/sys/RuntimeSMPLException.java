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
public class RuntimeSMPLException extends SMPLException {

    public RuntimeSMPLException() {
        super("SMPL Runtime Error");
    }

    public RuntimeSMPLException(String msg) {
        super(msg);
    }

    public RuntimeSMPLException(String msg, Throwable cause) {
        super(msg, cause);
    }

}