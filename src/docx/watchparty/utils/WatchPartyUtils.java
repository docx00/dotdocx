/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docx.watchparty.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author water
 */
public class WatchPartyUtils {
	public static String getRTMPServerAddress() {
		return "rtmp://your-server-address";
	}
    public static String getOSName(){
        if(System.getProperty("os.name").contains("Windows")) {
			return "Windows";
		}
		if(System.getProperty("os.name").contains("Mac")) {
			return "Mac";
		}
		if(System.getProperty("os.name").contains("Unix")) {
			return "Linux";
		}
        return "Linux";
    }
    public static String getRelease(){
        return "v0.1.5-alpha";
    }
    public static void copyToClipboard(String text){
        String myString = text;
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    public static void startViewingStream(String roomName) throws IOException, InterruptedException, InterruptedException{
        if(WatchPartyUtils.getOSName().contentEquals("Windows")) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                	ProcessBuilder ps=new ProcessBuilder("ffplay.exe","-x","1280","-y","720","-framedrop","-autoexit","-window_title","dotdocx: " + roomName , "rtmp://70.181.146.250/live/" + roomName);

                    //From the DOC:  Initially, this property is false, meaning that the 
                    //standard output and error output of a subprocess are sent to two 
                    //separate streams
                    ps.redirectErrorStream(true);

                    Process pr = null;
					try {
						pr = ps.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  

                    BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    String line;
                    try {
						while ((line = in.readLine()) != null) {
						    System.out.println(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						pr.waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println("ok!");

                    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.exit(0);
                    try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println(roomName);
                }
            });  
            t1.start();
        }
        if(WatchPartyUtils.getOSName().contentEquals("Mac")) {
            System.out.println("\\u001B[31m!!!ATTENTION!!! IF YOU NEVER GET A STREAM, PLEASE INSTALL FFMPEG THROUGH BREW!");
            System.out.println("Run this command through your Terminal: brew install ffmpeg --with-sdl2 ");
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("ffplay -x 1280 -y 720 -framedrop -autoexit -window_title dotdocx: " + roomName + " rtmp://70.181.146.250/live/" + roomName);
            System.in.read();
        }
        if(WatchPartyUtils.getOSName().contentEquals("Linux")) {
            System.out.println("\\u001B[31m!!!ATTENTION!!! IF YOU NEVER GET A STREAM, PLEASE INSTALL FFMPEG THROUGH YOUR PACKAGE MANAGER!");
            Process p = Runtime.getRuntime().exec("ffplay -x 1280 -y 720 -framedrop -autoexit -window_title dotdocx: " + roomName + " rtmp://70.181.146.250/live/" + roomName);

            p.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = "";
            String output = "";

            while ((line = buf.readLine()) != null) {
              output += line + "\n";
            }

            System.out.println(output);
        }
    }
}
