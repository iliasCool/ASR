package com.example;

/**
*
* @author andreadisst
*/
public class MessageToASR {
   
   MessageToASRContent message;
   
   public MessageToASR(){
       
   }
   
   public MessageToASR(String URL, String timestamp){
       this.message = new MessageToASRContent(URL, timestamp);
   }
   
}
