package docx.watchparty.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import docx.watchparty.utils.WatchPartyUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author water
 */
public class UIWelcome {
    public static void main() throws IOException {

        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        //Object Definitions
        
        
        // Create panel to hold components
        Panel panel = new Panel();
        panel.setPreferredSize(new TerminalSize(80, 20));
        
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("dotdocx " + WatchPartyUtils.getRelease()));
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(new Label("A barebones system for doing stuff with friends."));

        Button HostButton = new Button("Host", new Runnable() {
		@Override
		public void run() {
                    MessageDialog.showMessageDialog(textGUI, "Message", "The RTMP server address will be copied to your clipboard.\nThe stream key is equal to the room name.");
                    WatchPartyUtils.copyToClipboard(WatchPartyUtils.getRTMPServerAddress());
		}
        });
        
        Button ViewButton = new Button("View", new Runnable() {
		@Override
		public void run() {
                    // Create window to hold the panel
                    /*Panel panel = new Panel();
                    panel.setPreferredSize(new TerminalSize(40, 15));
                    panel.setLayoutManager(new GridLayout(2));
                    BasicWindow window = new BasicWindow();

                    window.setComponent(panel);
                    window.setHints(Arrays.asList(Window.Hint.CENTERED));
                    window.setCloseWindowWithEscape(true);
                    MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
                    gui.addWindowAndWait(window);*/
                    String input = TextInputDialog.showDialog(textGUI, "View Watch Party", "Type the name of your watch party", "");
                    try {
                        WatchPartyUtils.startViewingStream(input);
                    } catch (IOException ex) {
                        Logger.getLogger(UIWelcome.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UIWelcome.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
        });
        
        Button GitHubButton = new Button("GitHub", new Runnable() {
    		@Override
    		public void run() {
    			MessageDialog.showMessageDialog(textGUI, "Message", "Now opening GitHub in default browser.");
                try {
					WatchPartyUtils.openGitHub();
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        });
        
        Button ReportBugButton = new Button("Report Bug", new Runnable() {
    		@Override
    		public void run() {
                //MessageDialog.showMessageDialog(textGUI, "Message", "The RTMP server address will be copied to your clipboard.\nThe stream key is equal to the room name.");
    			MessageDialog.showMessageDialog(textGUI, "Message", "Now opening GitHub Issues in default browser.");
    			try {
					WatchPartyUtils.openBugReport();
				} catch (IOException | URISyntaxException e) {
					MessageDialog.showMessageDialog(textGUI, "Error", "Could not open web page.\nStack Trace:\n" + e.getStackTrace().toString());
					e.printStackTrace();
				}
    		}
        });
        
        panel.addComponent(new EmptySpace(new TerminalSize(0,3)));
        panel.addComponent(HostButton);
        panel.addComponent(ViewButton);
        panel.addComponent(GitHubButton);
        panel.addComponent(ReportBugButton);
        

        
        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

    }
}
