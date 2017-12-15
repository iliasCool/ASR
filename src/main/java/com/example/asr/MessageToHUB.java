package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageToHUB {
   
   MessageToHUBContent message;
   
   public MessageToHUB(){
       
   }
   
   public MessageToHUB(String timestamp, String language, String transcrpition_id){
       this.message = new MessageToHUBContent(URL, timestamp,language,incidentID);
   }
   
}
