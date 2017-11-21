package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  This is being used in order to read the Json message from hub and extract audio URL and timestamp
 */
public class MessageToASRContent {
    
    String URL;
    String timestamp;
    
    public MessageToASRContent(){
        
    }
    
    public MessageToASRContent(String URL, String timestamp){
        this.URL = URL;
        this.timestamp = timestamp;
    }
    
}
