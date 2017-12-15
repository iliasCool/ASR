package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageToASR {
   
   MessageToASRContent message;
   
   public MessageToASR(){
       
   }
   
   public MessageToASR(String URL, String timestamp, String language, String incidentID){
       this.message = new MessageToASRContent(URL, timestamp,language,incidentID);
   }
   
}
