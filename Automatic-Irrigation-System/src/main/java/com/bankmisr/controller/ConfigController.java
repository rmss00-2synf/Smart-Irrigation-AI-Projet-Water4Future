package com.bankmisr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmisr.service.ConfigService;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @PostMapping("/changeIrrigationStatus")
    public void changeIrrigationStatus(@RequestBody boolean irrigationStatus) {
        configService.setManualMode(irrigationStatus);
    }
}
