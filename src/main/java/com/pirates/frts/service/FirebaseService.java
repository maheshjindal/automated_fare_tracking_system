package com.pirates.frts.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.util.Firebase;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Arrays;

@Service
public class FirebaseService {

    private String firebase_baseUrl = "https://frts-63acc.firebaseio.com";
    protected static final Logger LOGGER = Logger.getRootLogger();


    public Firebase getInstanceforTable(String tableName) throws Exception{
        Firebase firebase = null;
        try {
            firebase = new Firebase(firebase_baseUrl+"/"+tableName,generateSecurityToken());
        }catch (FirebaseException e){
            LOGGER.error("Got exception while fetching firebase instance");
        }
        return firebase;
    }
    private String generateSecurityToken() throws Exception{

        GoogleCredential scoped;

        try {
            FileInputStream serviceAccount = new FileInputStream("/Users/maheshjindal/Documents/frts/src/main/resources/static/frts-63acc-firebase-adminsdk-x16ek-ef89532dd2.json");
            GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);
             scoped = googleCred.createScoped(
                    Arrays.asList(
                            "https://www.googleapis.com/auth/firebase.database",
                            "https://www.googleapis.com/auth/userinfo.email"
                    )
            );
            scoped.refreshToken();
            return scoped.getAccessToken();
        }catch (Exception e){
            LOGGER.error("Unable to fetch the access token for firebase",e);
        }
    return null;
    }

}
