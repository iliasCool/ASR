package com.example.asr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreadisst
 */
public class MessageToHUBContent {
    
    
    String timestamp;
    String language;
    String transcrpition_id;
    
    public MessageToHUBContent(){
        
    }
    
    public MessageToHUBContent(String timestamp, String language, String transcrpition_id){
        
        this.timestamp = timestamp;
        this.language = language;
        this.transcrpition_id = transcrpition_id;
    }
    
}
