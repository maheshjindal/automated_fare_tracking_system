package com.pirates.frts.rest;

import com.pirates.frts.domain.CardInformation;
import com.pirates.frts.domain.UserPathTracker;
import com.pirates.frts.service.RfidService;
import com.pirates.frts.util.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * This controller provides the rest interface for communicating with rfid server.Using any IOT device, we can directly hit this endpoints
 * which will track the things automatically and update the status,and send the text message as the updates
 *
 * **/
@RestController
public class RfidController {

    @Autowired
    RfidService rfidService;

    protected static final Logger LOGGER = LoggerFactory.getLogger(RfidController.class);

    /****
     * To tag the rfid reader when user visits any metro/bus station, it will automatically
     * upgrade the db in firebase everywhere
     *
     * **/

    @PostMapping("/rfid")
    public ResponseEntity<String> tagUserRfid(@RequestBody UserPathTracker userPathTracker){
        try {
            rfidService.tagUserRfid(userPathTracker);
        }catch (Exception ex){
            LOGGER.error("Unable to presently tag the user id",ex);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rfid/register")
    public ResponseEntity<String> registerRfid(@RequestBody CardInformation cardInformation){
        try {
            rfidService.registerCard(cardInformation);
        }catch (Exception ex){
            LOGGER.error("Unable to register new user with rfid");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
