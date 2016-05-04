package com.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Shell2 extends JFrame {
	
	public Shell2(){
		JFileChooser chooser = new JFileChooser();
		int approve = chooser.showOpenDialog(this);
		
		if (approve == JFileChooser.APPROVE_OPTION) {
		    File sf = chooser.getSelectedFile();
		    String cmd = readFile(sf);
		    
		    Scanner scanner = new Scanner(System.in);
			System.out.print("$ ");
			cmd += scanner.nextLine();
		
			shellExec(cmd.trim());
		}
	}

	public static void main(String[] args) {
	
		new Shell2();
	    
	}
	
	
	public static void shellExec(String cmd){
		
		String s = null;
		 
        try {
             
            Process p = Runtime.getRuntime().exec(cmd);
             
            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));
 
            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));
 
            // read the output from the command
            System.out.println("OUTPUT:");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
             
            // read any errors from the attempted command
            if(stdError.readLine() != null){
            	System.err.println("\nERROR:");
            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
             
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
        }
		
	}
	
	public String readFile(File file){
		
		String allText = "";
		try{
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String text = reader.readLine();	
			while(text != null){
				allText += text;
				text = reader.readLine();
			}
		
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return allText;
	}

}
