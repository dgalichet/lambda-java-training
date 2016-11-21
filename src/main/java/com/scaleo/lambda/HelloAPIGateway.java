package com.scaleo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class HelloAPIGateway {

    public String helloHandler(Person person, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Received a Hello from " + person);

        return "Hi " + person.getFirstname() + " " + person.getLastname();
    }

}
