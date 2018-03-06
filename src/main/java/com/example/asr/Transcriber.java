package com.example.asr;


import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

import java.io.InputStream;









public class Transcriber {

public static void main(String[] args) {
	
}
public static String transcribe(String fileURL, String language) throws IOException {
	// this is used to upload a file from workspace to Raw data Store 
	//RawDataStore.storeFile("test.wav","test.wav");

	// 1.Download audio file to current workspace
    RawDataStore.retrieveFile(fileURL,"new_audio.wav");
   
	// 2. Load ASR configuration 
	Configuration configuration = new Configuration();
	

    switch (language) {
    case "en-US": //English online
    	configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    	configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    	configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        // //alternativelly 
        	//configuration.setAcousticModelPath("file:acoustic_models/English/en-us");
        	//configuration.setDictionaryPath("file:acoustic_models/English/cmudict-en-us.dict");
        	//configuration.setLanguageModelPath("file:acoustic_models/English/en-us.lm.bin");
        break;
    case "it-IT"://Italian 
    	configuration.setAcousticModelPath("file:acoustic_models/Italian/cmusphinx-it-5.2/model_parameters/voxforge_it_sphinx.cd_cont_2000");
    	configuration.setDictionaryPath("file:acoustic_models/Italian/cmusphinx-it-5.2/etc/voxforge_it_sphinx.dic");
    	configuration.setLanguageModelPath("file:acoustic_models/Italian/cmusphinx-it-5.2/etc/voxforge_it_sphinx.lm");
        // //alternativelly 
    		//configuration.setAcousticModelPath("file:acoustic_models/Italian/cmusphinx-it-5.2/model_parameters/voxforge_it_sphinx.cd_cont_2000");
    		//configuration.setDictionaryPath("file:acoustic_models/Italian/voxforge_it_sphinx/etc/voxforge_it.dic");
    		//configuration.setLanguageModelPath("file:acoustic_models/Italian/voxforge_it_sphinx/etc/voxforge_it.lm.dmp");
        break;
    case "es"://Spanish 16kHz model
    	// //configuration.setAcousticModelPath("file:acoustic_models/Spanish/cmu_spanish/cmusphinx-es-5.2/model_parameters/voxforge_es_sphinx.cd_ptm_4000");
    	configuration.setDictionaryPath("file:acoustic_models/Spanish/cmu_spanish/es.dict");
    	// //configuration.setDictionaryPath("file:acoustic_models/Spanish/cmu_spanish/cmusphinx-es-5.2/etc/voxforge_es_sphinx");//words missing from the dictionary
    	// configuration.setLanguageModelPath("file:acoustic_models/Spanish/cmu_spanish/es-20k.lm");
        // //alternativelly 
    	configuration.setAcousticModelPath("file:acoustic_models/Spanish/spanish 2/voxforge-es-0.2/model_parameters/voxforge_es_sphinx.cd_ptm_3000");
    	//configuration.setDictionaryPath("file:acoustic_models/Spanish/spanish 2/voxforge-es-0.2/etc/voxforge_es_sphinx.dic");
    	configuration.setLanguageModelPath("file:acoustic_models/Spanish/spanish 2/voxforge-es-0.2/etc/voxforge_es_sphinx.transcription.test.lm");

        break;
    case "el-GR"://Greek 
        configuration.setAcousticModelPath("file:acoustic_models/Greek/cmusphinx-el-gr-5.2/el-gr.cd_cont_5000");
        configuration.setDictionaryPath("file:acoustic_models/Greek/cmusphinx-el-gr-5.2/el-gr.dic");
        configuration.setLanguageModelPath("file:acoustic_models/Greek/cmusphinx-el-gr-5.2/el-gr.lm.bin");
        //configuration.setLanguageModelPath("file:acoustic_models/Greek/cmusphinx-el-gr-5.2/el-gr.lm");
        break;
}

	
	
	
	
	

       
       // configuration.setAcousticModelPath("file:acoustic_models/English/en-us");
       // configuration.setDictionaryPath("file:acoustic_models/English/cmudict-en-us.dict");
       // configuration.setLanguageModelPath("file:acoustic_models/English/en-us.lm.bin");
        
        
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
    	InputStream stream = new FileInputStream(new File("new_audio.wav"));

//  	   InputStream stream = new FileInputStream(new File("spanish16KHz2.wav"));
	  // InputStream stream = new FileInputStream(new File("el-0128_16kHz.wav"));
    	
        
    	recognizer.startRecognition(stream);
        SpeechResult result;
        String transcription=""; 
            while ((result = recognizer.getResult()) != null) {
            	//    //1.Write on screen
            	         //System.out.format("Hypothesis: %s\n", result.getHypothesis());
                //    //2.alternatively: Print utterance string without filler words.
                      // System.out.println(result.getHypothesis());      
               //     3. Get individual words and their times.
            		  for (WordResult r : result.getWords()) {
            		      System.out.println(r);
            		  }      
               //     //4. Save lattice in a graphviz format.
            		  //result.getLattice().dumpDot("lattice.dot", "lattice");
              //      //5. Best 3 hypothesis
            		  //System.out.println("Best 3 hypothesis:");
            	      //for (String s : result.getNbest(3))
            		  //    System.out.println(s);
    // this text will get redirected to file
       	//       System.out.println(("\n"+result.getHypothesis()));      
        System.out.println(result.getHypothesis());      
        //transcription += "\n"+result.getHypothesis();
        transcription += " "+result.getHypothesis();
        
        
        
        
        List<WordResult> words = result.getWords();
        for (WordResult wordResult: words) {
        	System.out.print(wordResult.getWord()+ " ");
        }
    	}
    	recognizer.stopRecognition();
    	
        System.out.println(transcription);      
    	
    	   	  
        //String transcription = "This is a test transcription";
       // System.out.println(transcription); 
    //  Write transcription to text file
         //FileOutputStream f = new FileOutputStream("transcription.txt");
         //System.setOut(new PrintStream(f));         
       //  System.out.println(transcription);
         return transcription;

	}

}
