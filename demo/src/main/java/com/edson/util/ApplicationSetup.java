package com.edson.util;

import org.jdesktop.application.SingleFrameApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.edson.SpringInjector;
import com.edson.model.dto.SessionDTO;

import gateway.DesktopApplicationGateway;

public class ApplicationSetup {
    private static ConfigurableApplicationContext applicationContext;

    private static SessionDTO sessionDTO;

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

    public static void sair() {
        SingleFrameApplication.getInstance().exit();
    }



    public static SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public static void setSessionDTO(SessionDTO session) {
        sessionDTO = session;
    }
    
}
