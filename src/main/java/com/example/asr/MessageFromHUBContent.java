package com.example.asr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreadisst
 */
public class MessageFromHUBContent {
    
    String URL;
    String timestamp;
    String language;
    String incidentID;
    
    public MessageFromHUBContent(){
        
    }
    
    public MessageFromHUBContent(String URL, String timestamp, String language, String incidentID){
        this.URL = URL;
        this.timestamp = timestamp;
        this.language = language;
        this.incidentID = incidentID;
    }
    
}
