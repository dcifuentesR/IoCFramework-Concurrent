package edu.eci.arep.handlers.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import edu.eci.arep.handlers.Handler;


public class PNGHandler implements Handler {

	public void handle(PrintWriter out, OutputStream outStream, String request) {
		
		out.println("HTTP/1.1 200 OK \r");
		out.println("Content-Type: image/png \r");
		out.println("\r");
		BufferedImage image;
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + "/testFiles" + request));
			ImageIO.write(image, "png", outStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}