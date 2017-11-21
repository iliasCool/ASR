package com.example;

/**
*
* This is being used in order to read the Json message from hub and extract audio URL and timestamp
*/
public class MessageToASR {
   
   MessageToASRContent message;
   
   public MessageToASR(){
       
   }
   
   public MessageToASR(String URL, String timestamp){
       this.message = new MessageToASRContent(URL, timestamp);
   }
   
}
