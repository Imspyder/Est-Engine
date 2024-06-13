package com.estimationengine.controller;

import com.estimationengine.dto.CustomerScopesDTO;
import com.estimationengine.entity.AppSize;
import com.estimationengine.repo.CommonRepo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/application")
//@Api(value = "Example API", description = "API operations for the Example application")
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class.getName());
    Map<String, Object> responseMap = new HashMap<>();

    @Autowired
    private CommonRepo commonRepo;


    //Getting application effort by scope and app size
    @GetMapping("/getAppEffort")
    public ResponseEntity<Map<String, Object>> getAppEffort(@RequestParam String size, @RequestParam String scope) {
        Map<String , Object> appEffortCountMap= commonRepo.getAppEffortByAppSizeAndScope(size, scope);
        if(appEffortCountMap!=null){
            responseMap.put("success",true);
            responseMap.put("data",appEffortCountMap);
        }else {
            responseMap.put("success",false);
            responseMap.put("data", null);
        }
        logger.info("getAppEffort method executed with parameters: size={}, scope={}", size, scope);
        
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    //Getting all application size
    @GetMapping("/getAllSize")
    public ResponseEntity<Map<String, Object>> getAllSize() {
        List<AppSize> appSizes=commonRepo.getAllSize();

        if (!CollectionUtils.isEmpty(appSizes)) {
            responseMap.put("success", true);
            responseMap.put("data", appSizes);
        } else {
            responseMap.put("success",false);
            responseMap.put("data", null);
        }
        logger.info("getAllSize method executed");
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
