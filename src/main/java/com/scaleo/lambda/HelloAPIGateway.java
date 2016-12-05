package com.scaleo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Date;

public class HelloAPIGateway {

    private Date containerCreation  = new Date();

    public String helloHandler(Person person, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This container has been created at " + containerCreation.toString());
        logger.log("Received a Hello from " + person);

        return "Hi " + person.getFirstname() + " " + person.getLastname();
    }

}
