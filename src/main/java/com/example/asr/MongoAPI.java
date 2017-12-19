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

    	System.out.println(MONGO_URI); 
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
       // MongoIterable<String> db_names = mongoClient.listDatabaseNames();     // Get all database names
       //List<String> db_names1 = mongoClient.getDatabaseNames();
       //System.out.println(db_names1);
       // String str_names = db_names.toString();
       // System.out.println(str_names);
         
       // Collection<String> colnames= db.getCollectionNames();
       // System.out.println(colnames);
        
        
        //DBCollection coll = db.getCollection("EnglishFloods");
        //long num_col=coll.count();
        //System.out.println(num_col);
        //List<DBObject> ind = coll.getIndexInfo();
        //System.out.println(ind);
        //DBCursor ind_col = coll.find();
        //System.out.println(ind_col);

        //DBCursor cursor = coll.find();
        //System.out.println(cursor);
        //cursor.hasNext();
        //DBObject post = cursor.next();
        //String id = post.get("id_str").toString();
        //System.out.println(post);
        //String id = rintln(id);

        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp);
        
        
        
    ////////////////////////////////////////////////////////    
         //DBCollection transcriptions_col = db.createCollection("Transcriptions", null);  // creates the collection
   /////////////////////////////////////////////////////////
        
      
        
       DBCollection transcriptions_col = db.getCollection("Transcriptions");
       	//    transcriptions_col.drop();   //deletes the collection
  
       
      //String transcription_text= "This is a test transcription";
      //String  audio_timestamp="16173821234235";
       BasicDBObject doc = new BasicDBObject("text", transcription_text).append("timestamp", audio_timestamp);  
       //          .append("count", 1)
       //       .append("info", new BasicDBObject("x", 203).append("y", 102));
	  transcriptions_col.insert(doc);
      //ObjectId id1 = doc.getObjectId(URI);
      //ObjectId id2 = doc.getObjectId(audio_timestamp);
      //ObjectId id3 = doc.getObjectId(transcription_text);
	  // System.out.println(id1);
	  // System.out.println(id2);
	  // System.out.println(id3);
      
       //System.out.post.get("id_str").toString();
	   //   db.products.insert( { item: "card", qty: 15 } )
	   
    		  
    		  long num=transcriptions_col.count();
	   System.out.println(num);
	   DBCursor cursor = transcriptions_col.find();
	   //System.out.println(cursor);

  	 DBObject post = cursor.next();//Initialization
	 System.out.println(post);
	   for (int i=2; i<=num; i++) {
		   post = cursor.next();
		   System.out.println(post);	
	   }        

	String id = post.get("_id").toString();  //post contains the last object of the collection since the cursor stopped at the last entry
       System.out.println(id);   //na kanw return to id       String key1= "IDRef"; 
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
	    
       
           return id;
    }
    
}
