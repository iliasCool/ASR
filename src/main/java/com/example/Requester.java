package com.example.asr;


import java.io.*;
import java.net.*;

import com.google.gson.Gson;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

public class Requester{
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    String URL="test.wav";
    Requester(){}
    void run()
    {
        try{
        	
            //1. creating a socket to connect to the server
           // requestSocket = new Socket("160.40.49.114", 2031); //this pc
           requestSocket = new Socket("192.168.200.129", 2087); //vm ubuntu

            //System.out.println("Connected to localhost in port 2004");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("server>" + message);
                    
                    boolean terminate=true;  //this is becoming true when file processing is over
                    if (message.equals("Connection successful")) {  // start file processing
                        //1. create json message directly
                        //String jsonInString = "{\"URL\": \"test.wav\"}";
                        //String jsonInString = "{ \"URL\": \"test.wav\", \"timestamp\": \"1508414400\"  }";
                        
                    	
                    	// //2. create json message with constructor                    
                           //a.old meassage
                    	   // MessageFields messageFields = new MessageFields();
                           // messageFields.setURL("test.wav");
           	               // Gson gson = new Gson();
                           // String jsonInString = gson.toJson(messageFields);
               	          //b. new message
                    	String url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=test.wav";
        				String audio_timestamp="2017-01-01T12:00:00Z";
                    	MessageToASR msg = new MessageToASR(url,audio_timestamp);
        				Gson gson = new Gson();
        				String jsonInString = gson.toJson(msg);
                        System.out.println(jsonInString);
                    	                    	
                    	sendMessage(jsonInString);
                    	terminate=false;
                       }       
                                     
                    if (message.equals("Msg from Hub received. File is being processed..")) {  // do nothing
                    	terminate=false;
                    }
                                                       
                   if (terminate==true && !message.equals("bye")) {     // end connection
                        sendMessage("bye"); //  sendMessage("bye");
                   }
           	        
           	       
           	        
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Requester client = new Requester();
        client.run();
    }
    
    
    //
   

//	    private static MessageFields createObject() {

//	    	MessageFields MessageFields = new MessageFields();

	//    	MessageFields.setURL("test.wav");
	    
	  //      return MessageFields;

	    //}

	
    
    //
    
    
}
