package com.pirates.frts.service;


import com.pirates.frts.model.AuthorizationResponse;
import com.pirates.frts.util.FRTSConstants;
import com.pirates.frts.domain.CardInformation;
import com.pirates.frts.domain.Route;
import com.pirates.frts.domain.TravelHistory;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.TableType;
import io.netty.util.internal.MathUtil;
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
    public AuthorizationResponse isAuthorizedToTravel(TravelHistory history,String userId) throws Exception{
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setAllowed(false);
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
                if(cardInfo.get(rfId).getBalance()-route.getFare() >= FRTSConstants.MIN_CARD_BALANCE){
                    authorizationResponse.setAllowed(true);
                    authorizationResponse.setRouteFare(route.getFare());
                    authorizationResponse.setCardLimit(cardInfo.get(rfId).getCardLimit());
                }
                break;
            }
        }
        return authorizationResponse;
    }

    public void createTableByJson(TableType tableType, String jsonString, String primaryKey){
         crudService.createTable(tableType,jsonString,primaryKey);
    }

    public FirebaseResponse fetchTable(TableType tableType, String primaryKey){
        return crudService.getTable(tableType,primaryKey);
    }
    public void updateCardBalance(String userId,float routeFare) throws Exception{
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

    public Map<String,Object> getUserSpent(String userId) throws Exception{
        Map<String,Object> responseMap = new HashMap<>();
        FirebaseResponse response = crudService.getTable(TableType.TRAVEL_HISTORY,null);
        Map<String,Object> responseBody = response.getBody();
        LOGGER.info(responseBody.toString());
        Map<String,TravelHistory> travelSet = objectMapper.readValue(objectMapper.writeValueAsString(responseBody),new TypeReference<Map<String,TravelHistory>>(){});
        List<Float> totalExpense = new ArrayList<>();
        for (String travelId:travelSet.keySet()) {
            if(travelSet.get(travelId).getUserId().equalsIgnoreCase(userId)){
                TravelHistory information = travelSet.get(travelId);
                String routeId = information.getRouteId();
                Map<String,Object> routeResponseBody = crudService.getTable(TableType.ROUTE,routeId).getBody();
                Route route = objectMapper.readValue(objectMapper.writeValueAsString(routeResponseBody),Route.class);
                totalExpense.add(route.getFare());
            }
        }
        if(totalExpense.size()>0){
            responseMap.put("minFare",Collections.min(totalExpense));
            responseMap.put("maxFare",Collections.max(totalExpense));
            float sum = 0;
            for (float num:totalExpense) {
                sum+=num;
            }
            responseMap.put("totalSpent", sum);
        }

        return responseMap;
    }



}
