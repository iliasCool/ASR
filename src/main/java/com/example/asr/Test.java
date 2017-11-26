package com.example.asr;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		String url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=test.wav";
		String audio_timestamp="2018-01-01T12:00:00Z";
		String transcription;
   		transcription =   Transcriber.transcribe(url);
    	String transcription_id=MongoAPI.mongoWrite(transcription,audio_timestamp); 
    	System.out.println(transcription_id);
    	System.out.println(transcription);

	}
}
