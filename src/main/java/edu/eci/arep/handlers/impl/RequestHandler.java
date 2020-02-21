package edu.eci.arep.handlers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.eci.arep.handlers.Handler;

public class RequestHandler implements Runnable {
	
	private Handler handler;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public RequestHandler(Socket clientSocket) throws IOException {
		this.clientSocket =clientSocket;
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()));
	}
	
	private void handleRequest() throws IOException {
		String inputLine;
		String outputLine;
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
	
	@Override
	public void run() {
		try {
			handleRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
