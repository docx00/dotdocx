package docx.watchparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static String systemInformation;
	static String roomName;
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("DOTDOCX V0.1 =========");
		System.out.println("Built for watch parties, but cheaper lmao.");
		
		if(System.getProperty("os.name").contains("Windows")) {
			systemInformation = "Windows";
		}
		if(System.getProperty("os.name").contains("Mac")) {
			systemInformation = "Mac";
		}
		if(System.getProperty("os.name").contains("Unix")) {
			systemInformation = "Unix";
		}
		
		System.out.println("System: " + systemInformation);
		
		if(systemInformation.contentEquals("Unix")) {
			System.out.println("!!!UNIX SYSTEM DETECTED, PLEASE INSTALL FFPLAY FROM PACKAGE MANAGER!!!");
		}
		
		System.out.println("Select an option: 1) Host, 2) Viewer, 3) GitHub");
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
	    //System.out.println("> ");
	    String userOption = scanner.nextLine();  // Read user input
	    
	    if(userOption.equals("1")) { // HOST
	    	System.out.println("You are now HOSTING! This currently only works on Windows because of FFMPEG requiring compilation for each individual platform.");
	    	System.out.println("Type a name for your room:");
	    	Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("> ");
		    roomName = scanner.nextLine();  // Read user input
		    System.out.println("Please open OBS Studio, and set your Stream Server to rtmp://70.181.146.250/live/ and your Stream key to " + roomName );
	    }
	    
	    if(userOption.contentEquals("2")) {
	    	System.out.println("What is the host's room name?");
	    	Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("> ");
		    roomName = scanner1.nextLine();
		    if(systemInformation.contentEquals("Windows")) {
		    	ProcessBuilder ps=new ProcessBuilder("ffplay.exe","-x","1280","-y","720","-framedrop","-autoexit","-window_title","dotDocx: " + roomName , "rtmp://70.181.146.250/live/" + roomName);

		    	//From the DOC:  Initially, this property is false, meaning that the 
		    	//standard output and error output of a subprocess are sent to two 
		    	//separate streams
		    	ps.redirectErrorStream(true);

		    	Process pr = ps.start();  

		    	BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		    	String line;
		    	while ((line = in.readLine()) != null) {
		    	    System.out.println(line);
		    	}
		    	pr.waitFor();
		    	System.out.println("ok!");

		    	in.close();
		    	System.exit(0);
		    }
		    if(systemInformation.contentEquals("Mac")) {
		    	System.out.println("FFPlay logging is not supported on Mac yet!");
		    	System.out.println("\\u001B[31m!!!ATTENTION!!! IF YOU NEVER GET A STREAM, PLEASE INSTALL FFMPEG THROUGH BREW!");
		    	System.out.println("Run this command through your Terminal: brew install ffmpeg --with-sdl2 ");
		    	Runtime rt = Runtime.getRuntime();
		    	Process pr = rt.exec("ffplay rtmp://70.181.146.250/live/" + roomName);
		    }
	    }
	}

}