package com.pirates.frts.service;

import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.Firebase;
import com.pirates.frts.util.TableType;
import org.apache.log4j.spi.RootLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class CrudService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CrudService.class);

    @Autowired
    FirebaseService firebaseService;

    /***
     *
     * To create a new table at the firebase location
     *
     * **/
    public void createTable(TableType tableType, String jsonDataString,String primaryId){
        Firebase firebase = null;
        try{
            firebase = firebaseService.getInstanceforTable(tableType.getName(),primaryId);
        }catch (Exception e){

        }
        if(firebase != null){
            FirebaseResponse response = null;
            try{
                response = firebase.put(jsonDataString);

            }catch (FirebaseException | UnsupportedEncodingException e){
                LOGGER.error("Got Firebase/UnsupportedEncodingException", e);
            }
            LOGGER.info(response.toString());
            System.out.println(response);
        }
    }

    /***
     * To fetch data of some specific table
     *
     * **/
    public FirebaseResponse getTable(TableType tableType,String primaryId){
        Firebase firebase = null;
        try{
            firebase = firebaseService.getInstanceforTable(tableType.getName(),primaryId);
        }catch (Exception e){

        }
        if(firebase != null){
            FirebaseResponse response = null;
            try{
                response = firebase.get();

            }catch (FirebaseException | UnsupportedEncodingException e){
                LOGGER.error("Got Firebase/UnsupportedEncodingException", e);
            }
            LOGGER.info(response.toString());
            System.out.println(response);
            return response;
        }
        return null;
    }

    /***
     *
     * To update the data of some existing table
     *
     * ***/
    public FirebaseResponse updateTable(TableType tableType,String jsonDataString,String primaryId){
        Firebase firebase = null;
        try{
            firebase = firebaseService.getInstanceforTable(tableType.getName(),primaryId);
        }catch (Exception e){
            LOGGER.error("Unable to fetch Service Instance for firebase");
        }
        if(firebase != null){
            FirebaseResponse response = null;
            try{
                response = firebase.put(jsonDataString);

            }catch (FirebaseException | UnsupportedEncodingException e){
                LOGGER.error("Got Firebase/UnsupportedEncodingException", e);
            }
            LOGGER.info(response.toString());
            System.out.println(response);
            return response;
        }
        return null;
    }


    /****
     *
     * To delete some specific table
     *
     * **/
    public FirebaseResponse deleteTable(TableType tableType,String primaryId){
        Firebase firebase = null;
        try{
            firebase = firebaseService.getInstanceforTable(tableType.getName(),primaryId);
        }catch (Exception e){
            LOGGER.error("Unable to fetch Service Instance for firebase");
        }
        if(firebase != null){
            FirebaseResponse response = null;
            try{
                response = firebase.delete();

            }catch (FirebaseException | UnsupportedEncodingException e){
                LOGGER.error("Got Firebase/UnsupportedEncodingException", e);
            }
            LOGGER.info(response.toString());
            System.out.println(response);
            return response;
        }
        return null;
    }



}
