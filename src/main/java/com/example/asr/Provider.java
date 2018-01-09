package com.example.asr;



import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class Provider{
    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    Provider(){}
    void run() throws KeyManagementException, NoSuchAlgorithmException
    {
        try{
            //1. creating a server socket
            providerSocket = new ServerSocket(2087, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("client>" + message);
                    if (message.equals("bye")) {
                        sendMessage("bye"); //  sendMessage("bye");
                    }else {                   
                        Gson gson_in = new Gson();    	
                        //String jsonStr = "{\"a\": \"A\"}";
                        //MessageFields messagefields =gson.fromJson(message, MessageFields.class);
                       	//String url = messagefields.getURL();
                    	//System.out.println(url);
                    	//String audio_timestamp = messagefields.gettimestamp();
                    	//System.out.println(audio_timestamp);
//                      Gson gson2 = new Gson(); 
                        MessageFromHUB msg_in =gson_in.fromJson(message, MessageFromHUB.class);
                        String url=msg_in.message.URL;
                        String audio_timestamp=msg_in.message.timestamp;
                        String language=msg_in.message.language;
                        String incidentID=msg_in.message.incidentID;
                        System.out.println(url);
                        System.out.println(audio_timestamp);
                        
                        
                                       
                    	sendMessage("Msg from Hub received. File is being processed..");
                    	String transcription;
        	
          	     		transcription =   Transcriber.transcribe(url,language);

               	     	String transcription_id=MongoAPI.mongoWrite(transcription,audio_timestamp,language,incidentID); 
                        //String transcription_id="507f191e810c19729de860bb";
               	     	System.out.println(transcription);
               	     	System.out.println(transcription_id);
               	     	
                        // //a.create json directly
                        // //String jsonStrOut = "{\"IDRef\": \"507f191e810c19729de860ea\"}";
               	     	//String jsonStrOut = "{\"IDRef\": \""
               	     	//		+ transcription_id+
               	     	//		"\"}";
               	     	// //b. create json with constructor
                        MessageToHUB msg_out = new MessageToHUB(transcription_id,language);
        				Gson gson_out = new Gson();
        				String jsonStrOut = gson_out.toJson(msg_out);
                        
                        
               	     	System.out.println(jsonStrOut);
               	     	sendMessage(jsonStrOut);
                   }
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                providerSocket.close();
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
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException
    {
        Provider server = new Provider();
        while(true){
            server.run();
        }
    }
}
