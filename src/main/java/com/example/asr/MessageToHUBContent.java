package com.example.asr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreadisst
 */
public class MessageToHUBContent {
    
    String IDRef;
    String language;
    
    public MessageToHUBContent(){
        
    }
    
    public MessageToHUBContent(String IDRef, String language){
       
        this.IDRef = IDRef;
        this.language = language;
    }
    
}
