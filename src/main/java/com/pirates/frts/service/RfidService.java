package com.pirates.frts.service;

import com.pirates.frts.FRTSConstants;
import com.pirates.frts.domain.*;
import com.pirates.frts.error.UserNotRegisteredException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.StringUtils;
import com.pirates.frts.util.TableType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class RfidService {

    private ObjectMapper objectMapper = new ObjectMapper();
    protected static final Logger LOGGER = LoggerFactory.getLogger(RfidService.class);



    @Autowired
    CrudService crudService;

    public void tagUserRfid(UserPathTracker userPathTracker) throws Exception{
        if(StringUtils.containsValue(userPathTracker.getSourceLocationId()) ||
                !StringUtils.containsValue(userPathTracker.getDestinationLocationId())){
            if(StringUtils.containsValue(userPathTracker.getSourceLocationId())){
                //This is the checkout case from the station
                //out time,out station needs to be updated
                FirebaseResponse response = crudService.getTable(TableType.USER_PATH_TRACKER,userPathTracker.getRfId());
                if(response.getBody() != null){
                    UserPathTracker info = objectMapper.readValue(objectMapper.writeValueAsString(response.getBody()),UserPathTracker.class);
                    info.setDestinationLocationId(userPathTracker.getDestinationLocationId());
                    info.setOutTime(userPathTracker.getOutTime());

                    //update the user history table
                    TravelHistory history = new TravelHistory();

                    //find the route id if exists in table

                    FirebaseResponse routeResponse = crudService.getTable(TableType.ROUTE,null);
                    Map<String,Object> responseBody = routeResponse.getBody();
                    LOGGER.info(responseBody.toString());
                    Map<String,Route> routeInfo = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,Route>>(){});

                    for (String routeId:routeInfo.keySet()) {
                            if(routeInfo.get(routeId).getOriginLocationId().equalsIgnoreCase(info.getSourceLocationId())&&
                                    routeInfo.get(routeId).getDestinationOriginId().equalsIgnoreCase(info.getDestinationLocationId())
                            ){
                                LOGGER.info("Found route id for the route, now will check the fare");
                                double routeFare = routeInfo.get(routeId).getFare();
                                history.setRouteId(routeId);
                                history.setUserId(userPathTracker.getUserId());
                                history.setDate(new Date().toString());
                        }
                    }

                }

            }else{
                //this is the checkin case at the station, intime,instation, servicetype needs to be udpated

                boolean isUserValid = false;
                //first check if rfid is associated to any user from the card information table
                FirebaseResponse response = crudService.getTable(TableType.CARD_INFORMATION,userPathTracker.getRfId());
                if(response.getBody() != null){
                    CardInformation info = objectMapper.readValue(objectMapper.writeValueAsString(response.getBody()),CardInformation.class);
                    if(StringUtils.containsValue(info.getUserId()) && info.getBalance() >= Double.parseDouble(FRTSConstants.MIN_CARD_BALANCE)){
                       isUserValid=true;

                    }
                }
                if(isUserValid){
                    UserPathTracker userTracker = new UserPathTracker();
                    userTracker.setInTime(userPathTracker.getInTime());
                    userTracker.setSourceLocationId(userPathTracker.getSourceLocationId());
                    userTracker.setServiceType(userPathTracker.getServiceType());
                    userTracker.setRfId(userPathTracker.getRfId());
                    userTracker.setUserId(userPathTracker.getUserId());
                    crudService.createTable(TableType.USER_PATH_TRACKER,objectMapper.writeValueAsString(userTracker),userTracker.getRfId());
                }
            }
        }else{
            //invalid case
            throw  new RuntimeException("Not a Valid Rfid Card Swipe.Transaction Declined");
        }
    }

    public void registerCard(CardInformation cardInformation) throws Exception{
        boolean isValidUser = false;
        String userId = cardInformation.getUserId();
        if(!StringUtils.containsValue(userId)){
            FirebaseResponse routeResponse = crudService.getTable(TableType.USER,null);
            Map<String,Object> responseBody = routeResponse.getBody();
            LOGGER.info(responseBody.toString());
            Map<String,User> userInfo = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,User>>(){});

            for (String actualUserId:userInfo.keySet()) {
                if(actualUserId.equalsIgnoreCase(userId)){
                    isValidUser = true;
                    break;
                }
            }
            if(isValidUser){
                CardInformation cardInfo = new CardInformation();
                if(cardInformation.getCardLimit() >= Double.parseDouble(FRTSConstants.MIN_DEFAULT_CARD_LIMIT)){
                    cardInfo.setCardLimit(cardInformation.getCardLimit());
                }else {
                    LOGGER.warn("Invalid card limit, So using the default card limit");
                    cardInfo.setCardLimit(Double.parseDouble(FRTSConstants.DEFAULT_CARD_LIMIT));
                }
                if(cardInformation.getBalance() >= Double.parseDouble(FRTSConstants.MIN_CARD_BALANCE) &&
                cardInformation.getBalance() <= Double.parseDouble(FRTSConstants.CARD_MAX_LIMIT)){
                    cardInfo.setBalance(cardInformation.getBalance());
                }else {
                    LOGGER.warn("Invalid card balance, So using the default card balance");
                    cardInfo.setCardLimit(Double.parseDouble(FRTSConstants.DEFAULT_CARD_BALANCE));
                }
                String rfid = UUID.randomUUID().toString();
                cardInfo.setRfId(rfid);
                crudService.createTable(TableType.CARD_INFORMATION,objectMapper.writeValueAsString(cardInfo),rfid);
            }


        }else{
            LOGGER.error("A valid user  is required for the rfid registration");
            throw new UserNotRegisteredException("User is not registered");
        }
    }

}
