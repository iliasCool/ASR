package com.example.asr;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
import java.io.IOException;

public class FileEncoder {

    public static void main(String[] args) {
    	
    }
    public static void fileEncode(String inputFile,String outputFile) {
    	
        // TODO Auto-generated method stub
        File source = new File(inputFile);
        File target = new File(outputFile);
        AudioAttributes audio	= new AudioAttributes();
        audio.setCodec("pcm_s16le");
        audio.setBitRate(new Integer(16));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(16000));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InputFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}