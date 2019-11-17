package com.pirates.frts.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.Firebase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Arrays;


@Service
public class FirebaseService {

    @Autowired
    Environment env;

    private String firebase_baseUrl;
    protected static final Logger LOGGER = LoggerFactory.getLogger(FirebaseService.class);


    public Firebase getInstanceforTable(String tableName, String primaryId) throws Exception {
        Firebase firebase = null;
        this.firebase_baseUrl = env.getProperty("firebase.base.url");
        try {

            String newUrl = firebase_baseUrl + "/" + tableName;
            if (primaryId != null) {
                newUrl = newUrl + "/" + primaryId;

            }
            firebase = new Firebase(newUrl, generateSecurityToken());
        } catch (FirebaseException e) {
            LOGGER.error("Got exception while fetching firebase instance");
        }
        return firebase;
    }


    /****
     *
     * To generate Security Token for firebase communication
     * **/
    private String generateSecurityToken() throws Exception {

        GoogleCredential scoped;

        try {
            String sdkKeyPath = env.getProperty("api.base.path") + "/frts/src/main/resources/static/frts-63acc-firebase-adminsdk-x16ek-ef89532dd2.json";
            FileInputStream serviceAccount = new FileInputStream(sdkKeyPath);
            GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);
            scoped = googleCred.createScoped(
                    Arrays.asList(
                            "https://www.googleapis.com/auth/firebase.database",
                            "https://www.googleapis.com/auth/userinfo.email"
                    )
            );
            scoped.refreshToken();
            return scoped.getAccessToken();
        } catch (Exception e) {
            LOGGER.error("Unable to fetch the access token for firebase", e);
        }
        return null;
    }

}
