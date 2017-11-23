package com.example.asr;





import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import org.apache.http.HttpEntity;

import org.apache.http.HttpHeaders;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpDelete;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.ByteArrayEntity;

import org.apache.http.impl.client.HttpClients;

/*

 * Include dependencies httpclient 4.5.3 and httpcore 4.4.6!

*/



/**

 *

 * @author andreadisst

 */

public class RawDataStore {

    

    public static void main(String[] args) {

        //Examples

        //storeFile("C:\\Users\\user\\Desktop\\image-to-upload.jpg","myimage.jpg");

        //retrieveFile("myimage.jpg","C:\\Users\\user\\Desktop\\downloaded-image.jpg");

        //deleteFile("myimage.jpg");

    }

    

    

    public static boolean storeFile(String sourcePath, String fileName){

        boolean status = false;

        try {



            HttpClient httpclient = HttpClients.createDefault();

            HttpPost httppost = new HttpPost("http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=" + fileName);



            Path path = Paths.get(sourcePath);

            byte[] data = Files.readAllBytes(path);

            httppost.setEntity(new ByteArrayEntity(data));

            HttpResponse response = httpclient.execute(httppost);

            if(response.getStatusLine().getStatusCode() == 200){

                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    try (InputStream inStream = entity.getContent()){

                        if (inStream.read() == -1){

                            System.out.println("SUCCESS - file was sent");

                            status = true;

                        } else {

                            System.out.println("ERROR - file wasn't sent");

                        }

                    }

                }

            }else{

                System.out.println("ERROR - file wasn't sent");

            }

        } catch (IOException e) {

            System.out.println("ERROR - " + e);

        }

        return status;

    }

    

    public static boolean retrieveFile(String fileName, String destinationPath){

        boolean status = false;

        try {



            HttpClient httpclient = HttpClients.createDefault();



            //HttpGet httpGet = new HttpGet("http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=" + fileName);

            HttpGet httpGet = new HttpGet(fileName);



            HttpResponse getResponse = httpclient.execute(httpGet);

            if(getResponse.getStatusLine().getStatusCode() == 200){

                HttpEntity getEntity = getResponse.getEntity();

                try (InputStream inStream = getEntity.getContent() ;

                FileOutputStream fos = new FileOutputStream(new File(destinationPath))){

                    int inByte;

                while((inByte = inStream.read()) != -1)

                    fos.write(inByte);

                }

                System.out.println("SUCCESS - file download complete");

                status = true;

            }else{

                System.out.println("ERROR - file was not found");

            }

        } catch (IOException e) {

            System.out.println("ERROR - " + e);

        }

        return status;

    }

    

    public static boolean deleteFile(String fileName){

        boolean status = false;

        try {



            HttpClient httpclient = HttpClients.createDefault();



            HttpDelete httpDelete = new HttpDelete("http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=" + fileName);

            HttpResponse getResponse = httpclient.execute(httpDelete);

            if(getResponse.getStatusLine().getStatusCode() == 200){

                System.out.println("SUCCESS - file was deleted");

                status = true;

            }else{

                System.out.println("ERROR - file was not found");

            }

            

        } catch (IOException e) {

            System.out.println("ERROR - " + e);

        }

        return status;

    }

    

}


