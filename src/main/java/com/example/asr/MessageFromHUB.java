package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageFromHUB {
   
   MessageFromHUBContent message;
   
   public MessageFromHUB(){
       
   }
   
   public MessageFromHUB(String URL, String timestamp, String language, String incidentID){
       this.message = new MessageFromHUBContent(URL, timestamp,language,incidentID);
   }
   
}
