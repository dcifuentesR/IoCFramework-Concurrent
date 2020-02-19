package edu.eci.arep.handlers.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import edu.eci.arep.handlers.MethodHandler;

public class StaticMethodHandler implements MethodHandler {
	
	private Method method;
    
    public StaticMethodHandler(Method method) {
    	this.method = method;
    }

	@Override
	public String process(Object[] parameters) {
    	try {
			return method.invoke(null, parameters).toString();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

}
