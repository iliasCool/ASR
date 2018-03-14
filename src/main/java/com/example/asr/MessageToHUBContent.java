package com.example.asr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreadisst
 */
public class MessageToHUBContent {
    
    String transcription;
    String language;
    
    public MessageToHUBContent(){
        
    }
    
    public MessageToHUBContent(String transcription, String language){
       
        this.transcription = transcription;
        this.language = language;
    }
    
}
