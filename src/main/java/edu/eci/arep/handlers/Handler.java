package edu.eci.arep.handlers;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface Handler {
	
	/**
	 * Seeks the requested resource, generates a valid HTTP response and prints it to the provided PrintWriter
	 * @param out - The writer for the outputstream.
	 * @param outStream - the OutputStream for the client socket
	 * @param request - the requested resource e.g., /index.html, /error.html, /image.png, etc.
	 */
	public void handle(PrintWriter out, OutputStream outStream, String request);

}
