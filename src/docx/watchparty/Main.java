package docx.watchparty;

import docx.watchparty.ui.UIWelcome;
import docx.watchparty.utils.WatchPartyUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static String roomName;
	public static void main(String args[]) throws IOException, InterruptedException {
            if(args.length > 0 && args[0].equals("nogui")){
                System.out.println("DOTDOCX " + WatchPartyUtils.getRelease() + " =========");
                System.out.println("A barebones system for doing stuff with friends.");

                System.out.println("System: " + WatchPartyUtils.getOSName());

                if(WatchPartyUtils.getOSName().contentEquals("Unix") || WatchPartyUtils.getOSName().contentEquals("Mac")) {
                        System.out.println("!!!UNIX/MAC SYSTEM DETECTED, PLEASE INSTALL FFMPEG AND FFPLAY FROM PACKAGE MANAGER!!!");
                }

                System.out.println("Select an option: 1) Host, 2) Viewer, 3) GitHub");

                Scanner scanner = new Scanner(System.in);  // Create a Scanner object
                //System.out.println("> ");
                String userOption = scanner.nextLine();  // Read user input

                if(userOption.equals("1")) { // HOST
                    textBreak();
                    System.out.println("You are now HOSTING!");
                    System.out.println("Type a name for your room:");
                    Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
                        System.out.println("> ");
                        roomName = scanner.nextLine();  // Read user input
                        textBreak();
                        System.out.println("Please open OBS Studio, and set your Stream Server to rtmp://70.181.146.250/live/ and your Stream key to " + roomName );
                        System.in.read();
                        
                }

                if(userOption.contentEquals("2")) {
                    textBreak();
                    System.out.println("What is the host's room name?");
                    Scanner scanner1 = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("> ");
                    roomName = scanner1.nextLine();
                    WatchPartyUtils.startViewingStream(roomName);   
                }
            }
            if(args.length < 1){
                UIWelcome.main();
            }
            
    }
	
	public static void textBreak() throws IOException, InterruptedException {
		if(WatchPartyUtils.getOSName().contentEquals("Unix") || WatchPartyUtils.getOSName().contentEquals("mac")) {
	    	Runtime.getRuntime().exec("clear");
		}
		if(WatchPartyUtils.getOSName().contentEquals("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		System.out.println("-----------------------------------------------------");
	}

}