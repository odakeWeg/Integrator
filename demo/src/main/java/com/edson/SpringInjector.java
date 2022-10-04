package com.edson;

import javafx.application.Application;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gateway.DesktopApplicationGateway;

//@ImportResource("classpath:demo/bean.xml")
@SpringBootApplication
public class SpringInjector {
    
    public static void main(String[] args) {
		//SingleFrameApplication.launch(DesktopApplicationGateway.class, args);
		//org.jdesktop.application.Application.launch(DesktopApplicationGateway.class, args);

		Application.launch(App.class);
	}

}
