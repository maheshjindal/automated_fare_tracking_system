package com.pirates.frts.service;


import com.pirates.frts.domain.TravelHistory;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.util.Firebase;
import com.pirates.frts.util.TableType;
import com.pirates.frts.util.TravelHistoryMapper;
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
                tempResponse.put("date",travelSet.get(travelId).getDate());
                responseList.add(tempResponse);
            }
        }
        return responseList;
    }

    public void createTableByJson(TableType tableType, String jsonString, String primaryKey){
         crudService.createTable(tableType,jsonString,primaryKey);
    }

    public FirebaseResponse fetchTable(TableType tableType, String primaryKey){
        return crudService.getTable(tableType,primaryKey);
    }



}
