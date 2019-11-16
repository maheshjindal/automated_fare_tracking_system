package com.pirates.frts.rest;


import com.pirates.frts.service.CrudService;
import com.pirates.frts.util.TableType;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
public class TravelController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CrudService crudService;


    @GetMapping("/")
    public ResponseEntity<String> getStatus(){
//        crudService.createTable(TableType.ROUTE,objectMapper.writeValueAsString());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
