package edu.eci.arep.handlers.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import edu.eci.arep.handlers.Handler;
import edu.eci.arep.annotations.Web;

public class MethodHandler implements Handler {
	
	private Map<String, Method> URLHandlerMap;
    
	public MethodHandler() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		URLHandlerMap = new HashMap<String, Method>();

		Reflections reflections = new Reflections("edu.eci.arem.apps", new SubTypesScanner(false));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
		for (Class c : classes)
			for (Method m : c.getMethods())
				if (m.isAnnotationPresent(Web.class))
					URLHandlerMap.put("/apps/" + m.getAnnotation(Web.class).value(),m);
	}
	
	private static Object[] getParameters(String request) {
		Object parameters[] = null;

		if (request.matches("(/apps/)[a-z]+[?]+[A-Z,=,&,a-z,0-9,.]*")) {
			String[] s = request.split("[?]")[1].split("[&]");
			parameters = new Object[s.length];
			for (int i = 0; i < parameters.length; i++)
				parameters[i] = s[i].split("=")[1];
		}
		
		return parameters;
	}

	@Override
	public void handle(PrintWriter out, OutputStream outStream, String request) {
		Object[] parameters = getParameters(request);
		request = request.contains("?") ? request.substring(0, request.indexOf("?")) : request;

		if (URLHandlerMap.containsKey(request)) {
			out.println("HTTP/1.1 200 OK\r");
			out.println("Content-Type: text/html\r");
			out.println("\r");
			try {
				out.println(URLHandlerMap.get(request).invoke(null, parameters));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
