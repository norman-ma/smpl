/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.values;

import java.util.*;
import java.lang.Math;
import smpl.sys.SMPLException;

/**
 *
 * @author namro_000
 */
public abstract class SMPLValue<T extends SMPLValue<T>> {
    
    private static final long serialVersionUID = 1L; 
    
    public abstract SMPLType getType(); 
    
    public static SMPLReal make(Double v){
        return new SMPLReal(v);
    }
    
    public static SMPLInt make(Integer i, int a){
        return new SMPLInt(i, a);
    }
    
    public static SMPLString make(String s){
        return new SMPLString(s);
    }
    
    public static SMPLCharacter make(Character c){
        return new SMPLCharacter(c);
    }
    
    public static SMPLBoolean make(Boolean b){
        return new SMPLBoolean(b);
    }
    
    public static SMPLList make(){
        return new SMPLList();
    }
    
    public static SMPLList makeList(SMPLValue<?>[] v){
        return new SMPLList(v);
    }
    
    public static SMPLPair makePair(SMPLValue<?> a, SMPLValue<?> b){
        return new SMPLPair(a,b);
    }
    
    public static SMPLVector makeVector(SMPLValue<?>[] v){
        return new SMPLVector(v);
    }
    
    public static SMPLSubVector makeSubVector(SMPLValue<?>[] v){
        return new SMPLSubVector(v);
    }
    
     public static SMPLTuple makeTuple(SMPLValue<?>[] v){
        return new SMPLTuple(v);
    }
    
    
    public boolean isInteger(){
        return getType().equals(SMPLType.INTEGER);
    }
    
    public SMPLValue<?> add(SMPLValue<?> arg) throws SMPLException {
        throw new TypeSMPLException("Operation add called with non-numeric type");
    }

   
    public SMPLValue<?> sub(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation sub called with non-numeric type");
    }

   
    public SMPLValue<?> mul(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation mul called with non-numeric type");
    }

    
    public SMPLValue<?> div(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation div called with non-numeric type");
    }
    
    
    public SMPLValue<?> mod(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation mod called with non-numeric type");
    }
    
    public SMPLValue<?> pow(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation pow called with non-numeric type");
    }
    
    public SMPLValue<?> bitAnd(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation bit-wise and called with non-integer type");
    }
            
    public SMPLValue<?> bitOr(SMPLValue<?> arg) throws SMPLException {
            throw new TypeSMPLException("Operation bit-wise or  called with non-numeric type");
    }
    
    public SMPLValue<?> comp() throws SMPLException {
            throw new TypeSMPLException("Operation one's complement called with non-numeric type");
    }
    
   public abstract int compareTo(SMPLValue<?> arg) throws SMPLException;
    
    public int intValue() throws TypeSMPLException {
        throw new TypeSMPLException(SMPLType.INTEGER, getType());
    }

    
    public double doubleValue() throws TypeSMPLException {
        throw new TypeSMPLException(SMPLType.REAL, getType());
    }
    
    public String stringValue() throws TypeSMPLException{
        throw new TypeSMPLException(SMPLType.STRING, getType());
    }
    
    public char charValue() throws TypeSMPLException{
        throw new TypeSMPLException(SMPLType.CHARACTER, getType());
    }
    
    public boolean booleanValue() throws TypeSMPLException{
        throw new TypeSMPLException(SMPLType.BOOLEAN, getType());
    }
}
