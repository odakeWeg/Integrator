package com.edson;

import javafx.application.Application;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import gateway.DesktopApplicationGateway;

@SpringBootApplication
public class SpringInjector {
    
    public static void main(String[] args) {
		//SingleFrameApplication.launch(DesktopApplicationGateway.class, args);
		//org.jdesktop.application.Application.launch(DesktopApplicationGateway.class, args);

		Application.launch(App.class);
	}

}
