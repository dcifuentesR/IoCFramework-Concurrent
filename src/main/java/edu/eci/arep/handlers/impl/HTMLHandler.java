package edu.eci.arep.handlers.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import edu.eci.arep.handlers.Handler;


public class HTMLHandler implements Handler {

	public void handle(PrintWriter out, OutputStream outStream, String request) {
		StringBuilder response = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(System.getProperty("user.dir") + "/testFiles" + request));
			String inputfile;
			while ((inputfile = reader.readLine()) != null)
				response.append(inputfile);
			out.println("HTTP/1.1 200 OK \r");
			out.println("Content-Type: text/html \r");
			out.println("\r");
			out.println(response.toString());

			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
