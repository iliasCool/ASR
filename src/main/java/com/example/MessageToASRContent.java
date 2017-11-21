package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreadisst
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
