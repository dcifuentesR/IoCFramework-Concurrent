package edu.eci.arep;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Client {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Ingrese una URL");
		try {
			URL url = new URL(br.readLine());

			BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine = null;
			FileOutputStream fileOutputStream = new FileOutputStream("./results/resultado.html");
			while ((inputLine = urlReader.readLine()) != null) {
				fileOutputStream.write(inputLine.getBytes());
			}
			br.close();
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

}
