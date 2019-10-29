/* Written by Alex Motyka
The Client class is responsible for reading user input and determing
which function it should call. The function are stored remotely in the
ServerImpl class and they are accessed using Java RMI */

import java.io.*; 
import java.rmi.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Client{
   public static void main(String argv[]) {
      try {
		  boolean authenticated = false;
		  
		  // set up our interface
		  ServerInterface si = (ServerInterface) Naming.lookup("Server");
		  Scanner userIn = new Scanner(System.in);
		  while(!authenticated){
			  System.out.println("Enter the password: ");
			  String password = userIn.nextLine();
			  authenticated = si.authenticate(password);
			  if (authenticated == false){
				  System.out.println("Incorrect password. Try again.");
			  }
		  }
		  System.out.println("Acces granted. Listed are the proper usages: ");
		  System.out.println("[download] [filename]");
		  System.out.println("[upload] [filename]");
		  System.out.println("[delete] [filename]");
		  System.out.println("[write] [filename] [\"string\"]");
		  System.out.println("[ls]");
		  
		  while(true){
			  System.out.println("Enter a command: ");
			  String input = userIn.nextLine();
			  
			  // split user input to determine what to call
			  String[] tokens = input.split(" ");
			  if (tokens.length<1){
				  System.out.println("Proper usage: java Client [command] [filename] [filename/string]");
				  System.exit(0);
			  } else {
				  // collect tokens
				  String command = tokens[0];
				  String filename = "";
				  if (tokens.length >= 2){
					  filename = tokens[1];
				  }
				  String aux = "";
				  if (tokens.length >= 3){
					  aux = tokens[2];
				  }
				  if (command.equalsIgnoreCase("download")){
					  if(filename.equalsIgnoreCase("")){
						  System.out.println("You must provide a filename.");
					  } else {
						  // create a file and write to it
						  byte[] filedata = si.downloadFile(filename);
						  if(filedata.length == 0){
							  System.out.println("File did not exist on the server.");
						  } else {
							  // make sure files directory exists
							  File directory = new File("files");
							  directory.mkdirs();
						  
							  // create the file
							  File file = new File(directory, filename);
							  file.createNewFile();
							  
							  //write the download contents to the file
							  BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
							  output.write(filedata,0,filedata.length);
							  output.flush();
							  output.close();
							  System.out.println("File downloaded succesfully");
						  }
					  }
					   
				} else if(command.equals("upload")){
					if(filename.equalsIgnoreCase("")){
						System.out.println("You must provide a filename.");
					} else {
						// files directory
						File directory = new File("files");
		 
						// requested file
						File file = new File(directory, filename);
						if(file.exists()){
							System.out.println("Sending file...");
							// read from the file
							byte buffer[] = new byte[(int)file.length()];
							BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
							in.read(buffer,0,buffer.length);
							in.close();
							si.uploadFile(filename,buffer);
							System.out.println("File sent.");
						} else {
							System.out.println("File does not exist.");
						}
						
					}
					  
				} else if (command.equalsIgnoreCase("delete")){
					if(filename.equalsIgnoreCase("")){
						System.out.println("You must provide a filename.");
					} else {
						boolean deleted = si.deleteFile(filename);
						if (deleted){
							System.out.println("File deleted.");
						} else {
							System.out.println("File did not exist.");
						}
					}
					
				} else if (command.equalsIgnoreCase("write")){
					si.writeFile(filename,aux);
				} else if (command.equalsIgnoreCase("ls")){
					ArrayList<String> fileList = si.listFiles();
					System.out.println(fileList);
				} else {
					System.out.println("I don't understand that command human. Here is what I understand: ");
				    System.out.println("[download] [filename]");
					System.out.println("[upload] [filename]");
					System.out.println("[delete] [filename]");
					System.out.println("[write] [filename] [\"string\"]");
					System.out.println("[ls]");
				}
			  }
		  }
      } catch(Exception e) {
         System.err.println("Server exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}