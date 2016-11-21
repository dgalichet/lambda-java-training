package com.scaleo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

import java.util.Date;

import static java.util.stream.Collectors.joining;

public class HelloS3 {

    private Date lambdaContainerStartDate = new Date();

    public void s3EventHandler(S3Event s3Event, Context context) {
        LambdaLogger logger = context.getLogger();
        String listEvents = s3Event.getRecords().stream()
                .map(S3EventNotification.S3EventNotificationRecord::getEventName)
                .collect(joining(", "));
        logger.log(String.format("This Lambda container has been created at %s\n", lambdaContainerStartDate));
        logger.log(String.format("This Lambda has been invoked because of %s\n", listEvents));
    }
}
