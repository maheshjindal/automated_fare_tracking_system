package com.pirates.frts.service;

import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.Firebase;
import com.pirates.frts.util.TableType;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class CrudService {

    protected static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    FirebaseService firebaseService;

    public void createTable(TableType tableType, String jsonDataString){
        Firebase firebase = null;
        try{
            firebase = firebaseService.getInstanceforTable(tableType.getName());
        }catch (Exception e){

        }
        if(firebase != null){
            FirebaseResponse response = null;
            try{
                response = firebase.put(jsonDataString);
            }catch (FirebaseException | UnsupportedEncodingException e){
                LOGGER.error("Got Firebase/UnsupportedEncodingException", e);
            }
            LOGGER.info(response);
        }
    }
}
