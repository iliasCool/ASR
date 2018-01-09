package com.example.asr;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		String lang_id="en";   //Define language

		
		
		String language="en-US";
		String url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=test.wav"; //english msg6
		switch (lang_id) {
		case "en":
			language="en-US";
			url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=english_rec.wav";
			break;
	    case "el":
			language="el-GR";
			url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=greek_rec.wav";
			break;
	    case "es":
			language="es";
			url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=spanish_rec.wav";
			break;
	    case "it":
			language="it-IT";
			url="http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=italian_rec.wav";
			break;
		}
		
		String audio_timestamp="2018-01-01T12:00:00Z";
		String transcription;
   		
		transcription =   Transcriber.transcribe(url,language);
		//String transcription_id="test" ;
   		String incidentID="542845";
     	String transcription_id=MongoAPI.mongoWrite(transcription,audio_timestamp,language,incidentID); 
    	System.out.println(transcription_id);
    	System.out.println(transcription);

	}
}
