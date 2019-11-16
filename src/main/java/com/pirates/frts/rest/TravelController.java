package com.pirates.frts.rest;


import com.pirates.frts.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TravelController {

    @PostMapping("/create/location")
    public ResponseEntity<String> createNewLocation(@RequestBody Location request){

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/create/route")
    public ResponseEntity<String> createNewRoute(@RequestBody Map<String,String> request){
        firebaseService.createTable("",request);
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
