package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.eci.arep.handlers.Handler;
import edu.eci.arep.handlers.impl.HTMLHandler;
import edu.eci.arep.handlers.impl.ICOHandler;
import edu.eci.arep.handlers.impl.PNGHandler;
import edu.eci.arep.handlers.impl.RequestHandler;


public class Server {

	public static void main(String[] args) throws IOException {
		
		

		ExecutorService executor = Executors.newFixedThreadPool(20);
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		
		while(true)
		{
		Socket clientSocket = null;
		
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.execute(new RequestHandler(clientSocket));
		
		
				

		}
	}
	
	static int getPort() {
		return System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 4567;
	}

}
