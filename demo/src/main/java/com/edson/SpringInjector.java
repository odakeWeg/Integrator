package com.edson;

import javafx.application.Application;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edson.util.ApplicationSetup;

@SpringBootApplication
public class SpringInjector {
	
    public static void main(String[] args) {
		ApplicationSetup.initiateGateway(args);
		Application.launch(App.class);
	}

}
