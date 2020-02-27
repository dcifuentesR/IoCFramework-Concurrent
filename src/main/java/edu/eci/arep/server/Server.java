package edu.eci.arep.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.eci.arep.handlers.Handler;
import edu.eci.arep.handlers.impl.HTMLHandler;
import edu.eci.arep.handlers.impl.ICOHandler;
import edu.eci.arep.handlers.impl.MethodHandler;
import edu.eci.arep.handlers.impl.PNGHandler;


public class Server {

	public static void main(String[] args) throws IOException {
		Handler handler;
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
		PrintWriter out;
		BufferedReader in;
		
		while(true)
		{
		Socket clientSocket = null;
		
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()));
				
		
		String inputLine;
		String request = null;
		
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.matches("(GET)+.*")) {
					request = inputLine.split(" ")[1];
				}
				if(!in.ready())
					break;	
			}
			
		request = request == null ? "/error.html" : request;
            request = request.equals("/") ? "/index.html" : request;
			if (request.matches("(/apps).*")) {
				try {
					handler = new MethodHandler();
					handler.handle(out, clientSocket.getOutputStream(), request);
				} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (request.matches(".*(.html)")) {
				handler = new HTMLHandler();
				handler.handle(out, clientSocket.getOutputStream(), request);
			} else if (request.matches(".*(.PNG)")) {
				handler = new PNGHandler();
				handler.handle(out, clientSocket.getOutputStream(), request);
			} else if (request.matches(".*(.ico)")) {
				handler = new ICOHandler();
				handler.handle(out, clientSocket.getOutputStream(), request);
			}
			out.close();
			in.close();
				

		}
	}
	
	/**
	 * This method returns the port that the server is going to use
	 * @return The value of the 'PORT' variable or '4567' if the variable is not found.
	 */
	static int getPort() {
		return System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 4567;
	}

}
