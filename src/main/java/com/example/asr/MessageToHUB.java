package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageToHUB {
   
   MessageToHUBContent message;
   
   public MessageToHUB(){
       
   }
   
   public MessageToHUB(String timestamp, String language, String IDRef){
       this.message = new MessageToHUBContent(timestamp,language,IDRef);
   }
   
}
