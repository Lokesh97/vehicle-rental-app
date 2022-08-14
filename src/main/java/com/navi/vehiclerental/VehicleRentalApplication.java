package com.navi.vehiclerental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VehicleRentalApplication {
    private static final String configFile = "config.xml";
    @Autowired
    VehicleRentalApplicationTest vehicleRentalApplicationTest;

    private void start() throws IOException {
       this.vehicleRentalApplicationTest.start();
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(configFile);

        VehicleRentalApplication app = context.getBean(VehicleRentalApplication.class);
        app.start();
    }
}
