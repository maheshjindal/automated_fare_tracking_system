
package com.pirates.frts.rest;


import com.pirates.frts.error.FirebaseException;
import com.pirates.frts.model.FirebaseResponse;
import com.pirates.frts.service.CrudService;
import com.pirates.frts.util.Firebase;
import com.pirates.frts.util.TableType;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.pirates.frts.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class TravelController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CrudService crudService;


    @GetMapping("/")
    public ResponseEntity<String> getStatus(@RequestParam("id")String id) throws Exception {
        Route route = new Route();
        route.setFare(20);
        route.setDistance(10);
        route.setRouteId("456");
        route.setRouteId(id);
        crudService.createTable(TableType.ROUTE,objectMapper.writeValueAsString(route),id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @GetMapping("/getTable")
    public ResponseEntity<FirebaseResponse> getTable(@RequestParam("id")String id) throws Exception, FirebaseException {
        return new ResponseEntity<>(crudService.getTable(TableType.ROUTE,id),HttpStatus.OK);
    }
    @PostMapping("/create/location")
    public ResponseEntity<String> createNewLocation(@RequestBody Location request){

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/route")
    public ResponseEntity<String> createNewRoute(@RequestBody Map<String,String> request){
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createNewRoute(@RequestBody User request){

        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @PostMapping("/create/cardInformation")
    public ResponseEntity<String> createNewRoute(@RequestBody CardInformation request){

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/travelHistory")
    public ResponseEntity<String> createNewRoute(@RequestBody TravelHistory request){
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
