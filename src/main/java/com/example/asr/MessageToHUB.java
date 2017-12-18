package com.example.asr;


/**
*
* @author andreadisst
*/
public class MessageToHUB {
   
   MessageToHUBContent message;
   
   public MessageToHUB(){
       
   }
   
   public MessageToHUB(String IDRef, String language){
       this.message = new MessageToHUBContent(IDRef,language);
   }
   
}
