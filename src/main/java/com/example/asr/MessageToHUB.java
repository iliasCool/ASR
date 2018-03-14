package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageToHUB {
   
   MessageToHUBContent message;
   
   public MessageToHUB(){
       
   }
   
   public MessageToHUB(String transcription, String language){
       this.message = new MessageToHUBContent(transcription,language);
   }
   
}
