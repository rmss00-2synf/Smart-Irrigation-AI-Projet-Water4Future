package com.bankmisr.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
public class ConfigService {

    private Boolean manualMode = false;

}