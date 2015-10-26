package me.woder.trafficserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TrafficServer{
	public Logger log = Logger.getLogger("Minecraft");
	public String name;
	public String password;
	public String serverpassword;
	public boolean running = false;
	public List<ClientCon> players = new LinkedList<ClientCon>();
	public HashMap<String, Boolean> activated = new HashMap<String, Boolean>();
	ConnectionManager servers;
	ConnectionManager connection;
	Thread server;
	Thread client;
	Thread ircs;
	Thread commands;
	
	public static void main(String[] args){
	    new TrafficServer();
	}
	
	public TrafficServer(){
        System.out.println("TrafficServer started!");
		running = true;	
		servers = new ConnectionManager(this, 25455);
		server = new Thread(servers,"T1");
		server.start();
	}
	
	public String readString(DataInputStream in, int length){
	  String message = "";
	  try{
   	    for(int i = 0; i <= length; i++){
   		 message = message + in.readChar();
   	    }
	  }catch (IOException e) {
		e.printStackTrace();
		log.severe("FATAL ERROR: could not read string!");
		running = false;
	  }
	  return message;
	}
	
}
