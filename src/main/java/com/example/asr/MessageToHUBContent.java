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
    String IDRef;
    
    public MessageToHUBContent(){
        
    }
    
    public MessageToHUBContent(String timestamp, String language, String IDRef){
        
        this.timestamp = timestamp;
        this.language = language;
        this.IDRef = IDRef;
    }
    
}
