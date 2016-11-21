package example;

import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Date;

public class Hello {

    private Date lambdaContainerStartDate = new Date();

    public String myHandler(String name, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("This Lambda container has been created at %s\n", lambdaContainerStartDate));
        logger.log(String.format("Hello %s\n", name));
        return name;
    }
}
