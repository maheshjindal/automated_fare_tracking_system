package com.pirates.frts.service;


import com.pirates.frts.util.FRTSConstants;
import com.pirates.frts.domain.CardInformation;
import com.pirates.frts.domain.Route;
import com.pirates.frts.domain.TravelHistory;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.TableType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private ObjectMapper objectMapper = new ObjectMapper();
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    CrudService crudService;

    public List<Map<String,Object>> fetchTravelHistory(String userId) throws Exception{
        LOGGER.info("Inside fetchTravelHistory");
        FirebaseResponse response = crudService.getTable(TableType.TRAVEL_HISTORY,null);
        Map<String,Object> responseBody = response.getBody();
        LOGGER.info(responseBody.toString());
        Map<String,TravelHistory> travelSet = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,TravelHistory>>(){});
        List<Map<String,Object>> responseList = new LinkedList<>();
        for (String travelId:travelSet.keySet()) {
            if(travelSet.get(travelId).getUserId().equalsIgnoreCase(userId)){
                String routeId = travelSet.get(travelId).getRouteId();
                FirebaseResponse routeResponse = crudService.getTable(TableType.ROUTE,routeId);
                Map<String,Object> tempResponse = routeResponse.getBody();
                tempResponse.put("userId",userId);
                tempResponse.put("travelId",travelId);
                tempResponse.put("date",travelSet.get(travelId).getDate());
                responseList.add(tempResponse);
            }
        }
        return responseList;
    }
    public HashMap<String,String> isAuthorizedToTravel(TravelHistory history,String userId) throws Exception{
        HashMap<String,String> responseMap = new HashMap<>();
        responseMap.put("isAllowed","false");
        FirebaseResponse response = crudService.getTable(TableType.CARD_INFORMATION,null);
        Map<String,Object> responseBody = response.getBody();
        LOGGER.info(responseBody.toString());
        Map<String,CardInformation> cardInfo = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,CardInformation>>(){});

        for (String rfId:cardInfo.keySet()) {
            if(cardInfo.get(rfId).getUserId().equalsIgnoreCase(userId)){
                String userRouteId = history.getRouteId();
                FirebaseResponse routeResponse = crudService.getTable(TableType.ROUTE,userRouteId);
                Map<String,Object> routeResponseBody = routeResponse.getBody();
                Route route = objectMapper.readValue(objectMapper.writeValueAsString(routeResponseBody),Route.class);
                if(cardInfo.get(rfId).getBalance()-route.getFare() >= Double.parseDouble(FRTSConstants.MIN_CARD_BALANCE)){
                    responseMap.put("isAllowed","true");
                    responseMap.put("routeFare",""+route.getFare());
                    responseMap.put("cardLimit",""+cardInfo.get(rfId).getCardLimit());
                }
                break;
            }
        }
        return responseMap;
    }

    public void createTableByJson(TableType tableType, String jsonString, String primaryKey){
         crudService.createTable(tableType,jsonString,primaryKey);
    }

    public FirebaseResponse fetchTable(TableType tableType, String primaryKey){
        return crudService.getTable(tableType,primaryKey);
    }
    public void updateCardBalance(String userId,double routeFare) throws Exception{
        FirebaseResponse response = crudService.getTable(TableType.CARD_INFORMATION,null);
        Map<String,Object> responseBody = response.getBody();
        LOGGER.info(responseBody.toString());
        Map<String,CardInformation> cardInfo = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,CardInformation>>(){});

        for (String rfId:cardInfo.keySet()) {
            if(cardInfo.get(rfId).getUserId().equalsIgnoreCase(userId)){
                CardInformation information = cardInfo.get(rfId);
                information.setBalance(information.getCardLimit()-routeFare);
                crudService.createTable(TableType.CARD_INFORMATION,objectMapper.writeValueAsString(information),rfId);
                break;
            }
        }

    }



}
