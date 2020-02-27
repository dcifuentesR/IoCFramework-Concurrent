package edu.eci.arem.apps;

import edu.eci.arep.annotations.Web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2116387
 */
public class SampleMethods {
    /**
     * Simple greeting
     * @return a greeting
     */
    @Web(value="hola")
    public static String greet(){
        return "hola como estas?";
    }
    
    /**
     * Multiplies two Integers
     * @param num1 - A string representing the first integer
     * @param num2 - A string representing the first integer
     * @return The product of both Integers
     */
    @Web(value="product")
    public static String multiply(String num1,String num2) {
    	return "El producto es: "+Integer.toString(Integer.parseInt(num1)*Integer.parseInt(num2));
    }
    
}
