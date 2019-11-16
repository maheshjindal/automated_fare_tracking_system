
package com.pirates.frts.rest;


import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.service.CrudService;
import com.pirates.frts.service.UserService;
import com.pirates.frts.util.Firebase;
import com.pirates.frts.util.TableType;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.pirates.frts.domain.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TravelController {

    private ObjectMapper objectMapper = new ObjectMapper();
    protected static final Logger LOGGER = LoggerFactory.getLogger(TravelController.class);

    @Autowired
    UserService userService;


    @GetMapping("/get/route")
    public ResponseEntity<FirebaseResponse> getRoute(@RequestParam("routeId")String routeId) throws Exception, FirebaseException {
        return new ResponseEntity<>(userService.fetchTable(TableType.ROUTE,routeId),HttpStatus.OK);
    }
    @GetMapping("/get/location")
    public ResponseEntity<FirebaseResponse> getLocation(@RequestParam("locationId")String locationId) throws Exception, FirebaseException {
        return new ResponseEntity<>(userService.fetchTable(TableType.LOCATION,locationId),HttpStatus.OK);
    }
    @GetMapping("/get/user")
    public ResponseEntity<FirebaseResponse> getUser(@RequestParam("userId")String userId) throws Exception, FirebaseException {
        return new ResponseEntity<>(userService.fetchTable(TableType.USER,userId),HttpStatus.OK);
    }
    @GetMapping("/get/cardInformation")
    public ResponseEntity<FirebaseResponse> getCardInformation(@RequestParam("cardInformation")String cardInformation) throws Exception, FirebaseException {
        return new ResponseEntity<>(userService.fetchTable(TableType.CARD_INFORMATION,cardInformation),HttpStatus.OK);
    }

    @PostMapping("/create/location")
    public ResponseEntity<String> createNewLocation(@RequestBody Location request) throws IOException {
        userService.createTableByJson(TableType.LOCATION,objectMapper.writeValueAsString(request),request.getLocationId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/route")
    public ResponseEntity<String> createNewRoute(@RequestBody Route route) throws IOException {
        userService.createTableByJson(TableType.ROUTE,objectMapper.writeValueAsString(route),route.getRouteId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createNewUser(@RequestBody User request) throws IOException {
        userService.createTableByJson(TableType.USER,objectMapper.writeValueAsString(request),request.getUserId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @PostMapping("/create/cardInformation")
    public ResponseEntity<String> createNewCardInfo(@RequestBody CardInformation request) throws IOException {
        userService.createTableByJson(TableType.CARD_INFORMATION,objectMapper.writeValueAsString(request),request.getRfId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/travelHistory")
    public ResponseEntity<String> createNewTravelHistory(@RequestBody TravelHistory request) throws Exception {
        HashMap<String,String> response = userService.isAuthorizedToTravel(request,request.getUserId());
        if(response.get("isAllowed").equalsIgnoreCase("true")){
            userService.createTableByJson(TableType.TRAVEL_HISTORY,objectMapper.writeValueAsString(request),request.getTravelId());
            userService.updateCardBalance(request.getUserId(),Double.parseDouble(response.get("routeFare")));
        }else{

        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @GetMapping("/get/travelHistory")
    public ResponseEntity<List<Map<String,Object>>> getTravelHistory(@RequestParam("userId")String userId) throws Exception{
        return new ResponseEntity<>(userService.fetchTravelHistory(userId),HttpStatus.OK);

    }

}
