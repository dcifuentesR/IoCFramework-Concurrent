package edu.eci.arep.handlers.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import edu.eci.arep.handlers.Handler;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

public class ICOHandler implements Handler {

	public void handle(PrintWriter out, OutputStream outStream, String request) {
		out.println("HTTP/1.1 200 OK \r");
		out.println("Content-Type: image/vnd.microsoft.icon/png \r");
		out.println("\r");
		BufferedImage image;
		try {
			image = ICODecoder.read(new File(System.getProperty("user.dir") + "/testFiles" + request)).get(0);
			ICOEncoder.write(image, outStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

}
