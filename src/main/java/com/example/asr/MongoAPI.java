package com.example.asr;



import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoIterable;

import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
//import java.security.Timestamp;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.bson.types.ObjectId;

public class MongoAPI {
	static String MONGO_URI = System.getenv("SECRET_MONGO_URI");
	
	 public static void main(String[] args) {
	
	 }
	 
    public static String mongoWrite(String transcription_text, String audio_timestamp, String language, String incidentID) throws UnknownHostException, NoSuchAlgorithmException, KeyManagementException{

	    MongoClientURI mongoClientURI = new MongoClientURI(MONGO_URI);
       
        TrustManager[] trustManagers=new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String t) { }
                public void checkServerTrusted(X509Certificate[] certs, String t) { }
            }
        };

        SSLContext sslContext=SSLContext.getInstance("TLS");
        sslContext.init(null,trustManagers,new SecureRandom());
        
        MongoClientOptions options = MongoClientOptions.builder().
                        sslEnabled(true).
                        sslInvalidHostNameAllowed(true).
                        socketFactory(sslContext.getSocketFactory()).
                        build();

        MongoClient mongoClient = new MongoClient(Arrays.asList(
                                        new ServerAddress(mongoClientURI.getHosts().get(0)),
                                        new ServerAddress(mongoClientURI.getHosts().get(1))), Arrays.asList(mongoClientURI.getCredentials()), options);
        
        DB db = mongoClient.getDB("BeAware");
       
        
      
  //---------------------Write transcription and timestamp to mongo------------      
       DBCollection transcriptions_col = db.getCollection("Transcriptions");
       //    transcriptions_col.drop();   //deletes the collection
  
       BasicDBObject doc = new BasicDBObject("text", transcription_text).append("timestamp", audio_timestamp);  
       //          .append("count", 1)
       //       .append("info", new BasicDBObject("x", 203).append("y", 102));
	  transcriptions_col.insert(doc);
	  ObjectId ID = (ObjectId)doc.get( "_id" );
	  String id=ID.toString();
	  System.out.println(id);
	 
	  //----------------update field by including reference id as a string-----
	  String key1= "IDRef"; 
      String key2= "language";
      String key3= "incidentID";
      String value1=id;
      String value2=language;
      String value3=incidentID;
    
      transcriptions_col.remove(doc);
      doc.put(key1, value1);
      doc.put(key2, value2);
      doc.put(key3, value3);
      transcriptions_col.insert(doc);
      //------------------------------------------------------------------------
	  
	   
     //---------------Print all column records--------------------------------		  
      long num=transcriptions_col.count();
	  //System.out.println(num);
	  DBCursor cursor = transcriptions_col.find();
	  DBObject post = cursor.next();//Initialization
	  System.out.println(post);
	  for (int i=2; i<=num; i++) {
		  post = cursor.next();
		  System.out.println(post);	
	  }        

	  
	  
	  return id;
    }
    
}

