package com.edson.util;

import org.jdesktop.application.SingleFrameApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.edson.SpringInjector;

import gateway.DesktopApplicationGateway;

public class ApplicationSetup {
    private static ConfigurableApplicationContext applicationContext;

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(String[] args) {
        if(applicationContext == null) {
            applicationContext = new SpringApplicationBuilder().sources(SpringInjector.class).run(args);
        }
    }

    public static void initiateGateway(String[] args) {
        SingleFrameApplication.launch(DesktopApplicationGateway.class, args);
    }
    
}
